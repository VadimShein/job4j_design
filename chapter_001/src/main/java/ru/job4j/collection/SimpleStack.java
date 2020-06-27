package ru.job4j.collection;

public class SimpleStack<E>  {
    private SimpleList<E> linked = new SimpleList<>();

    public E pop() {
        E rsl = linked.getLast();
        linked.deleteLast();
        return rsl;
    }

    public void push(E value) {
        linked.add(value);
    }
}