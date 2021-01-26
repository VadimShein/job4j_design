package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
class CASCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>();
    Integer ref;
    Integer cur;

    public void increment() {
        if (ref != null) {
            ref = count.get();
        } else {
            ref = 0;
        }
        do {
            cur = ref + 1;
        } while (count.compareAndSet(ref, cur));
            count.set(cur);
    }

    public int get() {
        ref = count.get();
        if (ref == null) {
            throw new IllegalStateException("Count is empty");
        }
        return ref;
    }

    public static void main(String[] args) {
        CASCount<Integer> count = new CASCount<>();
        count.increment();
        System.out.println(count.get());
        count.increment();
        System.out.println(count.get());
    }
}
