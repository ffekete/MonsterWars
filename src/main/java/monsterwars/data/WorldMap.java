package monsterwars.data;

import java.util.Map;

public class WorldMap {

    private final Map<Town, Map<Directions, Town> >  map;

    public WorldMap(final Map<Town, Map<Directions, Town> > map) {
        this.map = map;
    }

    public Map<Town, Map<Directions, Town> > getMap() {
        return map;
    }
}
