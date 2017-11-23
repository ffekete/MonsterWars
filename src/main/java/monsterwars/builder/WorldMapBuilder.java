package monsterwars.builder;

import monsterwars.builder.creator.TownDataMapCreator;
import monsterwars.builder.filler.TownDataDirectionsFiller;
import monsterwars.data.DirectionNames;
import monsterwars.data.Town;
import monsterwars.data.WorldMap;

import java.util.Map;
import java.util.Set;

/**
 * Builds a {@link WorldMap} instance.
 */
public class WorldMapBuilder {

    private final TownDataMapCreator townDataMapCreator;
    private final TownDataDirectionsFiller townDataDirectionsFiller;

    public WorldMapBuilder(final TownDataMapCreator townDataMapCreator, final TownDataDirectionsFiller townDataDirectionsFiller) {
        this.townDataMapCreator = townDataMapCreator;
        this.townDataDirectionsFiller = townDataDirectionsFiller;
    }

    public WorldMap build(final Set<String> rawData) {
        Map<Town, Map<DirectionNames, Town>> map = townDataMapCreator.create(rawData);
        return townDataDirectionsFiller.fill(map, rawData);
    }
}
