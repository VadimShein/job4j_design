package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<E>  implements Iterable<E> {
    Object[] container = new Object[1];
    private int index = 0;
    private int modCount = 0;

    private void extensionArray() {
        if (this.index >= this.container.length) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
    }

    public void add(E model) {
        extensionArray();
        boolean access = true;
            for (int i = 0; i < this.index; i++) {
                if (this.container[this.index - 1].equals(model)) {
                    access = false;
                    break;
                }
            }
            if (access) {
                this.container[this.index++] = model;
                this.modCount++;
            }
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = this.modCount;

        return new Iterator<E>() {
            private int position = 0;
            @Override
            public boolean hasNext() {
                return position < index;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[position++];
            }
        };
    }
}
