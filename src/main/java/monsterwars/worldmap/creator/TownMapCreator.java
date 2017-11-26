package monsterwars.worldmap.creator;

import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

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

    public Map<Town, Map<Directions, Town>> createFrom(Set<String> rawData) {
        Map<Town, Map<Directions, Town>> map = rawMapFactory.create();
        rawData.forEach(line -> fillMap(map, line));
        return map;
    }

    private void fillMap(Map<Town, Map<Directions, Town>> map, String line) {
        String[] tokens = splitLineToTokens(line, SPACE_SEPARATOR);
        Town currentTown = createNewTown(tokens[0], map);
        placeCurrentTownToMap(map, currentTown);
        for (int i = 1; i < tokens.length; i++) {
            String direction = getDirectionFromCurrentToken(tokens[i]);
            String nameOfTownToLink = getTownNameFromCurrentToken(tokens[i]);
            Town townToLink = getTownToLink(nameOfTownToLink, map);
            map.computeIfAbsent(townToLink, k -> new HashMap<>());
            map.get(currentTown).put(getTownDirection(direction), townToLink);
        }
    }

    private Town getTownToLink(String nameOfTownToLink, Map<Town, Map<Directions, Town>> map) {
        return map.keySet().stream().filter(town -> town.getName().equals(nameOfTownToLink)).findFirst().orElse(new Town(nameOfTownToLink));
    }

    private String[] splitLineToTokens(final String line, final String regex) {
        return line.split(regex);
    }

    private Town createNewTown(final String name, Map<Town, Map<Directions, Town>> map) {
        return map.keySet().stream().filter(town -> town.getName().equals(name)).findFirst().orElse(new Town(name));
    }

    private void placeCurrentTownToMap(Map<Town, Map<Directions, Town>> map, Town currentTown) {
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
