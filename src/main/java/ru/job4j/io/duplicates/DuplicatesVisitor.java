package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> map = new HashMap<>();

    public Map<FileProperty, List<Path>> getMap() {
        return map;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty key = new FileProperty(attributes.size(), file.getFileName().toString());
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(file);
        }
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        map.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> {
                            System.out.printf("%s - %s Kb\n", entry.getKey().getName(), entry.getKey().getSize() / 1024);
                            entry.getValue().forEach(path ->
                                    System.out.println(path.toAbsolutePath()));
                        }
                );
    }
}