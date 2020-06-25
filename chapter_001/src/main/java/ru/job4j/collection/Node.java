package ru.job4j.collection;

public class Node<E> {
    private E item;
    private Node<E>  next;
    private Node<E>  prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
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
