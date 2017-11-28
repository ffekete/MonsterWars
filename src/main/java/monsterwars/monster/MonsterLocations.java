package monsterwars.monster;

import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Storing locations of Monsters.
 */
public class MonsterLocations {

    private ConcurrentMap<Town, List<Monster>> locations;

    public MonsterLocations(final ConcurrentMap<Town, List<Monster>> locations) {
        this.locations = locations;
    }

    public List<Monster> getListOfMonsters(final Town town) {
        return locations.get(town);
    }

    public void setMonstersListToTown(Town town, List<Monster> monsters) {
        locations.put(town, monsters);
    }

    public void removeMonsterFromTown(Town town, Monster monster) {
        locations.get(town).remove(monster);
    }

    public void addMonsterToTown(Town town, Monster monster) {
        locations.get(town).add(monster);
    }

    public Set<Town> getTowns() {
        return locations.keySet();
    }
}
