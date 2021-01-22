package ru.job4j.synch;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class SimpleBlockingQueueTest {

    @Test
    public void addThenPollWithOneThread() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Set<Integer> rsl = new TreeSet<>();

        Thread producer = new Thread(
                () -> queue.offer(1)
        );
        Thread consumer = new Thread(
                () -> rsl.add(queue.poll())
        );
        producer.start();
        consumer.start();
        consumer.join();
        assertThat(rsl, is(Set.of(1)));
    }

    @Test
    public void addThenPollWithThreeThread() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Set<Integer> rsl = new TreeSet<>();

        Thread producer = new Thread(
                () -> {
                    queue.offer(1);
                    queue.offer(2);
                    queue.offer(3);
                }
        );
        Thread consumerOne = new Thread(
                () -> rsl.add(queue.poll())
        );

        Thread consumerTwo = new Thread(
                () -> rsl.add(queue.poll())
        );

        Thread consumerThree = new Thread(
                () -> rsl.add(queue.poll())
        );

        producer.start();
        consumerOne.start();
        consumerOne.join();
        consumerTwo.start();
        consumerTwo.join();
        consumerThree.start();
        consumerThree.join();
        assertThat(rsl, is(Set.of(1, 2, 3)));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);

        Thread producer = new Thread(() -> IntStream.range(0, 5).forEach(queue::offer));
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}
