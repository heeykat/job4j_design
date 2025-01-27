package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> list = readPhrases();
        String conditions = String.format("""
                - при вводе текста программа направляет ответ
                - при вводе '%s' программа перестает отвечать
                - при вводе '%s' программа продолжает отвечать
                - при вводе '%s' программа завершает работу
                """, STOP, CONTINUE, OUT);
        boolean isNext = true;
        while (isNext) {
            System.out.println(conditions);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case OUT:
                    isNext = false;
                    log.add(input);
                    scanner.close();
                    saveLog(log);
                    System.out.println("Программа завершает работу");
                    break;
                case STOP:
                    log.add(input);
                    System.out.printf("Для продолжения диалога введите '%s'\n", CONTINUE);
                    boolean isContinue = false;
                    while (!isContinue) {
                        String newInput = scanner.nextLine().toLowerCase();
                        log.add(newInput);
                        if (newInput.equals(CONTINUE)) {
                            isContinue = true;
                            System.out.println("Программа продолжает работу");
                        } else {
                            System.out.printf("Для продолжения диалога введите '%s'\n", CONTINUE);
                        }
                    }
                    break;
                default:
                    log.add(input);
                    int index = new Random().nextInt(list.size());
                    String answer = list.get(index);
                    log.add(answer);
                    System.out.println(answer);
                    break;
            }
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            list = reader.lines()
                    .map(string -> string + System.lineSeparator())
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/logPoem.txt", "data/poem.txt");
        consoleChat.run();
    }
}