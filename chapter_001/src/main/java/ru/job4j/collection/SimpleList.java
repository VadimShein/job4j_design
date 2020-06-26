package ru.job4j.collection;

import java.util.*;

public class SimpleList<E> implements Iterable<E> {
    private Node<E> head;
    private int itemCount = 0;

    public E get(int index) {
        Objects.checkIndex(index, itemCount);
        Node<E> rsl = head;
        int position = 0;
        while (position < index) {
            rsl = head.getNext();
            position++;
        }
        return rsl.getItem();
    }

    public void add(E model) {
        if (head == null) {
            head  = new Node<>(model, null);
        } else {
            Node<E> tail = head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(new Node<>(model, null));
        }
        itemCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = itemCount;
            private Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (expectedModCount != itemCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.getItem();
                node = node.getNext();
                return rsl;
            }
        };
    }
}