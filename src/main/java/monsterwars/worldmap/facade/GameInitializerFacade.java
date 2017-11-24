package monsterwars.worldmap.facade;

import monsterwars.main.Main;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.WorldMapBuilder;
import monsterwars.worldmap.creator.TownMapCreator;
import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.reader.WorldMapFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Helps initializing a {@link WorldMap}.
 */
public class GameInitializerFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String FILE_READ_ERROR_MESSAGE = "Could not read file!";
    private static final String MAP_FILE_NAME = "map.txt";

    public WorldMap init() throws IOException {
        Set<String> rawData = readMap();
        WorldMap worldMap = buildWorldMap(rawData);
        return worldMap;
    }

    private WorldMap buildWorldMap(final Set<String> rawData) {
        WorldMapBuilder worldMapBuilder = new WorldMapBuilder(new TownMapCreator(new RawMapFactory()));
        return worldMapBuilder.build(rawData);
    }

    private Set<String> readMap() throws IOException {
        WorldMapFileReader worldMapFileReader = new WorldMapFileReader();
        Set<String> result = null;
        result = worldMapFileReader.read(MAP_FILE_NAME);
        return result;
    }
}
