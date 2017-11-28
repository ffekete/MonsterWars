package monsterwars.worldmap;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.inverter.DirectionsInverter;

import java.util.concurrent.ConcurrentMap;

/**
 * Simple pojo for storing worldmap.
 */
public class WorldMap {

    private final ConcurrentMap<String, ConcurrentMap<Directions, String>> map;
    private final DirectionsInverter directionsInverter;

    public WorldMap(ConcurrentMap<String, ConcurrentMap<Directions, String>> map, DirectionsInverter directionsInverter) {
        this.map = map;
        this.directionsInverter = directionsInverter;
    }

    public ConcurrentMap<String, ConcurrentMap<Directions, String>> getMap() {
        return map;
    }

    /**
     * Removes town from the map.
     *
     * @param town to remove.
     */
    public void removeTownFromWorldMap(String town) {
        map.get(town).forEach((direction, townInDirection) -> {
            map.get(townInDirection).remove(directionsInverter.get(direction));
        });
        map.remove(town);
    }
}
