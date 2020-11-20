package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportForAccountant implements Report {
    private Store store;

    public ReportForAccountant(Store store) {
        this.store = store;
    }

    @Override
    public String report(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(Math.round(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
