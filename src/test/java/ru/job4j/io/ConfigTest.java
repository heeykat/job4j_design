package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutCommentAndEmptyRow() {
        String path = "data/app/appWithoutCommentsAndEmptyRows.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairWithCommentAndEmptyRow() {
        String path = "data/app/appWithCommentsAndEmptyRows.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairWithWrongKeys() {
        String path = "data/app/appWithWrongKeys.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Incorrect string format");
    }

    @Test
    void whenPairWithWrongValues() {
        String path = "data/app/appWithWrongValues.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Incorrect string format");
    }

    @Test
    void whenPairWithSeveralEqualSignInLine() {
        String path = "data/app/appWithSeveralEqualSignInLine.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org=postgresql.Driver");
    }

    @Test
    void whenWithLineWithoutKeyAndValue() {
        String path = "data/app/appWithLineWithoutKeyAndValue.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Incorrect string format");
    }
}