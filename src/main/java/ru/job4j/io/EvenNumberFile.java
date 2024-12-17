package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String data = text.toString();
            String[] numbers = data.split("\n");
            for (String number : numbers) {
                System.out.print(number);
                if (Integer.parseInt(number) % 2 == 0) {
                    System.out.print(" - четное");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
