package monsterwars.worldmap;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.inverter.DirectionsInverter;

import java.util.Map;

/**
 * Simple pojo for storing worldmap.
 */
public class WorldMap {

    private final Map<String, Map<Directions, String>> map;
    private final DirectionsInverter directionsInverter;

    public WorldMap(Map<String, Map<Directions, String>> map, DirectionsInverter directionsInverter) {
        this.map = map;
        this.directionsInverter = directionsInverter;
    }

    public Map<String, Map<Directions, String>> getMap() {
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
