package ru.job4j.collection;

public class Node<E> {
    private E item;
    private Node<E>  next;

    Node(E item, Node<E> next) {
        this.item = item;
        this.next = next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getItem() {
        return item;
    }

    public Node<E> getNext() {
        return next;
    }
}
