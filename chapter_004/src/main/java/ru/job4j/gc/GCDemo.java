package ru.job4j.gc;

public class GCDemo {
    private static final long B = 1;
    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / KB);
        System.out.printf("Total: %d%n", totalMemory / KB);
        System.out.printf("Max: %d%n", maxMemory / KB);
    }

    public static void main(String[] args) {
   //     System.gc();
        info();
        for (int i = 0; i < 5400; i++) {
            new User(i, "N" + i);
        }
     //  System.gc();
        info();
    }
}
