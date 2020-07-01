package ru.job4j.collection;

public class SimpleStack<E>  {
    private SimpleList<E> linked = new SimpleList<>();

    public E pop() {
        return linked.deleteLast();
    }

    public void push(E value) {
        linked.add(value);
    }

    public boolean isEmpty() {
        return linked.isEmpty();
    }
}