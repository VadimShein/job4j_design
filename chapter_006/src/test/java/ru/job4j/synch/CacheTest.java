package ru.job4j.synch;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

public class CacheTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @Test
    public void whenAddAndUpdate() throws InterruptedException {
        Cache cache = new Cache();
        Thread add = new Thread(() -> cache.add(new Base(1)));
        add.start();
        add.join();

        Thread update = new Thread(() -> cache.update(new Base(1)));
        update.start();
        update.join();

        Assert.assertThat(cache.get(1).getVersion(), is(1));
    }
}
