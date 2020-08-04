package ru.job4j.collection;

import java.util.Map;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean rsl = true;
        Map<Character, Integer> map = new java.util.HashMap<>();
        char[] charArrayLeft = left.toCharArray();
        char[] charArrayRight = right.toCharArray();

        for (char c : charArrayLeft) {
            int count = 1;
            if (map.containsKey(c)) {
                count = map.get(c) + 1;
            }
            map.put(c, count);
        }
        for (char c : charArrayRight) {
            int count = -1;
            if (map.containsKey(c)) {
                count = map.get(c) - 1;
            }
            map.put(c, count);
        }
        for (char c : map.keySet()) {
            if (map.get(c) != 0) {
                rsl = false;
            }
        }
    return rsl;
    }
}