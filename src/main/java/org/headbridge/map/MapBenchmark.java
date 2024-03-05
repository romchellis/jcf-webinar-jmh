package org.headbridge.map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1, time = 1)
@Measurement(time = 5, iterations = 2)
public class MapBenchmark {
    private MapSelector hashMapSelector;
    private MapSelector treeMapSelector;

    @Setup
    public void setup() {
        HashMap<Integer, String> hash = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        var random = new Random();
        while (hash.size() < 1048576) {
            int val = random.nextInt();
            String value = String.valueOf(val);
            hash.put(val, value);
            treeMap.put(val, value);
        }
        hashMapSelector = new HashMapSelector(hash);
        treeMapSelector = new TreeMapSelector(treeMap);
    }

    @Benchmark
    public void selectFromHashMap() {
        hashMapSelector.select(10, 100000);
    }

    @Benchmark
    public void selectFromTreeMap() {
        treeMapSelector.select(10, 100000);
    }

    public static void main(final String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(MapBenchmark.class.getSimpleName())
                .result("map-jmh.json").resultFormat(ResultFormatType.JSON).build();
        new Runner(options).run();
    }
}

