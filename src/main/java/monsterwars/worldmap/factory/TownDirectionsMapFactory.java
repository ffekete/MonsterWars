package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Creates a {@link ConcurrentMap} instance for {@link monsterwars.worldmap.creator.TownMapCreator}.
 */
public class TownDirectionsMapFactory {

    /**
     * Creates the {@link ConcurrentMap} instance.
     *
     * @return created map.
     */
    public ConcurrentMap<Directions, String> create() {
        return new ConcurrentSkipListMap<>();
    }
}
