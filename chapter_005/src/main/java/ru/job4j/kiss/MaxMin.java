package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator);
    }

    public <T> T compare(List<T> value, Comparator<T> comparator) {
        T current = null;
        if (value.size() != 0) {
            current = value.get(0);
            for (T val : value) {
                if (comparator.compare(current, val) < 0) {
                    current = val;
                }
            }
        }
        return current;
    }

    public static void main(String[] args) {
        List<Integer> listInt = List.of(3, 5, -10, 0, 5, 1, 2);

        Comparator<Integer> compMax = (o1, o2) -> o1.compareTo(o2);
        Comparator<Integer> compMin = (o1, o2) -> o2.compareTo(o1);

        MaxMin search = new MaxMin();
        System.out.println("max " + search.max(listInt, compMax));
        System.out.println("min " + search.min(listInt, compMin));
    }
}
