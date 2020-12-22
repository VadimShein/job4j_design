package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            for (int i = 0; i < 10; i++) {
                                System.out.print("\rLoading: " + i + "%");
                                Thread.sleep(1000);
                                System.out.println();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(4000);
        thread.interrupt();
    }
}

