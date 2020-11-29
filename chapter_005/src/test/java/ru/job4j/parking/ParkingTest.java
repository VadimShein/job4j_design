package ru.job4j.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    @Test
    public void whenOnePassengerCarAndOneFreightCar() {
        Parking parking = new CarParking(4, 2);
        Car c1 = new PassengerCar("pc1", "passenger");
        Car c2 = new FreightCar("fc1", "freight");

        parking.addCar(c1);
        parking.addCar(c2);


        assertThat(parking.getPassengerCarCount(), is(1));
        assertThat(parking.getFreightCarCount(), is(1));
    }

    @Test
    public void whetThreeFreightCar() {
        Parking parking = new CarParking(4, 2);
        Car c1 = new FreightCar("fc1", "freight");
        Car c2 = new FreightCar("fc2", "freight");
        Car c3 = new FreightCar("fc3", "freight");

        parking.addCar(c1);
        parking.addCar(c2);
        parking.addCar(c3);

        assertThat(parking.getFreightCarCount(), is(3));
        assertThat(parking.getFreeFreightSeat(), is(0));
        assertThat(parking.getFreePassengerSeat(), is(2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whetException() {
        Parking parking = new CarParking(1, 2);
        Car c1 = new PassengerCar("pc1", "passenger");
        Car c2 = new PassengerCar("pc2", "passenger");

        parking.addCar(c1);
        parking.addCar(c2);
    }
}
