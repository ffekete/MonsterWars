package monsterwars.data;

import java.util.Map;

public class WorldMap {

    private final Map<Town, Map<DirectionNames, Town> >  map;

    public WorldMap(final Map<Town, Map<DirectionNames, Town> > map) {
        this.map = map;
    }

    public Map<Town, Map<DirectionNames, Town> > getMap() {
        return map;
    }
}
