package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;
import ru.job4j.collection.SimpleList;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    private final SimpleList<T> list = new SimpleList<>();
    @GuardedBy("this")
    public synchronized void add(T value) {
        list.add(value);
    }
    @GuardedBy("this")
    public synchronized T get(int index) {
        return list.get(index);
    }

    private SimpleList<T> copy(SimpleList<T> list) {
        SimpleList<T> tmpList = new SimpleList<>();
        for (T item : list) {
            tmpList.add(item);
        }
        return tmpList;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }
}
