package ru.job4j.multithreads;

public class MasterSlaveBarrier {
    private final Object monitor = this;
    private volatile boolean masterRun = true;

    public void tryMaster() {
        synchronized (monitor) {
            while (!masterRun) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void trySlave() {
        synchronized (monitor) {
            while (masterRun) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doneMaster() {
        synchronized (monitor) {
            masterRun = false;
            monitor.notifyAll();
        }
    }

    public void doneSlave() {
        synchronized (monitor) {
            masterRun = true;
            monitor.notifyAll();
        }
    }
}