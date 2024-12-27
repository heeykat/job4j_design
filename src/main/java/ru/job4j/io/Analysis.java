package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> rst = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(source))) {
            boolean wasAvailable = true;
            String start = "";
            List<String[]> lines = input.lines()
                    .map(line -> line.split(" "))
                    .toList();
            for (String[] line : lines) {
                if (line[0].equals("400") || line[0].equals("500")) {
                    if (wasAvailable) {
                        start = line[1];
                        wasAvailable = false;
                    }
                } else {
                    if (!wasAvailable) {
                        rst.add(start + ";" + line[1] + ";");
                        wasAvailable = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter output = new PrintWriter(new FileWriter(target))) {
            rst.forEach(output::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}