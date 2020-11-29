package ru.job4j.parking;

public interface Parking {
     void addCar(Car car);
     int getPassengerCarCount();
     int getFreightCarCount();
     int getFreePassengerSeat();
     int getFreeFreightSeat();
}
