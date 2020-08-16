package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {

    private final Map<String, String> values = new HashMap<>();

    public ArgZip(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args is empty");
        }
        for (String arg : args) {
            String[] splitArg = arg.replaceFirst("-", "").split("=");
            values.put(splitArg[0], splitArg[1]);
        }
    }

    public boolean valid() {
        return values.get("d") != null && values.get("e") != null && values.get("o") != null;
    }

    public String directory() {
        return values.get("d");
    }

    public String exclude() {
        return values.get("e");
    }

    public String output() {
        return values.get("o");
    }
}