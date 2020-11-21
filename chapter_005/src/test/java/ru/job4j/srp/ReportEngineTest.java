package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateForProgrammer() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForProgrammer(store);

        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("<Employee>")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append("</Employee>")
                .append(System.lineSeparator());
        assertThat(engine.report(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateForAccountant() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForAccountant(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(Math.round(worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.report(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateForHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Employee worker2 = new Employee("Vasiliy", now, now, 200);
        store.add(worker2);
        Report engine = new ReportForHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.report(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateToXML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportToXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<Employee>").append(System.lineSeparator())
                .append("\t<Name>").append(worker.getName()).append("</Name>").append(System.lineSeparator())
                .append("\t<Hired>").append(worker.getHired()).append("</Hired>").append(System.lineSeparator())
                .append("\t<Fired>").append(worker.getFired()).append("</Fired>").append(System.lineSeparator())
                .append("\t<Salary>").append(worker.getSalary()).append("</Salary>").append(System.lineSeparator())
                .append("</Employee>").append(System.lineSeparator());
        assertThat(engine.report(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateToJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportToJSON(store);
        StringBuilder expect = new StringBuilder()
                .append("{").append(System.lineSeparator())
                .append("\t\"Name\": \"").append(worker.getName()).append("\",").append(System.lineSeparator())
                .append("\t\"Hired\": \"").append(worker.getHired()).append("\",").append(System.lineSeparator())
                .append("\t\"Fired\": \"").append(worker.getFired()).append("\",").append(System.lineSeparator())
                .append("\t\"Salary\": \"").append(worker.getSalary()).append("\"").append(System.lineSeparator())
                .append("}").append(System.lineSeparator());
        assertThat(engine.report(em -> true), is(expect.toString()));
    }

    @Test
    public void whenGenerateToHTML() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportToHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<html>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("<p>").append(worker.getName()).append("</p>").append(System.lineSeparator())
                .append("<p>").append(worker.getHired()).append("</p>").append(System.lineSeparator())
                .append("<p>").append(worker.getFired()).append("</p>").append(System.lineSeparator())
                .append("<p>").append(worker.getSalary()).append("</p>").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("/html").append(System.lineSeparator());
        assertThat(engine.report(em -> true), is(expect.toString()));
    }
}