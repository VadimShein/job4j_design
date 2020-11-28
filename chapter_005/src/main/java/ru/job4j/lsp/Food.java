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

    public static void main(String[] args)  {
        Food apple = new Food("apple", new GregorianCalendar(2022, Calendar.JANUARY, 25), new GregorianCalendar(2019, Calendar.JANUARY, 25), 100, 0);
        Food milk = new Food("milk", new GregorianCalendar(2020, Calendar.DECEMBER, 1), new GregorianCalendar(2020, Calendar.NOVEMBER, 20), 100, 0);
        Food tomatoes = new Food("tomatoes", new GregorianCalendar(2023, Calendar.MAY, 25), new GregorianCalendar(2020, Calendar.MAY, 25), 100, 0);

        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();

        ControlQuality control = new ControlQuality();
        control.addStorage(warehouse);
        control.addStorage(shop);
        control.addStorage(trash);

        control.distribute(apple);
        control.distribute(milk);
        control.distribute(tomatoes);


        System.out.println(warehouse.clear().get(0).getName());
        System.out.println(shop.clear().get(0).getName());
        System.out.println(trash.clear().get(0).getName());
    }
}