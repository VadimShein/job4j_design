package ru.job4j.collection;

public class Article {
    public static boolean generateBy(String origin, String line) {
        boolean rsl = true;
        String[] lineArray = line.split(" ");
        for (String str : lineArray) {
            if (!origin.contains(str)) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
