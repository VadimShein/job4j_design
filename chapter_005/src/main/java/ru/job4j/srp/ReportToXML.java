package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportToXML implements Report {
    private Store store;

    public ReportToXML(Store store) {
        this.store = store;
    }

    @Override
    public String report(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append("<Employee>").append(System.lineSeparator())
                    .append("\t<Name>").append(employee.getName()).append("</Name>").append(System.lineSeparator())
                    .append("\t<Hired>").append(employee.getHired()).append("</Hired>").append(System.lineSeparator())
                    .append("\t<Fired>").append(employee.getFired()).append("</Fired>").append(System.lineSeparator())
                    .append("\t<Salary>").append(employee.getSalary()).append("</Salary>").append(System.lineSeparator())
                    .append("</Employee>").append(System.lineSeparator());
        }
        return text.toString();
    }
}
