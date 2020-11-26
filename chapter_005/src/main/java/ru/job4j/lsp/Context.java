package ru.job4j.lsp;

import java.text.ParseException;

public class Context {
    private Control control;

    public Context(Control control) {
        this.control = control;
    }

    public void execute() throws ParseException {
        control.sort();
    }
}
