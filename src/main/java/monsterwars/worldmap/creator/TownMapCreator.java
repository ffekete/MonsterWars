package monsterwars.worldmap.creator;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;
import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.factory.TownDirectionsMapFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Converts raw lines of Strings to {@link ConcurrentMap} content.
 */
public class TownMapCreator {

    private static final String SPACE_SEPARATOR = "\\s+";
    private static final String EQUALS_SEPARATOR = "=";

    private final RawMapFactory rawMapFactory;
    private final TownDirectionsMapFactory townDirectionsMapFactory;

    public TownMapCreator(final RawMapFactory rawMapFactory, TownDirectionsMapFactory townDirectionsMapFactory) {
        this.rawMapFactory = rawMapFactory;
        this.townDirectionsMapFactory = townDirectionsMapFactory;
    }

    /**
     * Creates a {@link ConcurrentMap} instance from {@link Set} raw text lines.
     * @param rawData a {@link Set} of lines.
     * @return the created map instance.
     */
    public ConcurrentMap<Town, ConcurrentMap<Directions, Town>> createFrom(Set<String> rawData) {
        ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map = rawMapFactory.create();
        rawData.forEach(line -> fillMap(map, line));
        return map;
    }

    private void fillMap(ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map, String line) {
        String[] tokens = splitLineToTokens(line, SPACE_SEPARATOR);
        Town currentTown = createNewTownIfDoesNotExist(getTownNameFromTokens(tokens), map);
        placeCurrentTownToMap(map, currentTown);
        for (int i = 1; i < tokens.length; i++) {
            String direction = getDirectionFromCurrentToken(tokens[i]);
            String nameOfTownToLink = getTownNameFromCurrentToken(tokens[i]);
            Town townToLink = getTownToLink(nameOfTownToLink, map);
            addLinkedTownToMapIfNeeded(map, townToLink);
            putTownToLinkToGivenDirection(map, currentTown, direction, townToLink);
        }
    }

    private String getTownNameFromTokens(final String[] tokens) {
        return tokens[0];
    }

    private void putTownToLinkToGivenDirection(ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map, Town currentTown, String direction, Town townToLink) {
        map.get(currentTown).put(getTownDirection(direction), townToLink);
    }

    private void addLinkedTownToMapIfNeeded(ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map, Town townToLink) {
        map.computeIfAbsent(townToLink, k -> townDirectionsMapFactory.create());
    }

    private Town getTownToLink(String nameOfTownToLink, ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map) {
        return map.keySet().stream().filter(town -> town.getName().equals(nameOfTownToLink)).findFirst().orElse(new Town(nameOfTownToLink));
    }

    private String[] splitLineToTokens(final String line, final String regex) {
        return line.split(regex);
    }

    private Town createNewTownIfDoesNotExist(final String name, ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map) {
        return map.keySet().stream().filter(town -> town.getName().equals(name)).findFirst().orElse(new Town(name));
    }

    private void placeCurrentTownToMap(ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map, Town currentTown) {
        map.put(currentTown, townDirectionsMapFactory.create());
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
