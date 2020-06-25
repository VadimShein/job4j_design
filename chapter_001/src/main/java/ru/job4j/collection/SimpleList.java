package ru.job4j.collection;

import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private Node<E> current;
    private int modCount = 0;

    public E get(int index) {
        Objects.checkIndex(index,  modCount);
        Node<E> rsl = current;
        int position = 0;
        while (position < index) {
            rsl = current.getNext();
            position++;
        }
        return rsl.getItem();
    }

    public void add(E model) {
        if (modCount == 0) {
            current  = new Node<>(null, model, null);
        } else {
            current.setNext(new Node<>(current, model, null));
        }
        modCount++;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = this.modCount;

        return new Iterator<>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < modCount;
            }

            @Override
            public E next() {
                E rsl;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (position == 0) {
                    rsl = current.getItem();
                } else {
                    rsl = current.getNext().getItem();
                }
                position++;
                return rsl;
            }
        };
    }
}