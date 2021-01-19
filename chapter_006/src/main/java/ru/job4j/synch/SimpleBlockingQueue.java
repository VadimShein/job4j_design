package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final Object monitor = this;
    private int max;

    public SimpleBlockingQueue(int max) {
        this.max = max;
    }

    public synchronized void offer(T value) {
            while (queue.size() == max) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.add(value);
            monitor.notifyAll();
    }

    public synchronized T poll() {
        T element;
            while (queue.size() == 0) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            element = queue.poll();
            monitor.notifyAll();
        return element;
    }
}
