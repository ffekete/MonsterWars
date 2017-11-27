package monsterwars.worldmap.decorator;

import monsterwars.worldmap.WorldMap;

/**
 * Decorates {@link WorldMap} instance.
 */
public class WorldMapDecorator {

    private final WorldMap worldMap;

    public WorldMapDecorator(final WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    /**
     * Prints the remaining {@link monsterwars.worldmap.data.Town}s in the world.
     */
    public void printRemainingWorldMap() {
        worldMap.getMap().keySet().forEach(town -> {
            System.out.print(town.getName() + " ");
            worldMap.getMap().get(town).forEach((directions, town1) -> System.out.print(directions.getName() + ": " + town1.getName() + " "));
            System.out.println("");
        });
    }
}
