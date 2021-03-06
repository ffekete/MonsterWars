package monsterwars.monster;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Storing locations of Monsters.
 */
public class MonsterLocations {

    private final Map<String, List<Monster>> locations;

    public MonsterLocations(final Map<String, List<Monster>> locations) {
        this.locations = locations;
    }

    public List<Monster> getListOfMonsters(final String town) {
        return locations.get(town);
    }

    public void setMonstersListToTown(String town, List<Monster> monsters) {
        locations.put(town, monsters);
    }

    public void removeMonsterFromTown(String town, Monster monster) {
        locations.get(town).remove(monster);
    }

    public void addMonsterToTown(String town, Monster monster) {
        locations.get(town).add(monster);
    }

    public Set<String> getTowns() {
        return locations.keySet();
    }

    public Map<String, List<Monster>> getLocations() {
        return locations;
    }
}
