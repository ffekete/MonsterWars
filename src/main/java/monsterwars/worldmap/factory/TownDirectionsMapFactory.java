package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a {@link Map} instance for {@link monsterwars.worldmap.creator.TownMapCreator}.
 */
public class TownDirectionsMapFactory {

    /**
     * Creates the {@link Map} instance.
     *
     * @return created map.
     */
    public Map<Directions, String> create() {
        return new HashMap<>();
    }
}
