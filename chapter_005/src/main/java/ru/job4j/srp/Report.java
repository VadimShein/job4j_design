package ru.job4j.srp;

import java.util.function.Predicate;

public interface Report {
    String report(Predicate<Employee> filter);
}
