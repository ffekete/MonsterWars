package monsterwars.builder.creator;

import monsterwars.builder.factory.RawMapFactory;
import monsterwars.data.Directions;
import monsterwars.data.Town;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Converts raw lines of Strings to {@link Map} content.
 */
public class TownMapCreator {

    private static final String SPLIT_REGEX_PATTERN = "\\s+";

    private final RawMapFactory rawMapFactory;

    public TownMapCreator(final RawMapFactory rawMapFactory) {
        this.rawMapFactory = rawMapFactory;
    }

    public Map<Town, Map<Directions, Town>> create(final Set<String> rawData) {
        Map<Town, Map<Directions, Town>> map = rawMapFactory.create();
        rawData.forEach(line -> fillMap(map, line));
        return map;
    }

    private void fillMap(final Map<Town, Map<Directions, Town>> map, final String line) {
        Town currentTown = createNewTown(line);
        map.put(currentTown, new HashMap<>());
        String[] tokens = line.split("\\s");
        for (int i = 1; i < tokens.length; i++) {
            String townLink = tokens[i];
            String direction = townLink.split("=")[0];
            String townToLink = townLink.split("=")[1];
            Town townToLinkInstance = new Town(townToLink);
            map.get(currentTown).put(Directions.valueOf(direction.toUpperCase()), townToLinkInstance);
        }
    }

    private Town createNewTown(final String line) {
        return new Town(getTownName(line));
    }

    private String getTownName(final String line) {
        return line.split(SPLIT_REGEX_PATTERN)[0];
    }
}
