package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Find {
    public static List<Path> dataSearch(String directory, String type, String findType) throws IOException {
        SearchFiles searcher = null;

        if (findType.equals("r")) {
            searcher = new SearchFiles(p -> p.getFileName().toString().matches(type)); //  "^(A.+)"
        } else if (findType.equals("f")) {
            searcher = new SearchFiles(p -> p.getFileName().toString().equals(type));
        } else if (findType.equals("m")) {
            searcher = new SearchFiles(p -> p.getFileName().toString().contains(type));
        }
        Files.walkFileTree(Paths.get(directory), searcher);
        return searcher.getList();
    }

    public void writeFile(List<Path> listPaths, String name) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                        new FileOutputStream(name)))) {
            for (Path path : listPaths) {
                out.write(path.getFileName() + System.lineSeparator());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsFind argFind = new ArgsFind(args);
        if (argFind.valid()) {
            List<Path> listPaths = dataSearch(argFind.directory(), argFind.name(), argFind.findType());
            new Find().writeFile(listPaths, argFind.output());
        }
    }
}
