package ru.job4j.concurrent;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        first.start();
        second.start();
        Thread.sleep(100);
        if (first.getState() == Thread.State.TERMINATED & second.getState() == Thread.State.TERMINATED) {
            System.out.println("job is finished");
        }
    }
}