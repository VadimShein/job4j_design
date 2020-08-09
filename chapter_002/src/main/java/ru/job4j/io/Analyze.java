package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analyze {
    public void unavailable(String source, String target) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
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
                    list.add(dataStart + ";" + dataStop + ";" + System.lineSeparator());
                    dataStart = null;
                    dataStop = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        write(list, target);
    }

    private void write(List<String> list, String target) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (String str : list) {
                out.write(str);
            }
        }
    }
}
