package monsterwars.facade;

import monsterwars.builder.WorldMapBuilder;
import monsterwars.builder.creator.TownDataMapCreator;
import monsterwars.builder.filler.TownDataDirectionsFiller;
import monsterwars.data.WorldMap;
import monsterwars.main.Main;
import monsterwars.reader.WorldMapFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

/**
 * Helps initializing a {@link monsterwars.data.WorldMap}.
 */
public class GameInitializerFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String FILE_READ_ERROR_MESSAGE = "Could not read file!";
    private static final String MAP_FILE_NAME = "map.txt";

    public void init() {
        Set<String> rawData = readMap();
        WorldMap worldMap = buildWorldMap(rawData);
        System.out.println(worldMap.getMap().keySet().size());
        worldMap.getMap().keySet().forEach(town -> System.out.println("" + town.getName() + worldMap.getMap().get(town)));
    }

    private WorldMap buildWorldMap(final Set<String> rawData) {
        WorldMapBuilder worldMapBuilder = new WorldMapBuilder(new TownDataMapCreator(), new TownDataDirectionsFiller());
        return worldMapBuilder.build(rawData);
    }

    private Set<String> readMap() {
        WorldMapFileReader worldMapFileReader = new WorldMapFileReader();
        Set<String> result = null;
        try {
            result = worldMapFileReader.read(MAP_FILE_NAME);
        } catch (IOException exception) {
            LOGGER.error(FILE_READ_ERROR_MESSAGE);
        }
        return result;
    }
}
