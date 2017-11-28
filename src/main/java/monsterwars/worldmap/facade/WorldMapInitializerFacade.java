package monsterwars.worldmap.facade;

import com.google.inject.Inject;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.WorldMapCreator;
import monsterwars.worldmap.reader.WorldMapFileReader;

import java.io.IOException;
import java.util.Set;

/**
 * Helps initializing a {@link WorldMap}.
 */
public class WorldMapInitializerFacade {

    private final WorldMapCreator worldMapCreator;
    private final WorldMapFileReader worldMapFileReader;

    @Inject
    public WorldMapInitializerFacade(WorldMapCreator worldMapCreator, WorldMapFileReader worldMapFileReader) {
        this.worldMapCreator = worldMapCreator;
        this.worldMapFileReader = worldMapFileReader;
    }

    private static final String MAP_FILE_NAME = "map.txt";

    /**
     * Initializes the world map.
     *
     * @return {@link WorldMap} instance.
     * @throws IOException when map file cannot be found.
     */
    public WorldMap init() throws IOException {
        Set<String> rawData = readMap();
        return buildWorldMap(rawData);
    }

    private Set<String> readMap() throws IOException {
        return worldMapFileReader.read(MAP_FILE_NAME);
    }

    private WorldMap buildWorldMap(Set<String> rawData) {
        return worldMapCreator.create(rawData);
    }
}
