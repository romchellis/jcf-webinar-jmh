package org.headbridge.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.IntStream;

public class HashMapSelector implements MapSelector {
    private final HashMap<Integer, String> map;

    public HashMapSelector(HashMap<Integer, String> map) {
        this.map = map;
    }

    @Override
    public Collection<String> select(int from, int to) {
        return IntStream.range(from, to)
                .filter(map::containsKey)
                .mapToObj(map::get)
                .toList();
    }
}
