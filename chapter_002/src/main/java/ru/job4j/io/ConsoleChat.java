package ru.job4j.io;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private boolean run = true;
    private boolean pause = false;
    private List<String> savedPhrases = new ArrayList<>();
    private List<String> readPhrases = new ArrayList<>();
    private String chatAnswers;
    private String chatLog;

    public ConsoleChat(String chatAnswers, String chatLog) {
        this.chatAnswers = chatAnswers;
        this.chatLog = chatLog;
    }
    public void start() {
        final String STOP = "стоп";
        final String CONTINUE = "продолжить";
        final String FINISH = "закончить";
        while (run) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            savedPhrases.add(line);
                if (line.equals(FINISH)) {
                    System.out.println("Программа звершена");
                    run = false;
                    break;
                }
                if (line.equals(STOP)) {
                    System.out.println("Работа программы приостановлена");
                    pause = true;
                }
                if (line.equals(CONTINUE)) {
                    System.out.println("Работа программы возобновлена");
                    pause = false;
                }
                if (!pause) {
                    String phrase = read();
                    System.out.println(phrase);
                    savedPhrases.add(phrase);
                }
        }
        write();
    }
    private String read() {
        if (readPhrases.isEmpty()) {
            try (BufferedReader in = new BufferedReader(new FileReader(chatAnswers))) {
                String line;
                while ((line = in.readLine()) != null) {
                    readPhrases.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int rand = (int) (Math.random() * readPhrases.size());
        return readPhrases.get(rand);
    }
    private void write() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(chatLog)))) {
            for (String str : savedPhrases) {
                out.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String chatAnswers = "./chapter_002/src/main/java/ru/job4j/io/data/chatAnswers.txt";
        String chatLog = "./chapter_002/src/main/java/ru/job4j/io/data/chatLog.txt";
        ConsoleChat chat = new ConsoleChat(chatAnswers, chatLog);
        chat.start();
    }
}
