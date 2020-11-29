package ru.job4j.parking;

public class FreightCar implements Car {
    private String name;
    private String type;

    public FreightCar(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }
}
