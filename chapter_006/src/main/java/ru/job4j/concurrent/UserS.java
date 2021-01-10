package ru.job4j.concurrent;

public class UserS {
    private int id;
    private int amount;

    public static UserS of(int id, int amount) {
        UserS user = new UserS();
        user.id = id;
        user.amount = amount;
        return user;
    }

    public  int getId() {
        return id;
    }

    public  int getAmount() {
        return amount;
    }

    public  void setAmount(int amount) {
        this.amount = amount;
    }
}
