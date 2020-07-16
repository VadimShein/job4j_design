package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class HashMapTest {

    @Test
    public void whenAddThenGet() {
        HashMap<Integer, String> map = new HashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        assertThat(map.get(1), is("first"));
        assertThat(map.get(2), is("second"));
    }

    @Test
    public void whenAddThenGetAfterExtension() {
        HashMap<Integer, String> map = new HashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        assertThat(map.get(1), is("first"));
        assertThat(map.get(2), is("second"));
        assertThat(map.get(3), is("third"));
    }

    @Test
    public void whenAddThenIterate() {
        HashMap<Integer, String> map = new HashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.insert(3, "third");
        Iterator<String> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.next(), is("third"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddThenDelete() {
        HashMap<Integer, String> map = new HashMap<>();
        map.insert(1, "first");
        map.insert(2, "second");
        map.delete(2);
        assertThat(map.get(1), is("first"));
        assertNull(map.get(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        HashMap<Integer, String> map = new HashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        HashMap<Integer, String> map = new HashMap<>();
        map.insert(1, "first");
        Iterator<String> it = map.iterator();
        map.insert(2, "second");
        it.next();
    }
}
