package ru.job4j.it;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


    public class StoreTest {
        @Test
        public void addInMemStore() {
            MemStore<User> store = new MemStore<>();
            store.add(new User("000"));
            assertThat(store.findById("000").getId(), is("000"));
        }

        @Test
        public void replaceInMemStore() {
            MemStore<User> store = new MemStore<>();
            store.add(new User("000"));
            store.replace("000", new User("123"));
            assertThat(store.findById("123").getId(), is("123"));
            Assert.assertNull(store.findById("000"));
        }

        @Test
        public void deleteInMemStore() {
            MemStore<User> store = new MemStore<>();
            store.add(new User("000"));
            store.delete("000");
            Assert.assertNull(store.findById("000"));
        }

        @Test
        public void addInUserStore() {
            UserStore store = new UserStore();
            store.add(new User("000"));
            assertThat(store.findById("000").getId(), is("000"));
        }

        @Test
        public void replaceInUserStore() {
            UserStore store = new UserStore();
            store.add(new User("000"));
            store.replace("000", new User("123"));
            assertThat(store.findById("123").getId(), is("123"));
            Assert.assertNull(store.findById("000"));
        }

        @Test
        public void deleteInUserStore() {
            UserStore store = new UserStore();
            store.add(new User("000"));
            store.delete("000");
            Assert.assertNull(store.findById("000"));
        }
    }



