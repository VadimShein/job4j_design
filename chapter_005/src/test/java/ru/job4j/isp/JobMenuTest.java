package ru.job4j.isp;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class JobMenuTest {
    @Test
    public void whenUnOrderedOut() {
        StubAction act1 = new StubAction("task 1");
        StubAction act11 = new StubAction("task 1.1");
        StubAction act111 = new StubAction("task 1.1.1");
        StubAction act12 = new StubAction("task 1.2");
        JobMenu menu = new JobMenu("A", act1);
        menu.add("A", "B", act11);
        menu.add("B", "C", act111);
        menu.add("A", "D", act12);
        String out = menu.unOrdered();
        String expect = String.format(
                "%s%n%s%n%s%n%s%n",
                "task 1",
                "- task 1.1",
                "-- task 1.1.1",
                "- task 1.2"
        );
        System.out.println(out);
        Assert.assertEquals(expect, out);
    }

    @Test
    public void whenMadeAction() {
        StubAction stub = new StubAction("task");
        JobMenu menu = new JobMenu("A", stub);
        menu.add("A", "B", stub);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        menu.get("B").act();
        String expect = ("action starting\r\n");
        Assert.assertEquals(expect, out.toString());
    }
}
