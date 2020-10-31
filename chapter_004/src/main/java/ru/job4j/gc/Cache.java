package ru.job4j.gc;

public interface Cache<K, V> {
    V get(K key);
    V download(K key);
}
