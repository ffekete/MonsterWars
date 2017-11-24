package monsterwars.monster;

import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.Map;

public class MonsterLocations {

    private final Map<Town, List<Monster>> locations;

    public MonsterLocations(final Map<Town, List<Monster>> locations) {
        this.locations = locations;
    }

    public Map<Town, List<Monster>> getLocations() {
        return locations;
    }
}
