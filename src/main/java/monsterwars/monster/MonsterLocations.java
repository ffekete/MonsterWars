package monsterwars.monster;

import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MonsterLocations {

    private final Map<Town, List<Monster>> locations;

    public MonsterLocations(final Map<Town, List<Monster>> locations) {
        this.locations = locations;
    }

    public List<Monster> getListOfMonsters(final Town town) {
        return locations.get(town);
    }

    public void addMonstersToTown(final Town town, final List<Monster> monsters) {
        locations.put(town, monsters);
    }

    public Set<Town> getTowns() {
        return locations.keySet();
    }

}
