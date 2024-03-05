package org.headbridge.map;

import java.util.Collection;
import java.util.NavigableMap;

public class TreeMapSelector implements MapSelector {
    private final NavigableMap<Integer, String> map;

    public TreeMapSelector(NavigableMap<Integer, String> map) {
        this.map = map;
    }

    @Override
    public Collection<String> select(int from, int to) {
        return map.subMap(from, to).values().stream().toList();
    }
}
