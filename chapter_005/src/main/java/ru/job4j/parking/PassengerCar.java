package ru.job4j.parking;

public class PassengerCar implements Car {
    private String name;
    private int size;

    public PassengerCar(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }
}
