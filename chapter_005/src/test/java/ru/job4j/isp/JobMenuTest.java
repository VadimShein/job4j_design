package ru.job4j.isp;

import org.junit.Test;

public class JobMenuTest {

    @Test
    public void createMenu() {
        Menu menu = new JobMenu();
        Item task1 = new JobItem("task 1");
        Item task11 = new JobItem("task 1.1");
        Item task111 = new JobItem("task 1.1.1");
        Item task12 = new JobItem("task 1.2");
        Item task2 = new JobItem("task 2");

        task11.addSubItem(task111);
        task1.addSubItem(task11);
        task1.addSubItem(task12);
        menu.addItem(task1);
        menu.addItem(task2);

        menu.print();
    }
}
