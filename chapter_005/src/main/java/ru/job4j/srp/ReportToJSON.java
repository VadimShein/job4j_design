package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportToJSON implements Report {
    private Store store;

    public ReportToJSON(Store store) {
        this.store = store;
    }

    @Override
    public String report(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
             for (Employee employee : store.findBy(filter)) {
                 text.append("{").append(System.lineSeparator())
                         .append("\t\"Name\": \"").append(employee.getName()).append("\",").append(System.lineSeparator())
                         .append("\t\"Hired\": \"").append(employee.getHired()).append("\",").append(System.lineSeparator())
                         .append("\t\"Fired\": \"").append(employee.getFired()).append("\",").append(System.lineSeparator())
                         .append("\t\"Salary\": \"").append(employee.getSalary()).append("\"").append(System.lineSeparator())
                         .append("}").append(System.lineSeparator());
        }
        return text.toString();
    }
}
