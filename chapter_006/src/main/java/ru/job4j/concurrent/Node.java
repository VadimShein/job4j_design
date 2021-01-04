package ru.job4j.concurrent;

public class Node<T> {
    private Node<T> next;
    private T value;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> setNext(Node<T> next) {
        T value = this.value;
        return new Node<>(value, next);
    }

    public T getValue() {
        return value;
    }

    public Node<T> setValue(T value) {
        Node<T> next = this.next;
        return new Node<>(value, next);
    }
}
