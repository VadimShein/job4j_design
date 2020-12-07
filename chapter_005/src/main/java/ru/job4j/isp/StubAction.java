package ru.job4j.isp;

public class StubAction implements Action {
    private String name;

    public StubAction(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act() {
        System.out.println("action starting");

    }
}
