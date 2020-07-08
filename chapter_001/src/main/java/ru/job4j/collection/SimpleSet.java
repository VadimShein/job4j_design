package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleList<E> list = new SimpleList<>();

    public void add(E model) {
        if (!contains(model)) {
            list.add(model);
        }
    }

    private boolean contains(E model) {
        Iterator<E> it = list.iterator();
        boolean contains = false;
        while (it.hasNext()) {
            if (Objects.equals(it.next(), model)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Iterator<E> iterator() {
       return list.iterator();
    }
}
