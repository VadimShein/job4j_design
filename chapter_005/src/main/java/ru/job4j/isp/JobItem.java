package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class JobItem implements Item {
    private String itemName;
    private List<Item> subItems = new ArrayList<>();
    private int count = 1;

    public JobItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public List<Item> getSubItems() {
        return subItems;
    }

    @Override
    public void addSubItem(Item subItem) {
        subItems.add(subItem);
    }

    private void tab() {
        for (int i = 0; i < count; i++) {
            System.out.print("--");
            i++;
        }
    }

    public void print() {
        System.out.print("--");
        System.out.println(itemName);

        if (subItems.size() > 0) {
            for (Item it : subItems) {
                tab();
                count++;
                 it.print();
            }
        }
    }

    @Override
    public void performAction() {
        System.out.println("made action");
    }
}
