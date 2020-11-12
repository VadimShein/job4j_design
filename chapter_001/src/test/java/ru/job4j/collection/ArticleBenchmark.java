package ru.job4j.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class ArticleBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public boolean testLongText() {
        return Article.generateBy(
                "Мой дядя самых честных правил, "
                        + "Когда не в шутку занемог, "
                        + "Он уважать себя заставил "
                        + "И лучше выдумать не мог. "
                        + "Его пример другим наука; "
                        + "Но, боже мой, какая скука "
                        + "С больным сидеть и день и ночь, "
                        + "Не отходя ни шагу прочь! "
                        + "Какое низкое коварство "
                        + "Полуживого забавлять, "
                        + "Ему подушки поправлять, "
                        + "Печально подносить лекарство, "
                        + "Вздыхать и думать про себя: "
                        + "Когда же черт возьмет тебя!",
                "Мой дядя мог думать про тебя и день и ночь"
        );
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public boolean testShortText() {
        return Article.generateBy(
                "Мама мыла раму и окно",
                "мыла окно"
        );
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(ArticleBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
