package ru.job4j.io;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private boolean run = true;
    private boolean pause = false;
    private List<String> list = new ArrayList<>();

    public void start() {
        while (run) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            list.add(line);
                if (line.equals("закончить")) {
                    System.out.println("Программа звершена");
                    run = false;
                    break;
                }
                if (line.equals("стоп")) {
                    System.out.println("Работа программы приостановлена");
                    pause = true;
                }
                if (line.equals("продолжить")) {
                    System.out.println("Работа программы возобновлена");
                    pause = false;
                }
                if (!pause) {
                    list.add(read());
                }
        }
        write();
    }

    private String read() {
        String rsl = null;
        String file = "./chapter_002/src/main/java/ru/job4j/io/data/chatAnswers.txt";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            int n = 1;
            int rand = (int) (1 + Math.random() * 10);
            while ((line = in.readLine()) != null) {
                if (n == rand) {
                    rsl = line;
                    System.out.println(line);
                    break;
                }
                n++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void write() {
        String file = "./chapter_002/src/main/java/ru/job4j/io/data/logChat.txt";
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : list) {
                out.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat();
        chat.start();
    }
}
