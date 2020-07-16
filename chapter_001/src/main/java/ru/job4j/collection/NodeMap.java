package ru.job4j.collection;

import java.util.Objects;

public class NodeMap<K, V> {
    final int hash;
    private K key;
    private V value;
    private NodeMap<K, V> next;

    NodeMap(int hash, K key, V value, NodeMap<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public NodeMap<K, V> getNext() {
        return next;
    }

    public void setNext(NodeMap<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeMap<?, ?> nodeMap = (NodeMap<?, ?>) o;
        return hash == nodeMap.hash
                && Objects.equals(key, nodeMap.key)
                && Objects.equals(value, nodeMap.value)
                && Objects.equals(next, nodeMap.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, key, value, next);
    }

    @Override
    public String toString() {
        return "NodeMap{" + "hash=" + hash + ", key=" + key
                + ", value=" + value + ", next=" + next + '}';
    }
}
