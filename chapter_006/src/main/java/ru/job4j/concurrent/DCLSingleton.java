package ru.job4j.concurrent;

public final class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                    System.out.println(inst);
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

    public static void main(String[] args) {
        DCLSingleton.instOf();
    }
}
