package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {
    private final int[] array;
    private final int index;
    private final int from;
    private final int to;

    public ParallelSearch(int[] array, int index, int from, int to) {
        this.array = array;
        this.index = index;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        Integer result = null;

        if (from - to  < 11) {
            for (int i : array) {
                if (i == index) {
                    return index;
                }
            }
        }
        int mid = (from + to) / 2;
        ParallelSearch leftSearch = new ParallelSearch(array, index, from, mid);
        ParallelSearch rightSearch = new ParallelSearch(array, index, mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        Integer left = leftSearch.join();
        Integer right = rightSearch.join();
        if (left == index) {
            result = left;
        } else if (right == index) {
            result = right;
        }
        return result;
    }

    public static int search(int[] array, int index) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearch(array, index, 0, array.length - 1));
    }

    public static void main(String[] args) {
        int[] array = new int[] {9, 4, 7, 8, 5, 6, 10, 12, 3, 15, 14, 4};
        int rsl = search(array, 8);
        System.out.println(rsl);
    }
}
