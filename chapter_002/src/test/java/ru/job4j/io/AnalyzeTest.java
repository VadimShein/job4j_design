package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void whenPairWithoutComment() throws IOException {
        Analyze analyze = new Analyze();
        analyze.unavailable("C://projects/job4j_design/chapter_002/src/main/java/ru/job4j/io/data/serverLog",
                "C://projects/job4j_design/chapter_002/src/main/java/ru/job4j/io/data/unavailable.csv");
        Path actual = Path.of("C:/projects/job4j_design/chapter_002/src//main//java/ru/job4j/io/data/unavailable.csv");
        Path expected = Path.of("C:/projects//job4j_design/chapter_002/src/main/java/ru/job4j//io/data/unavailableTestFile.csv");
        assertThat(Files.mismatch(actual, expected), is(-1L));
    }
}
