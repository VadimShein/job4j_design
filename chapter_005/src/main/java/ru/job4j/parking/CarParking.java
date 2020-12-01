package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private int maxPassengerCar;
    private int maxFreightCar;
    private int passengerCarCount = 0;
    private int freightCarCount = 0;

    List<Car> cars = new ArrayList<>();

    public CarParking(int maxPassengerCar, int maxFreightCar) {
        this.maxPassengerCar = maxPassengerCar;
        this.maxFreightCar = maxFreightCar;
    }

    public void addCar(Car car) {
        if (car.getSize() == 1 & maxPassengerCar > 0) {
            cars.add(car);
            passengerCarCount++;
            maxPassengerCar--;
        } else if (car.getSize() == 2 & maxFreightCar > 0) {
            cars.add(car);
            freightCarCount++;
            maxFreightCar--;
        } else if (car.getSize() == 2 & maxPassengerCar > 1) {
            cars.add(car);
            freightCarCount++;
            maxPassengerCar -= 2;
        } else {
            throw new IllegalArgumentException("Free seat wasn't find");
        }
    }

    @Override
    public int getPassengerCarCount() {
        return passengerCarCount;
    }

    @Override
    public int getFreightCarCount() {
        return freightCarCount;
    }

    @Override
    public int getFreePassengerSeat() {
        return maxPassengerCar;
    }

    @Override
    public int getFreeFreightSeat() {
        return maxFreightCar;
    }
}
