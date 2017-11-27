package monsterwars.worldmap;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.concurrent.ConcurrentMap;

/**
 * Simple pojo for storing worldmap.
 */
public class WorldMap {

    private final ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map;

    public WorldMap(ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map) {
        this.map = map;
    }

    public ConcurrentMap<Town, ConcurrentMap<Directions, Town>> getMap() {
        return map;
    }
}
