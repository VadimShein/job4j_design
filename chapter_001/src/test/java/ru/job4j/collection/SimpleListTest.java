package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleListTest {
    @Test
    public void whenAddThenGet() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.add("second");
        assertThat(list.get(0), is("first"));
        assertThat(list.get(1), is("second"));
    }

    @Test
    public void whenAddThenNext() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.add("second");
        Iterator<String> iterator = list.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoSuchExeption() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        Iterator<String> iterator = list.iterator();
        list.add("second");
        iterator.next();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsException() {
        SimpleList<String> list = new SimpleList<>();
        list.add("first");
        list.get(1);
    }


}
