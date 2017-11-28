package monsterwars.worldmap.creator;

import com.google.inject.Inject;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.inverter.DirectionsInverter;

import java.util.Set;

/**
 * Creates a {@link WorldMap} instance.
 */
public class WorldMapCreator {

    private final TownMapCreator townMapCreator;

    @Inject
    public WorldMapCreator(final TownMapCreator townMapCreator) {
        this.townMapCreator = townMapCreator;
    }

    /**
     * Creates a {@link WorldMap} instance from raw lines.
     *
     * @param rawData lines to use.
     * @return created instance.
     */
    public WorldMap create(Set<String> rawData) {
        return new WorldMap(townMapCreator.createFrom(rawData), new DirectionsInverter());
    }
}
