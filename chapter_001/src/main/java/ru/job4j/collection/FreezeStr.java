package ru.job4j.collection;

import java.util.Arrays;

public class FreezeStr {
    public static boolean eq(String left, String right) {
        boolean rsl = true;
        char[] charArrayLeft =  left.toCharArray();
        char[] charArrayRight =  right.toCharArray();
        Arrays.sort(charArrayLeft);
        Arrays.sort(charArrayRight);

        int size  = Math.min(charArrayLeft.length, charArrayRight.length);
        for (int i = 0; i < size; i++) {
            if (charArrayLeft[i] != charArrayRight[i]) {
                rsl = false;
                break;
            }
        }
        return rsl && charArrayLeft.length == charArrayRight.length;
    }
}