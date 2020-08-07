package ru.job4j.io;

import ru.job4j.io.Matrix;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        int[][] matrix = new Matrix().multiple(4);
            try (FileOutputStream out = new FileOutputStream("./chapter_002/src/main/java/ru/job4j/io/data/result.txt")) {
                for (int[] ints : matrix) {
                    String str = Arrays.toString(ints);
                    out.write((str + System.lineSeparator()).getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}