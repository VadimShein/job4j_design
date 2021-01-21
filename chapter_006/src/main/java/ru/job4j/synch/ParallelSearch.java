package ru.job4j.synch;


import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearch {
    public static void main(String[] args) {
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        final AtomicBoolean flag = new AtomicBoolean(false);

        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            if (flag.get()) {
                                Thread.currentThread().interrupt();
                            } else {
                                System.out.println(queue.poll());
                                Thread.sleep(200);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

        final Thread check = new Thread(
                () -> {
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (!flag.get()) {
                        if (consumer.getState() == Thread.State.WAITING) {
                            flag.set(true);
                        }
                    }
                }
        );
        consumer.start();
        check.start();
    }
}