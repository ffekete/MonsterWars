package monsterwars.worldmap.creator;

import com.google.inject.Inject;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.factory.TownDirectionsMapFactory;

import java.util.Map;
import java.util.Set;

/**
 * Converts raw lines of Strings to {@link Map} content.
 */
public class TownMapCreator {

    private static final String SPACE_SEPARATOR = "\\s+";
    private static final String EQUALS_SEPARATOR = "=";

    private final RawMapFactory rawMapFactory;
    private final TownDirectionsMapFactory townDirectionsMapFactory;

    @Inject
    public TownMapCreator(final RawMapFactory rawMapFactory, final TownDirectionsMapFactory townDirectionsMapFactory) {
        this.rawMapFactory = rawMapFactory;
        this.townDirectionsMapFactory = townDirectionsMapFactory;
    }

    /**
     * Creates a {@link Map} instance from {@link Set} raw text lines.
     *
     * @param rawData a {@link Set} of lines.
     * @return the created map instance.
     */
    public Map<String, Map<Directions, String>> createFrom(Set<String> rawData) {
        Map<String, Map<Directions, String>> map = rawMapFactory.create();
        rawData.forEach(line -> fillMap(map, line));
        return map;
    }

    private void fillMap(Map<String, Map<Directions, String>> map, String line) {
        String[] tokens = splitLineToTokens(line, SPACE_SEPARATOR);
        String currentTown = getTownNameFromTokens(tokens);
        addTownToMapIfNeeded(map, currentTown);
        for (int i = 1; i < tokens.length; i++) {
            validateToken(tokens[i]);
            String direction = getDirectionFromCurrentToken(tokens[i]);
            String nameOfTownToLink = getTownNameFromCurrentToken(tokens[i]);
            addTownToMapIfNeeded(map, nameOfTownToLink);
            putTownToLinkToGivenDirection(map, currentTown, direction, nameOfTownToLink);
        }
    }

    private String[] splitLineToTokens(final String line, final String regex) {
        return line.split(regex);
    }

    private String getTownNameFromTokens(final String[] tokens) {
        return tokens[0];
    }

    private void validateToken(String token) {
        if (!token.contains(EQUALS_SEPARATOR)) {
            throw new IllegalArgumentException("Cannot split token " + token + ", it should be '[direction]=[townname]'.");
        }
    }

    private String getDirectionFromCurrentToken(final String token) {
        return splitLineToTokens(token, EQUALS_SEPARATOR)[0];
    }

    private String getTownNameFromCurrentToken(final String token) {
        return splitLineToTokens(token, EQUALS_SEPARATOR)[1];
    }

    private void addTownToMapIfNeeded(Map<String, Map<Directions, String>> map, String townToLink) {
        map.computeIfAbsent(townToLink, k -> townDirectionsMapFactory.create());
    }

    private void putTownToLinkToGivenDirection(Map<String, Map<Directions, String>> map, String currentTown, String direction, String townToLink) {
        map.get(currentTown).put(getTownDirection(direction), townToLink);
    }

    private Directions getTownDirection(final String direction) {
        return Directions.valueOf(direction.toUpperCase());
    }
}
