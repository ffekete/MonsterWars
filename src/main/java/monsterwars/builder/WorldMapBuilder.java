package monsterwars.builder;

import monsterwars.builder.creator.TownMapCreator;
import monsterwars.data.WorldMap;

import java.util.Set;

/**
 * Builds a {@link WorldMap} instance.
 */
public class WorldMapBuilder {

    private final TownMapCreator townMapCreator;

    public WorldMapBuilder(final TownMapCreator townMapCreator) {
        this.townMapCreator = townMapCreator;
    }

    public WorldMap build(final Set<String> rawData) {
       return new WorldMap(townMapCreator.createFrom(rawData));
    }
}
