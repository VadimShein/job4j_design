package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    SimpleList<E> list = new SimpleList<>();

    public void add(E model) {
    Iterator<E> it = list.iterator();
        boolean access = true;
        while (it.hasNext()) {
            if (it.next().equals(model)) {
                access = false;
                break;
            }
        }
        if (access) {
            list.add(model);
        }
    }

    @Override
    public Iterator<E> iterator() {
       return list.iterator();
    }
}
