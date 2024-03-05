package org.headbridge.map;

import java.util.Collection;

public interface MapSelector {

    Collection<String> select (int from, int to);
}
