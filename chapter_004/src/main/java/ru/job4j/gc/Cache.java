package ru.job4j.gc;

public interface Cache {
    String get(String key);

    static String download(String key) {
        return null;
    }
}
