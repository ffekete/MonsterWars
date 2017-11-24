package monsterwars.builder;

import monsterwars.builder.creator.TownDataMapCreator;
import monsterwars.data.WorldMap;

import java.util.Set;

/**
 * Builds a {@link WorldMap} instance.
 */
public class WorldMapBuilder {

    private final TownDataMapCreator townDataMapCreator;

    public WorldMapBuilder(final TownDataMapCreator townDataMapCreator) {
        this.townDataMapCreator = townDataMapCreator;
    }

    public WorldMap build(final Set<String> rawData) {
       return new WorldMap(townDataMapCreator.create(rawData));
    }
}
