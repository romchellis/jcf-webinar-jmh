package org.headbridge.list;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 5)
@Measurement(time = 5, iterations = 2)
public class ListBenchmark {

    @Param({"100", "100000", "1000000"})
    private int N;

    private List<Integer> arrayList;
    private List<Integer> linkedList;

    @Setup
    public void setup() {
        arrayList = new ArrayList<>(N);
        linkedList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    @Benchmark
    public void removeFromArrayList() {
        while (!arrayList.isEmpty()) {
            arrayList.remove(0);
        }
    }

    @Benchmark
    public void removeFromLinkedList() {
        while (!linkedList.isEmpty()) {
            linkedList.remove(0);
        }
    }

    public static void main(final String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(ListBenchmark.class.getSimpleName())
                .result("list-jmh.json").resultFormat(ResultFormatType.JSON).build();
        new Runner(options).run();
    }
}

