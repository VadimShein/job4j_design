package ru.job4j.template;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Map;

public class GeneratorTest {
    @Test
    public void generatorTrueAnswerTest() {
        Generator generator = new TextGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you");
        String rsl = generator.produce(template, map);
        assertThat(rsl, is("I am a Petr Arsentev, Who are you? "));
    }

    @Test (expected = IllegalArgumentException.class)
    public void generatorIncorrectKeyExceptionTest() {
        Generator generator = new TextGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("surname", "Petr Arsentev", "subject", "you");
        String rsl = generator.produce(template, map);
    }

    @Test (expected = IllegalArgumentException.class)
    public void generatorExtraKeyExceptionTest() {
        Generator generator = new TextGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = Map.of("name", "Petr Arsentev", "subject", "you", "comment", "check it");
        String rsl = generator.produce(template, map);
    }
}
