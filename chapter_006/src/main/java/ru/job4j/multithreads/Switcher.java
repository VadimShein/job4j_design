package ru.job4j.multithreads;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier ms = new MasterSlaveBarrier();

        Thread first = new Thread(
                () -> {

                    while (true) {
                        ms.tryMaster();
                        System.out.println("Thread A");
                        ms.doneMaster();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );

        Thread second = new Thread(
                () -> {
                    while (true) {
                        ms.trySlave();
                        System.out.println("Thread B");
                        ms.doneSlave();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );

        first.start();
        second.start();
        first.join();
        second.join();
    }
}