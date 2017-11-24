package monsterwars.builder.creator;

import monsterwars.builder.factory.RawMapFactory;
import monsterwars.data.Directions;
import monsterwars.data.Town;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Converts ras lines of Strings to {@link monsterwars.data.WorldMap} content.
 */
public class TownDataMapCreator {

    private static final String SPLIT_REGEX_PATTERN = "\\s+";

    private final RawMapFactory rawMapFactory;

    public TownDataMapCreator(RawMapFactory rawMapFactory) {
        this.rawMapFactory = rawMapFactory;
    }

    public Map<Town, Map<Directions, Town>> create(final Set<String> rawData) {
        Map<Town, Map<Directions, Town>> map = rawMapFactory.create();
        rawData.forEach(line -> fillMap(map, line));
        return map;
    }

    private Map<Directions, Town> fillMap(Map<Town, Map<Directions, Town>> map, String line) {
        Town currentTown = createNewTown(line);
        Map m = map.put(currentTown, new HashMap<>());
        String[] tokens = line.split("\\s");
        for (int i = 1; i < tokens.length; i++) {
            String townLink = tokens[i];
            String direction = townLink.split("=")[0];
            String townToLink = townLink.split("=")[1];
            Town townToLinkInstance = new Town(townToLink);
            map.get(currentTown).put(Directions.valueOf(direction.toUpperCase()), townToLinkInstance);
        }
        return m;
    }

    private Town createNewTown(final String line) {
        return new Town(getTownName(line));
    }

    private String getTownName(final String line) {
        return line.split(SPLIT_REGEX_PATTERN)[0];
    }
}
