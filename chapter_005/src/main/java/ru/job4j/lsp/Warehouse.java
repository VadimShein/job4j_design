package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Warehouse implements Storage {
    private List<Food> storage = new ArrayList<>();

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return shelfLife(food) > 75;
    }

    @Override
    public List<Food> clear() {
        List<Food> temp = new ArrayList<>(storage);
        storage.clear();
        return temp;
    }

    private long shelfLife(Food food) {
        return (food.getExpiryDate().getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) * 100
                / (food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis());
    }
}
