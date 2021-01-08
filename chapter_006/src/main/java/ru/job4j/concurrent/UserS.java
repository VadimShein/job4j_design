package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserS {
    @GuardedBy("this")
    private int id;
    @GuardedBy("this")
    private int amount;

    public synchronized static UserS of(int id, int amount) {
        UserS user = new UserS();
        user.id = id;
        user.amount = amount;
        return user;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized int getAmount() {
        return amount;
    }

    public synchronized void setAmount(int amount) {
        this.amount = amount;
    }
}
