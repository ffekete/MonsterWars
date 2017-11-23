package monsterwars.builder.filler;

import monsterwars.data.DirectionNames;
import monsterwars.data.Directions;
import monsterwars.data.Town;
import monsterwars.data.WorldMap;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TownDataDirectionsFiller {

    private static final String SPLIT_REGEX_PATTERN = "\\s+";

    public WorldMap fill(final Map<Town, Map<DirectionNames, Town>> map, final Set<String> rawData) {
        rawData.forEach(lines -> {
            String[] tokens = lines.split(SPLIT_REGEX_PATTERN);
            String townName = tokens[0];
            for (int i = 1; i < tokens.length; i++) {
                final String nameOfTheTownToSearch = tokens[i].split("=")[1];
                final String directionOfThatTown = tokens[i].split("=")[0];
                final Optional<Town> townToPut = map.keySet().stream().filter(town -> town.getName().equals(nameOfTheTownToSearch)).findFirst();
                map.keySet().stream().filter(town -> town.getName().equals(townName)).findFirst().map(town -> {
                    map.get(town).put(DirectionNames.valueOf(directionOfThatTown.toUpperCase()), townToPut.get());
                    return map;
                });
            }
        });
        return new WorldMap(map);
    }
}
