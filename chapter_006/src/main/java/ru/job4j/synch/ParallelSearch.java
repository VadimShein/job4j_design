package ru.job4j.synch;


import java.util.concurrent.CountDownLatch;

public class ParallelSearch {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);

        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                            countDownLatch.countDown();
                            Thread.sleep(10);
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

        consumer.start();

        try {
            countDownLatch.await();
            consumer.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}