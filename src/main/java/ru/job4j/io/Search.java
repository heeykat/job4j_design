package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<String> listAllowExtensions = List.of(".js");

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Error in the number of arguments");
        }
        Path start = Paths.get(args[0]);
        pathValidation(start);
        String extension = args[1];
        extensionValidation(extension);
        search(start, path -> path.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    private static void pathValidation(Path start) {
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toAbsolutePath()));
        }
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toAbsolutePath()));
        }
    }

    private static void extensionValidation(String extension) {
        if (!listAllowExtensions.contains(extension)) {
            throw new IllegalArgumentException(String.format("Not allowed extension %s", extension));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}