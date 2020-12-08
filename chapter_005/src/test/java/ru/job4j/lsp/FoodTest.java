package ru.job4j.lsp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FoodTest {
    @Test
    public void whenSort() {
        Food apple = new Food("apple", new GregorianCalendar(2022, Calendar.JANUARY, 25), new GregorianCalendar(2019, Calendar.JANUARY, 25), 100, 0);
        Food milk = new Food("milk", new GregorianCalendar(2020, Calendar.DECEMBER, 1), new GregorianCalendar(2020, Calendar.NOVEMBER, 20), 100, 0);
        Food tomatoes = new Food("tomatoes", new GregorianCalendar(2023, Calendar.MAY, 25), new GregorianCalendar(2020, Calendar.MAY, 25), 100, 0);

        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();

        ControlQuality control = new ControlQuality();
        control.addStorage(warehouse);
        control.addStorage(shop);
        control.addStorage(trash);

        control.distribute(apple);
        control.distribute(milk);
        control.distribute(tomatoes);

        assertThat(warehouse.clear().get(0).getName(), is("tomatoes"));
        assertThat(shop.clear().get(0).getName(), is("apple"));
        assertThat(trash.clear().get(0).getName(), is("milk"));
    }

    @Test
    public void whenResort() {
        Food apple = new Food("apple", new GregorianCalendar(2022, Calendar.JANUARY, 25), new GregorianCalendar(2019, Calendar.JANUARY, 25), 100, 0);
        Food milk = new Food("milk", new GregorianCalendar(2020, Calendar.DECEMBER, 1), new GregorianCalendar(2020, Calendar.NOVEMBER, 20), 100, 0);
        Food tomatoes = new Food("tomatoes", new GregorianCalendar(2023, Calendar.MAY, 25), new GregorianCalendar(2020, Calendar.MAY, 25), 100, 0);

        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();

        ControlQuality control = new ControlQuality();
        control.addStorage(warehouse);
        control.addStorage(shop);
        control.addStorage(trash);

        control.distribute(apple);
        control.distribute(milk);
        control.distribute(tomatoes);

        control.resort();
        assertThat(warehouse.clear().get(0).getName(), is("tomatoes"));
        assertThat(shop.clear().get(0).getName(), is("apple"));
        assertThat(trash.clear().get(0).getName(), is("milk"));
    }
}
