package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class JobMenu implements Menu {
    List<Item> items = new ArrayList<>();

    @Override
    public void print() {
        if (items.size() > 0) {
            for (Item it : items) {
                it.print();
            }
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItem(int index) {
        return items.get(index);
    }
}
