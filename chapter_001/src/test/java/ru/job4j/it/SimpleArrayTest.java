package ru.job4j.it;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    @Test
    public void addInArray() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(3);
        simple.add(11);
        simple.add(22);
        simple.set(1, 2);
        assertThat(simple.get(1), is(2));
    }

    @Test
    public void removeInArray() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(3);
        simple.add(11);
        simple.add(22);
        simple.add(33);
        simple.remove(0);
        assertThat(simple.get(1), is(33));
        Assert.assertNull(simple.get(2));
    }

    @Test
    public void emptyArray() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(3);
        Iterator<Integer> iterator = simple.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void exeptionInArray() {
        SimpleArray<Integer> simple = new SimpleArray<Integer>(3);
        simple.add(11);
        simple.get(1);
    }

    @Test
    public void stringIterator() {
        SimpleArray<String> simple = new SimpleArray<String>(3);
        simple.add("string1");
        simple.add("string2");
        Iterator<String> iterator = simple.iterator();
        iterator.next();
        assertThat(iterator.next(), is("string2"));
    }
}
