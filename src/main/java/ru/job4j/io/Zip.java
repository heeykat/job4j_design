package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                if (source.toFile().isDirectory()) {
                    zip.putNextEntry(new ZipEntry(source.toString()));
                } else {
                    zip.putNextEntry(new ZipEntry(source.toString()));
                    try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                        zip.write(input.readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validation(Path directory, String extension, String fileName, String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Number of arguments must be at least 3");
        }
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException(String.format("Incorrect path %s", directory));
        }
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException(String.format("Incorrect extension %s", extension));
        }
        if (!fileName.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Incorrect fileName %s", fileName));
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        ArgsName argsName = ArgsName.of(args);
        Path directory = Paths.get(argsName.get("d"));
        String extension = argsName.get("e");
        String fileName = argsName.get("o");
        validation(directory, extension, fileName, args);
        List<Path> sources = Search.search(directory, path -> !path.toFile().getName().endsWith(extension));
        zip.packFiles(sources, Files.createFile(Paths.get(fileName)).toFile());
    }
}