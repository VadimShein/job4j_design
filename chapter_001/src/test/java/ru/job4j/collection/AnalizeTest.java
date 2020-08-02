package ru.job4j.collection;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Test
        public void whenAddAndDelete() {
            Analize analize = new Analize();
            List<Analize.User> previous = List.of(new Analize.User(1, "name1"),
                    new Analize.User(2, "name2"));
            List<Analize.User> current = List.of(new Analize.User(1, "name1"),
                    new Analize.User(3, "name3"));

            Analize.Info info = analize.diff(previous, current);
            assertThat(info.getAdded(), is(1));
            assertThat(info.getDeleted(), is(1));
            assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenChange() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(1, "name1"),
                new Analize.User(2, "name2"));
        List<Analize.User> current = List.of(new Analize.User(2, "name3"),
                new Analize.User(1, "name1"));

        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(1));
    }

    @Test
    public void whenEqualsId() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(1, "name1"),
                new Analize.User(1, "name1"));
        List<Analize.User> current = List.of(new Analize.User(2, "name2"),
                new Analize.User(1, "name1"));

        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getAdded(), is(1));
        assertThat(info.getDeleted(), is(0));
        assertThat(info.getChanged(), is(0));
    }

    @Test
    public void whenNoMatches() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(new Analize.User(1, "name1"),
                new Analize.User(2, "name2"));
        List<Analize.User> current = List.of(new Analize.User(3, "name3"),
                new Analize.User(4, "name4"));

        Analize.Info info = analize.diff(previous, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getDeleted(), is(2));
        assertThat(info.getChanged(), is(0));
    }
}
