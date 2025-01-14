package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/ekaterinasavcuk/projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.printf("Размер директории: %.2f Gb%n", file.getTotalSpace() / 1073741824.0);
        for (File subfile : file.listFiles()) {
            System.out.printf("%s: %.2f Kb%n", subfile.getName(), subfile.length() / 1024.0);
        }
    }
}
