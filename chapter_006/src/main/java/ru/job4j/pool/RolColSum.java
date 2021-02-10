package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }
        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }
        public int getColSum() {
            return colSum;
        }
        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        final Sums[] sums = new Sums[matrix.length];
        int rowSum;
        int colSum;

        for (int ind = 0; ind < matrix.length; ind++) {
            Sums ss = new Sums();
            rowSum = 0;
            colSum = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                rowSum += matrix[ind][i];
                colSum += matrix[i][ind];
            }
            ss.setRowSum(rowSum);
            ss.setColSum(colSum);
            sums[ind] = ss;
        }
    return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        Sums[] sums = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int startIndex) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sum = new Sums();
            int rowSum = 0;
            int colSum = 0;

            for (int i = 0; i < data[0].length; i++) {
                rowSum += data[startIndex][i];
                colSum += data[i][startIndex];
            }
            sum.setRowSum(rowSum);
            sum.setColSum(colSum);
            return sum;
        });
    }
}