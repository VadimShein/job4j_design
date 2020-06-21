package ru.job4j.it;

import java.util.*;
import java.util.function.Consumer;

public class SimpleArray<T> implements  Iterable<T> {
    private Object[] data;
    private int index = 0;

    public SimpleArray(int size) {
        this.data = new Object[size];
    }

    public void add(T model) {
        this.data[index++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index,  this.index);
        this.data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index,  this.index);
        this.data[index] = null;
        System.arraycopy(this.data, index + 1, this.data, index, this.index - index - 1);
        this.data[this.index - 1] = null;
        index--;
    }

    public T get(int index) {
        Objects.checkIndex(index,  this.index);
        return (T) this.data[index];
    }

    @Override
    public Iterator<T> iterator() {
           return new Iterator<T>() {
               private int position = 0;
                @Override
                public boolean hasNext() {
                    return position < index;
                }

                @Override
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return (T) data[position++];
                }
            };
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
