package monsterwars.monster.initializer;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.Set;

public class LocationsInitializer {

    public void initialize(MonsterLocations locations, final Set<Town> towns) {
        towns.forEach(town -> locations.addMonstersToTown(town, getEmptyListOfMonsters()));
    }

    private ArrayList<Monster> getEmptyListOfMonsters() {
        return new ArrayList<>();
    }
}
