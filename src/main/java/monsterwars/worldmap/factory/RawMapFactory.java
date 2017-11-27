package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RawMapFactory {

    public ConcurrentMap<Town, ConcurrentMap<Directions, Town>> create() {
        return new ConcurrentHashMap<>();
    }
}
