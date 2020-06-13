package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    public void findIndex() {
        while ((data[index] % 2) > 0 && index < data.length - 1) {
            index++;
        }
    }

    @Override
    public boolean hasNext() {
        findIndex();
        return data[index] % 2 == 0;
    }

    @Override
    public Integer next() {
        int rsl;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        findIndex();
        rsl = data[index];
        index++;
        return rsl;
    }
}
