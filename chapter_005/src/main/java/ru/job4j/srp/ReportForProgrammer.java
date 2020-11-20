package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportForProgrammer implements Report {
    private Store store;

    public ReportForProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String report(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<Employee>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</Employee>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
