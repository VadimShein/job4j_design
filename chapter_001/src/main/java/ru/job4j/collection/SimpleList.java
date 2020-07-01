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
            rsl = rsl.getNext();
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

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.getNext();
        itemCount--;
    }

    public E deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<E> tmp = head;
        if (itemCount == 1) {
            head = null;
        } else  {
            int point = 0;
            while (point < itemCount - 1) {
                point++;
                tmp = tmp.getNext();
            }
            tmp.setNext(null);
        }
        itemCount--;
        System.out.println("rsl " + tmp.getItem());
        return tmp.getItem();
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