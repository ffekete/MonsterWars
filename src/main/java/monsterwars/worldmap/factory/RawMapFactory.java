package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.HashMap;
import java.util.Map;

public class RawMapFactory {

    public Map<Town, Map<Directions, Town>> create() {
        return new HashMap<>();
    }
}
