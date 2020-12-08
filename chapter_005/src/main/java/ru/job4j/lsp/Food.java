package ru.job4j.lsp;

import java.util.*;

public class Food {
    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private int price;
    private int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, int price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return price == food.price
                && discount == food.discount
                && name.equals(food.name)
                && expiryDate.equals(food.expiryDate)
                && createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}