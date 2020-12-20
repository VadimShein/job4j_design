package ru.job4j.concurrent;

public class ConcurrentOutput {

    public static void main(String[] args) {
        Thread firstThread = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );

        Thread secondThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
        );

        firstThread.start();
        secondThread.start();
        System.out.println(Thread.currentThread().getName());

    }
}