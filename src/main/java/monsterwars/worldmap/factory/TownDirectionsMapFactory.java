package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TownDirectionsMapFactory {

    public ConcurrentMap<Directions, Town> create() {
        return new ConcurrentHashMap<>();
    }
}
