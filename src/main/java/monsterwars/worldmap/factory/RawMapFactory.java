package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.Map;
import java.util.TreeMap;

public class RawMapFactory {

    public Map<Town, Map<Directions, Town>> create() {
        return new TreeMap<>();
    }
}
