package monsterwars.builder.filler;

import monsterwars.data.Directions;
import monsterwars.data.Town;
import monsterwars.data.WorldMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TownDataDirectionsFiller {

    private static final String SPLIT_REGEX_PATTERN = "\\s+";

    public WorldMap fill(final Map<Town, Map<Directions, Town>> map, final Set<String> rawData) {
        rawData.forEach(lines -> {
            String[] tokens = lines.split(SPLIT_REGEX_PATTERN);
            String currentTownName = tokens[0];
            for (int i = 1; i < tokens.length; i++) {
                final String nameOfTheTownToSearch = tokens[i].split("=")[1];
                final String directionOfThatTown = tokens[i].split("=")[0];
                final Optional<Town> townToPut = findTownInstanceInMap(map, nameOfTheTownToSearch);
                townToPut.ifPresent(town -> map.get(new Town(currentTownName)).put(Directions.valueOf(directionOfThatTown.toUpperCase()), town));
            }
        });
        return new WorldMap(map);
    }

    private Optional<Town> findTownInstanceInMap(Map<Town, Map<Directions, Town>> map, String nameOfTheTownToSearch) {
        return map.keySet().stream().filter(town -> town.getName().equals(nameOfTheTownToSearch)).findFirst();
    }
}
