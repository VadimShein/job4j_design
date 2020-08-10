package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void whenIdenticalFiles() throws IOException {
        Analyze analyze = new Analyze();
        analyze.unavailable("./src/main/java/ru/job4j/io/data/serverLog",
                "./src/main/java/ru/job4j/io/data/unavailable.csv");
        Path actual = Path.of("./src/main/java/ru/job4j/io/data/unavailable.csv");
        Path expected = Path.of("./src/main/java/ru/job4j/io/data/unavailableTestFile.csv");
        assertThat(Files.mismatch(actual, expected), is(-1L));
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenUsedTemp() throws IOException {
        Analyze analyze = new Analyze();
        analyze.unavailable("./src/main/java/ru/job4j/io/data/serverLog",
                "./src/main/java/ru/job4j/io/data/unavailable.csv");

        File source = folder.newFile("unavailable.csv");
        try (BufferedReader in = new BufferedReader(new FileReader("./src/main/java/ru/job4j/io/data/unavailable.csv"))) {
            String line;
            try (PrintWriter out = new PrintWriter(source)) {
                while ((line = in.readLine()) != null) {
                    out.println(line);
                }
            }
        }

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(), is("10:57:01;10:59:01;"
                + "11:01:02;11:02:02;"
                + "12:00:00;12:10:00;"));
    }
}
