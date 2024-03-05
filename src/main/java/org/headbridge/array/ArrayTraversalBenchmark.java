package org.headbridge.array;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 5)
@Measurement(time = 5, iterations = 2)
public class ArrayTraversalBenchmark {

    @Param({"10", "100", "1000"})
    private int N;
    private int[][] array;
    private ArrayTraverse rowTraverse;
    private ArrayTraverse columnTraverse;

    @Setup
    public void setup() {
        array = new int[N][N];
        Random random = new Random();
        for (var row : array) {
            Arrays.setAll(row, (i) -> random.nextInt());
        }
        System.out.println(array);
        rowTraverse = new RowArrayTraverse(array);
        columnTraverse = new ColumnTraverse(array);
    }

    @TearDown
    public void tearDown() {
        array = null;
    }

    @Benchmark
    public void traversalByRows() {
        rowTraverse.traverse();
    }

    @Benchmark
    public void traversalByColumns() {
        columnTraverse.traverse();
    }

    public static void main(final String[] args) throws RunnerException {
        final Options options =
                new OptionsBuilder()
                        .include(ArrayTraversalBenchmark.class.getSimpleName())
                        .result("array-jmh.json").resultFormat(ResultFormatType.JSON).build();
        new Runner(options).run();
    }
}
