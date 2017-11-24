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

    private static final String SPACE_SEPARATOR = "\\s+";
    private static final String EQUALS_SEPARATOR = "=";

    private final RawMapFactory rawMapFactory;

    public TownMapCreator(final RawMapFactory rawMapFactory) {
        this.rawMapFactory = rawMapFactory;
    }

    public Map<Town, Map<Directions, Town>> createFrom(final Set<String> rawData) {
        Map<Town, Map<Directions, Town>> map = rawMapFactory.create();
        rawData.forEach(line -> fillMap(map, line));
        return map;
    }

    private void fillMap(final Map<Town, Map<Directions, Town>> map, final String line) {
        String[] tokens = splitLineToTokens(line, SPACE_SEPARATOR);
        Town currentTown = createNewTown(tokens[0]);
        placeCurrentTownToMap(map, currentTown);
        for (int i = 1; i < tokens.length; i++) {
            String direction = getDirectionFromCurrentToken(tokens[i]);
            String nameOfTownToLink = getTownNameFromCurrentToken(tokens[i]);
            Town townToLink = new Town(nameOfTownToLink);
            map.get(currentTown).put(getTownDirection(direction), townToLink);
        }
    }

    private String[] splitLineToTokens(final String line, final String regex) {
        return line.split(regex);
    }

    private Town createNewTown(final String name) {
        return new Town(name);
    }

    private void placeCurrentTownToMap(final Map<Town, Map<Directions, Town>> map, final Town currentTown) {
        map.put(currentTown, new HashMap<>());
    }

    private String getDirectionFromCurrentToken(final String token) {
        return splitLineToTokens(token, EQUALS_SEPARATOR)[0];
    }

    private String getTownNameFromCurrentToken(final String token) {
        return splitLineToTokens(token, EQUALS_SEPARATOR)[1];
    }

    private Directions getTownDirection(final String direction) {
        return Directions.valueOf(direction.toUpperCase());
    }
}
