package ru.job4j.isp;

public interface Menu {
    void print();
    void addItem(Item item);
    Item getItem(int index);

}
