package monsterwars.worldmap;

import com.google.inject.Inject;
import monsterwars.worldmap.creator.TownMapCreator;

import java.util.Set;

/**
 * Builds a {@link WorldMap} instance.
 */
public class WorldMapBuilder {

    private final TownMapCreator townMapCreator;

    @Inject
    public WorldMapBuilder(final TownMapCreator townMapCreator) {
        this.townMapCreator = townMapCreator;
    }

    public WorldMap build(Set<String> rawData) {
        return new WorldMap(townMapCreator.createFrom(rawData));
    }
}
