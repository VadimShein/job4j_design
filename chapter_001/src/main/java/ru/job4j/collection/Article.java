package ru.job4j.collection;

public class Article {
    public static boolean generateBy(String origin, String line) {
        boolean rsl = true;
        java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
        String[] originArray = origin.replaceAll("[^a-zA-Zа-яА-Я ]", "").toLowerCase().split(" ");
        String[] lineArray = line.replaceAll("[^a-zA-Zа-яА-Я ]", "").toLowerCase().split(" ");

        for (String c : originArray) {
            int count = 1;
            if (map.containsKey(c)) {
                count = map.get(c) + 1;
            }
            map.put(c, count);
        }
        for (String c : lineArray) {
            int count = -1;
            if (map.containsKey(c)) {
                count = map.get(c) - 1;
            }
            map.put(c, count);
        }
        for (String c : map.keySet()) {
            if (map.get(c) < 0) {
                rsl = false;
            }
        }
        return rsl;
    }
}
