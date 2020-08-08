package ru.job4j.io;

import java.io.*;

public class Analyze {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                    new FileOutputStream(target)))) {
                String dataStart = null;
                String dataStop = null;
                boolean start = false;
                while ((line = in.readLine()) != null) {
                    String[] strArray = line.split(" ");
                    if (!start  && (strArray[0].equals("500") || strArray[0].equals("400"))) {
                        dataStart = strArray[1];
                        start = true;
                    }
                    if (start && (strArray[0].equals("300") || strArray[0].equals("200"))) {
                        dataStop = strArray[1];
                        start = false;
                    }
                    if (dataStart != null && dataStop != null) {
                        out.write(dataStart + ";" + dataStop + ";" + System.lineSeparator());
                        dataStart = null;
                        dataStop = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analyze ana = new Analyze();
        ana.unavailable("./chapter_002/src/main/java/ru/job4j/io/data/serverLog",
                "./chapter_002/src/main/java/ru/job4j/io/data/unavailable.csv");
    }
}