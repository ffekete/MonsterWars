package monsterwars.worldmap.factory;

import monsterwars.worldmap.data.Town;

public class TownFactory {
    public Town create(final String name) {
        return new Town(name);
    }
}
