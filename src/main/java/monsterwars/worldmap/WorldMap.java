package monsterwars.worldmap;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;
import monsterwars.worldmap.inverter.DirectionsInverter;

import java.util.concurrent.ConcurrentMap;

/**
 * Simple pojo for storing worldmap.
 */
public class WorldMap {

    private final ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map;
    private final DirectionsInverter directionsInverter;

    public WorldMap(ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map, DirectionsInverter directionsInverter) {
        this.map = map;
        this.directionsInverter = directionsInverter;
    }

    public ConcurrentMap<Town, ConcurrentMap<Directions, Town>> getMap() {
        return map;
    }

    /**
     * Removes {@link Town} from the map.
     *
     * @param town to remove.
     */
    public void removeTownFromWorldMap(Town town) {
        map.get(town).keySet().forEach(direction -> {
            Town actualTownInDirection = map.get(town).get(direction);
            if (actualTownInDirection != null) map.get(actualTownInDirection).remove(directionsInverter.get(direction));
        });
        map.remove(town);
    }
}
