package ru.job4j.synch;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

public class SimpleBlockingQueueTest {

    @Test
    public void addThenPollWithOneThread() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
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
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
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
}
