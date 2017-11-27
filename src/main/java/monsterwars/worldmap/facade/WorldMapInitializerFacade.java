package monsterwars.worldmap.facade;

import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.WorldMapBuilder;
import monsterwars.worldmap.creator.TownMapCreator;
import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.factory.TownDirectionsMapFactory;
import monsterwars.worldmap.factory.TownFactory;
import monsterwars.worldmap.reader.WorldMapFileReader;

import java.io.IOException;
import java.util.Set;

/**
 * Helps initializing a {@link WorldMap}.
 */
public class WorldMapInitializerFacade {

    private static final String MAP_FILE_NAME = "map.txt";

    public WorldMap init() throws IOException {
        Set<String> rawData = readMap();
        return buildWorldMap(rawData);
    }

    private WorldMap buildWorldMap(Set<String> rawData) {
        WorldMapBuilder worldMapBuilder = new WorldMapBuilder(new TownMapCreator(new RawMapFactory(), new TownDirectionsMapFactory(), new TownFactory()));
        return worldMapBuilder.build(rawData);
    }

    private Set<String> readMap() throws IOException {
        WorldMapFileReader worldMapFileReader = new WorldMapFileReader();
        return worldMapFileReader.read(MAP_FILE_NAME);
    }
}
