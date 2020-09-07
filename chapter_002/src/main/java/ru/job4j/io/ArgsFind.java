package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsFind {
        private final Map<String, String> values = new HashMap<>();

        public ArgsFind(String[] args) {
            if (args.length == 0) {
                throw new IllegalArgumentException("args is empty");
            }
            for (String arg : args) {
                String[] splitArg = arg.replaceFirst("-", "").split("=");
                values.put(splitArg[0], splitArg[1]);
            }
        }

        public boolean valid() {
            return values.get("d") != null && values.get("n") != null && values.get("o") != null && values.get("f") != null;
        }

        public String directory() {
            return values.get("d");
        }

        public String name() {
            return values.get("n");
        }

    public String findType() {
            if (!values.get("f").equals("r") & !values.get("f").equals("m") & !values.get("f").equals("f")) {
                throw new IllegalArgumentException("find type (-f) must be: r | m | f");
            }
        return values.get("f");
    }

        public String output() {
            return values.get("o");
        }
}
