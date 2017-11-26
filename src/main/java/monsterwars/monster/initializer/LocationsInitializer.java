package monsterwars.monster.initializer;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.Set;

public class LocationsInitializer {

    private final MonsterListFactory monsterListFactory;

    public LocationsInitializer(final MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
    }

    public void initialize(MonsterLocations locations, final Set<Town> towns) {
        towns.forEach(town -> locations.addMonstersToTown(town, getEmptyListOfMonsters()));
    }

    private List<Monster> getEmptyListOfMonsters() {
        return monsterListFactory.create();
    }
}
