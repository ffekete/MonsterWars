package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Creates a {@link ConcurrentMap} instance for {@link monsterwars.worldmap.creator.TownMapCreator}.
 */
public class RawMapFactory {

    /**
     * Creates the {@link ConcurrentMap} instance.
     *
     * @return created map.
     */
    public ConcurrentMap<Town, ConcurrentMap<Directions, Town>> create() {
        return new ConcurrentHashMap<>();
    }
}
