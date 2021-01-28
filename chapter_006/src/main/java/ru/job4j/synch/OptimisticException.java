package ru.job4j.synch;

public class OptimisticException extends RuntimeException {
    public void getException() {
        throw new RuntimeException("Throw Exception in Thread");
    }
}