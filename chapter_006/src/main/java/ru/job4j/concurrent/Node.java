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

    public void setNext(Node<T> next) {
        T value = this.value;
        this.next = new Node<>(value, next).getNext();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        Node<T> next = this.next;
        this.value = new Node<>(value, next).getValue();
    }
}
