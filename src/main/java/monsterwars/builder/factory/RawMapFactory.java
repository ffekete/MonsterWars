package monsterwars.builder.factory;

import monsterwars.data.Directions;
import monsterwars.data.Town;

import java.util.Map;
import java.util.TreeMap;

public class RawMapFactory {

    public Map<Town, Map<Directions, Town>> create() {
        return new TreeMap<>();
    }
}
