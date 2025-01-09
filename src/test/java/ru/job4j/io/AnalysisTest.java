package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {
    @Test
    void whenOneUnavailablePeriods(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("200 11:01:02");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("10:57:01;10:59:01;");
    }

    @Test
    void whenTwoUnavailablePeriods(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("10:57:01;10:59:01;11:01:02;11:02:02;");
    }

    @Test
    void whenNoUnavailablePeriods(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("300 10:57:01");
            output.println("200 10:58:01");
            output.println("300 10:59:01");
            output.println("200 11:01:02");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("");
    }

    @Test
    void whenNoPeriods(@TempDir Path tempDir) throws IOException {
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString()).isEqualTo("");
    }
}