package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<List<String>> data = new ArrayList<>();
        List<String> filteredData = new ArrayList<>();
        Path path = Paths.get(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        try (Scanner scanner = new Scanner(path.toFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(Arrays.stream(line.split(delimiter)).toList());
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        if (data.isEmpty()) {
            throw new IllegalArgumentException("The CSV file is empty or unreadable");
        }
        List<List<Integer>> indexes = new ArrayList<>();
        List<String> header = data.get(0);
        for (int i = 0; i < header.size(); i++) {
            for (int j = 0; j < filter.length; j++) {
                if (header.get(i).equals(filter[j])) {
                    indexes.add(Arrays.asList(i, j));
                }
            }
        }
        for (List<String> line : data) {
            StringJoiner joiner = new StringJoiner(delimiter);
            String[] rstLine = new String[indexes.size()];
            for (List<Integer> index : indexes) {
                String el = line.get(index.get(0));
                rstLine[index.get(1)] = el;
            }
            for (String el : rstLine) {
                joiner.add(el);
            }
            filteredData.add(joiner.toString());
        }
        if ("stdout".equals(out)) {
            for (String line : filteredData) {
                System.out.println(line);
            }
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(out, true))) {
                filteredData.forEach(writer::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("Number of arguments must be at least 4");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}