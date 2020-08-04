package ru.job4j.collection;

import java.util.Objects;

public class Article {
    public static boolean generateBy(String origin, String line) {
        boolean rsl = true;
        java.util.HashMap<String, String> map = new java.util.HashMap<>();
        String[] originArray = origin.replaceAll("[^a-zA-Zа-яА-Я ]", "").toLowerCase().split(" ");
        String[] lineArray = line.replaceAll("[^a-zA-Zа-яА-Я ]", "").toLowerCase().split(" ");

        for (String strOrigin : originArray) {
            map.put(strOrigin, strOrigin);
        }
        for (String strLine : lineArray) {
            if (!Objects.equals(strLine, map.get(strLine))) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
