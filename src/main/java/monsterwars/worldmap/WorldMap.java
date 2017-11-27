package monsterwars.worldmap;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.HashMap;
import java.util.Map;
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

    public void removeTownFromWorldMap(Town town) {
        Map<Directions, Directions> invertDirections = new HashMap<>();
        invertDirections.put(Directions.NORTH, Directions.SOUTH);
        invertDirections.put(Directions.SOUTH, Directions.NORTH);
        invertDirections.put(Directions.EAST, Directions.WEST);
        invertDirections.put(Directions.WEST, Directions.EAST);

        map.get(town).keySet().forEach(directions -> {
            Town northTown = map.get(town).get(directions);
            if (northTown != null) map.get(northTown).remove(invertDirections.get(directions));
        });
        map.remove(town);
    }
}
