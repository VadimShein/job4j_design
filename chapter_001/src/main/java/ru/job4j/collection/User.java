package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User first = new User("User1", 10, new GregorianCalendar(2000, Calendar.DECEMBER, 31));
        User second = new User("User1", 10, new GregorianCalendar(2000, Calendar.DECEMBER, 31));
        HashMap<User, Object> map = new HashMap<>();
        map.put(first, "first user");
        map.put(second, "second user");
        System.out.println(map);
    }
}


