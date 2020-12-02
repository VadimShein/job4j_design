package ru.job4j.parking;

public class FreightCar implements Car {
    private String name;
    private final int size;

    public FreightCar(String name, int size) {
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
