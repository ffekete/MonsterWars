package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Town;

/**
 * Creates a {@link Town} instance.
 */
public class TownFactory {

    /**
     * Creates the {@link Town} instance.
     * @param name name of the town.
     * @return instance.
     */
    public Town create(final String name) {
        return new Town(name);
    }
}
