package monsterwars.monster;

import com.google.inject.Singleton;
import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Singleton
public class MonsterLocations {

    private Map<Town, List<Monster>> locations;

    public MonsterLocations(final Map<Town, List<Monster>> locations) {
        this.locations = locations;
    }

    public List<Monster> getListOfMonsters(final Town town) {
        return locations.get(town);
    }

    public void addMonstersToTown(Town town, List<Monster> monsters) {
        locations.put(town, monsters);
    }

    public void removeMonsterFromTown(Town town, Monster monster) {
        locations.get(town).remove(monster);
    }

    public void addMonsterToTown(Town town, Monster monster) {
        //Town town1 = locations.keySet().stream().filter(town2 -> town2.getName().equals(town.getName())).findFirst().get();
        locations.get(town).add(monster);
    }

    public Set<Town> getTowns() {
        return locations.keySet();
    }

}
