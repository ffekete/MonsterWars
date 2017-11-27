package monsterwars.monster.factory;


import monsterwars.monster.Monster;
import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Used for creating {@link ConcurrentMap} for {@link monsterwars.monster.MonsterLocationsInitializerFacade}.
 */
public class LocationsFactory {

    /**
     * Creates {@link ConcurrentMap} instance.
     *
     * @return created instance.
     */
    public ConcurrentMap<Town, List<Monster>> create() {
        return new ConcurrentHashMap<>();
    }
}
