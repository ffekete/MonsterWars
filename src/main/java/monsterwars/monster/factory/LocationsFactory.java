package monsterwars.monster.factory;


import monsterwars.monster.Monster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used for creating {@link Map} for {@link monsterwars.monster.MonsterLocationsInitializerFacade}.
 */
public class LocationsFactory {

    /**
     * Creates {@link Map} instance.
     *
     * @return created instance.
     */
    public Map<String, List<Monster>> create() {
        return new HashMap<>();
    }
}
