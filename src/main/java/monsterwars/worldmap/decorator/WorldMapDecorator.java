package monsterwars.worldmap.decorator;

import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

public class WorldMapDecorator {

    private final WorldMap worldMap;

    public WorldMapDecorator(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void removeTownFromWorldMap(Town town) {
        worldMap.getMap().get(town).keySet().forEach(directions -> {
            Town northTown = worldMap.getMap().get(town).get(Directions.NORTH);
            Town southTown = worldMap.getMap().get(town).get(Directions.SOUTH);
            Town eastTown = worldMap.getMap().get(town).get(Directions.EAST);
            Town westTown = worldMap.getMap().get(town).get(Directions.WEST);
            if (northTown != null) worldMap.getMap().get(northTown).remove(Directions.SOUTH);
            if (southTown != null) worldMap.getMap().get(southTown).remove(Directions.NORTH);
            if (eastTown != null) worldMap.getMap().get(eastTown).remove(Directions.WEST);
            if (westTown != null) worldMap.getMap().get(westTown).remove(Directions.EAST);
        });
        worldMap.getMap().remove(town);
    }
}
