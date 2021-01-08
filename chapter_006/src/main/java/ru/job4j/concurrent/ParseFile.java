package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized ParseFile setFile(File f) {
        return new ParseFile(f);
    }

    public synchronized File getFile() {
        return file;
    }

    private String get(Predicate<Integer> predicate) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (predicate.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getContent() {
        Predicate<Integer> withUnicode = i -> i > 0;
        return get(withUnicode);
    }

    public synchronized String getContentWithoutUnicode() {
        Predicate<Integer> withoutUnicode = i -> i < 0x80;
        return get(withoutUnicode);
    }

    public synchronized void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}