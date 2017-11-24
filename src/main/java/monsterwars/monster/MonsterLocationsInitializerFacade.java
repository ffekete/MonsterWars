package monsterwars.monster;

import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MonsterLocationsInitializerFacade {

    public MonsterLocations init(final int numberOfMonsters, Set<Town> towns) {
        Map<Town, List<Monster>> locations = new HashMap<>();
        initializeLocations(towns, locations);
        int size = getNumberOfTowns(towns);
        ArrayList<Town> listOfTowns = initializeListOfTowns(towns);
        for(int i = 0; i < numberOfMonsters; i++) {
            Monster monster = createMonster(i);
            int itemIndex = getRandomItemIndex(size);
            getMonstersList(locations, listOfTowns, itemIndex).add(monster);
        }
        return new MonsterLocations(locations);
    }

    private int getNumberOfTowns(Set<Town> towns) {
        return towns.size();
    }

    private List<Monster> getMonstersList(Map<Town, List<Monster>> locations, ArrayList<Town> listOfTowns, int itemIndex) {
        return locations.get(listOfTowns.get(itemIndex));
    }

    private int getRandomItemIndex(int size) {
        return new Random().nextInt(size);
    }

    private Monster createMonster(int i) {
        return new Monster(String.valueOf(i));
    }

    private ArrayList<Town> initializeListOfTowns(final Set<Town> towns) {
        return new ArrayList<>(towns);
    }

    private void initializeLocations(final Set<Town> towns, Map<Town, List<Monster>> locations) {
        towns.forEach(town -> locations.put(town, new ArrayList<>()));
    }
}
