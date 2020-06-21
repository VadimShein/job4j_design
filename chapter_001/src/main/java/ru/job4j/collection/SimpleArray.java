package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    Object[] container = new Object[1];
    private int index = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index,  this.index);
        return (T) this.container[index];
    }

    public void add(T model) {
        if (this.index >= this.container.length) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
        this.container[this.index++] = (T) model;
        this.modCount++;
    }

    @Override
    public Iterator<T> iterator() {
         int expectedModCount = this.modCount;


        return new Iterator<T>() {
            private int position = 0;
            @Override
            public boolean hasNext() {
                return position < index;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[position++];
            }
        };
    }
}