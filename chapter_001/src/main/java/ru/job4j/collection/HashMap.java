package ru.job4j.collection;

import java.util.*;

public class HashMap<K, V> implements Iterable<V> {
    private int tableLength = 4;
    transient NodeMap<K, V>[] container = new NodeMap[tableLength];
    private int itemCount = 0;


    public V get(K key) {
        if (this.container[tablePosition(key)] == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.container[tablePosition(key)].getValue();
    }

    private int tablePosition(K key) {
        return (key == null) ? 0 : key.hashCode() & (tableLength - 1);
    }

    public boolean insert(K key, V model) {
        boolean rsl = true;
        NodeMap<K, V>  map = new NodeMap<>(key.hashCode(), key, model, null);
        if (this.container[tablePosition(key)] != null) {
            rsl = false;
        } else {
            this.container[tablePosition(key)] = map;
            this.itemCount++;
        }
        extensionArray();
        return rsl;
    }

    public void delete(K key) {
        this.container[tablePosition(key)] = null;
        this.itemCount--;
    }

    @Override
    public String toString() {
        return "HashMap{" + "container=" + Arrays.toString(container) + '}';
    }

    private void extensionArray() {
        if (this.itemCount >= this.tableLength * 0.75f) {
            tableLength = tableLength * 2;
            NodeMap<K, V>[] newContainer = new NodeMap[tableLength];
            for (NodeMap<K, V> cell : container) {
                if (cell != null) {
                    newContainer[tablePosition(cell.getKey())] = new NodeMap<>(cell.getKey().hashCode(), cell.getKey(), cell.getValue(), null);
                }
            }
            this.container = newContainer;
        }
    }

    @Override
    public Iterator<V> iterator() {
        int expectedModCount = this.itemCount;

        return new Iterator<>() {
            private int index = 0;
            private int position = 0;
            @Override
            public boolean hasNext() {
                return index < itemCount;
            }

            @Override
            public V next() {
                if (expectedModCount != itemCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (container[position] == null) {
                    position++;
                }
                V rsl = container[position++].getValue();
                index++;
                return rsl;
            }
        };
    }
}
