package ru.job4j.lsp;

import java.util.Calendar;
import java.util.List;

public class ControlQuality implements Control {
    List<Food> storage;
    Warehouse warehouse = new Warehouse();
    Trash trash = new Trash();
    Shop shop = new Shop();

    public ControlQuality(List<Food> storage) {
        this.storage = storage;
    }

    private long shelfLife(Food food) {
        return (food.getExpiryDate().getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) * 100
                / (food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis());
    }

    @Override
    public void sort() {
        for (Food food : storage) {
            if (shelfLife(food) < 25) {
                warehouse.add(food);
            } else if (shelfLife(food) > 75) {
                trash.add(food);
            } else {
                shop.add(food);
            }
        }
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Trash getTrash() {
        return trash;
    }

    public Shop getShop() {
        return shop;
    }
}
