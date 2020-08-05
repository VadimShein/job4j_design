package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> node = findBy(parent);
        if (node.isPresent() && findBy(child).isEmpty()) {
            node.get().children.add(new Node<>(child));
                rsl = true;
            }
        return rsl;
    }

    public boolean sort(Node<E> el, Predicate<Node<E>> predicate) {
        return predicate.test(el);
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = i -> i.value.equals(value);
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (sort(el, predicate)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean rsl = true;
        Predicate<Node<E>> predicate = i -> i.children.size() > 2;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (sort(el, predicate)) {
                rsl = false;
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
