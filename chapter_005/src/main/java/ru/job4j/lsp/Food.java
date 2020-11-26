package ru.job4j.lsp;

import java.text.ParseException;
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

    public static void main(String[] args) throws ParseException {
        List<Food> storage = new ArrayList<>();
        Food apple = new Food("apple", new GregorianCalendar(2021, Calendar.JANUARY, 25), new GregorianCalendar(2019, Calendar.JANUARY, 25), 100, 0);
        Food milk = new Food("milk", new GregorianCalendar(2020, Calendar.NOVEMBER, 30), new GregorianCalendar(2020, Calendar.NOVEMBER, 25), 100, 0);
        Food tomatoes = new Food("tomatoes", new GregorianCalendar(2023, Calendar.MAY, 25), new GregorianCalendar(2020, Calendar.MAY, 25), 100, 0);
        storage.add(apple);
        storage.add(milk);
        storage.add(tomatoes);

        ControlQuality cq = new ControlQuality(storage);
        Context context = new Context(cq);
        context.execute();

        System.out.println("Shop: " + cq.getShop().getShopList().get(0).getName());
        System.out.println("Warehouse: " + cq.getWarehouse().getWarehouseList().get(0).getName());
        System.out.println("Trash: " + cq.getTrash().getTrashList().get(0).getName());
    }
}
