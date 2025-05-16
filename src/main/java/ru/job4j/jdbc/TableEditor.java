package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s);", tableName, "id SERIAL PRIMARY KEY"
        );
        execute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE %s;", tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s\n"
                        + "ADD COLUMN %s %s;", tableName, columnName, type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s\n"
                        + "DROP COLUMN %s;", tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s\n"
                        + "RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName
        );
        execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            props.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(props)) {
            String tableName = "cats";
            String columnName1 = "breed";
            String columnName2 = "owner";
            String newColumnName2 = "owners";
            String type = "text";

            tableEditor.dropTable(tableName);
            tableEditor.createTable(tableName);
            System.out.println(tableEditor.getTableScheme("cats"));

            tableEditor.addColumn(tableName, columnName1, type);
            tableEditor.addColumn(tableName, columnName2, type);
            System.out.println(tableEditor.getTableScheme("cats"));

            tableEditor.renameColumn(tableName, columnName2, newColumnName2);
            System.out.println(tableEditor.getTableScheme("cats"));

            tableEditor.dropColumn(tableName, columnName1);
            System.out.println(tableEditor.getTableScheme("cats"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}