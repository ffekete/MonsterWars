package monsterwars.builder.filler;

import monsterwars.data.Directions;
import monsterwars.data.Town;
import monsterwars.data.WorldMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class TownDataDirectionsFiller {

    private static final String SPLIT_REGEX_PATTERN_FOR_LINE_PROCESSING = "\\s+";
    private static final String SPLIT_TOKEN_FOR_NEXT_TOWN_IN_LINE_PROCESSING = "=";

    public WorldMap fill(final Map<Town, Map<Directions, Town>> map, final Set<String> rawData) {
        rawData.forEach(lines -> {
            String[] tokens = lines.split(SPLIT_REGEX_PATTERN_FOR_LINE_PROCESSING);
            String currentTownName = tokens[0];
            for (int i = 1; i < tokens.length; i++) {
                final String nameOfNextTownInLine = tokens[i].split("=")[1];
                final String directionOfNextTownInLine = getDirectionOfNextTownInLineAsString(tokens, i);
                final Optional<Town> townToPutInThatDirection = findTownInstanceInMap(map, nameOfNextTownInLine);
                townToPutInThatDirection.ifPresent(placeNextTownInLineOnWorldMap(map, currentTownName, directionOfNextTownInLine));
            }
        });
        return new WorldMap(map);
    }

    private Consumer<Town> placeNextTownInLineOnWorldMap(final Map<Town, Map<Directions, Town>> map, final String currentTownName, final String directionOfNextTownInLine) {
        return town -> getCurrentTownDirectionsMap(map, currentTownName).put(getDirectionsValue(directionOfNextTownInLine), town);
    }

    private String getDirectionOfNextTownInLineAsString(final String[] tokens, int i) {
        return tokens[i].split(SPLIT_TOKEN_FOR_NEXT_TOWN_IN_LINE_PROCESSING)[0];
    }

    private Directions getDirectionsValue(final String directionOfThatTown) {
        return Directions.valueOf(directionOfThatTown.toUpperCase());
    }

    private Map<Directions, Town> getCurrentTownDirectionsMap(final Map<Town, Map<Directions, Town>> map, final String currentTownName) {
        return map.get(new Town(currentTownName));
    }

    private Optional<Town> findTownInstanceInMap(final Map<Town, Map<Directions, Town>> map, final String nameOfTheTownToSearch) {
        return map.keySet().stream().filter(town -> town.getName().equals(nameOfTheTownToSearch)).findFirst();
    }
}
