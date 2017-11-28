package monsterwars.monster.initializer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.Set;

@Singleton
public class LocationsInitializer {

    private final MonsterListFactory monsterListFactory;

    @Inject
    public LocationsInitializer(final MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
    }

    public void initialize(MonsterLocations locations, final Set<Town> towns) {
        towns.forEach(town -> locations.setMonstersListToTown(town, getEmptyListOfMonsters()));
    }

    private List<Monster> getEmptyListOfMonsters() {
        return monsterListFactory.createEmpty();
    }
}
