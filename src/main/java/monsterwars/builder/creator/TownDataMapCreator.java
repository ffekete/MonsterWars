package monsterwars.builder.creator;

import monsterwars.data.DirectionNames;
import monsterwars.data.Directions;
import monsterwars.data.Town;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Converts ras lines of Strings to {@link monsterwars.data.WorldMap} content.
 */
public class TownDataMapCreator {

    private static final String SPLIT_REGEX_PATTERN = "\\s+";
    private static final String STRING_SPLIT_ERROR_MESSAGE = "The following line could not be processed during world map creation: ";

    public Map<Town, Map<DirectionNames, Town >> create(final Set<String> rawData) {
        TreeMap<Town, Map<DirectionNames, Town>> map = new TreeMap<>();
        rawData.forEach(line -> map.put(createNewTown(line), new HashMap<>()));
        return map;
    }

    private Town createNewTown(final String line) {
        return new Town(getTownName(line));
    }

    private String getTownName(final String line) {
        String[] result = line.split(SPLIT_REGEX_PATTERN);
        if (result.length == 0) {
            throw new IllegalArgumentException(STRING_SPLIT_ERROR_MESSAGE + line);
        }
        return result[0];
    }
}
