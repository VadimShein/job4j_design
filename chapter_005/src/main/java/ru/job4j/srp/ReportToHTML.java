package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportToHTML implements Report {
    private Store store;

    public ReportToHTML(Store store) {
        this.store = store;
    }

    @Override
    public String report(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append("<html>").append(System.lineSeparator())
                    .append("<body>").append(System.lineSeparator())
                    .append("<p>").append(employee.getName()).append("</p>").append(System.lineSeparator())
                    .append("<p>").append(employee.getHired()).append("</p>").append(System.lineSeparator())
                    .append("<p>").append(employee.getFired()).append("</p>").append(System.lineSeparator())
                    .append("<p>").append(employee.getSalary()).append("</p>").append(System.lineSeparator())
                    .append("</body>").append(System.lineSeparator())
                    .append("/html").append(System.lineSeparator());
        }
        return text.toString();
    }
}
