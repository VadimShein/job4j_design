package ru.job4j.synch;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<PThread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            threads.add(new PThread(tasks));
        }
        for (PThread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for (PThread thread : threads) {
            thread.stopThread();
        }
    }

    static class PThread extends Thread {
        private final SimpleBlockingQueue<Runnable> tasks;
        private boolean isStopped = false;

        public PThread(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }
        public void run() {
            while (!isStopped) {
                tasks.poll().run();
            }
        }
        public synchronized void stopThread() {
            isStopped = true;
        }
    }

        public static void main(String[] args) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            ThreadPool tp = new ThreadPool();
            for (int i = 0; i < 8; i++) {
                tp.work(task);
            }
//       tp.shutdown();
        }
    }




