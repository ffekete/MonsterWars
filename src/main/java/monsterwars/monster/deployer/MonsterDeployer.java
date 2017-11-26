package monsterwars.monster.deployer;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterFactory;
import monsterwars.monster.strategy.MonsterPlacementStrategy;
import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MonsterDeployer {

    private final MonsterFactory monsterFactory;
    private final MonsterPlacementStrategy monsterPlacementStrategy;

    @Inject
    public MonsterDeployer(final MonsterFactory monsterFactory, final MonsterPlacementStrategy monsterPlacementStrategy) {
        this.monsterFactory = monsterFactory;
        this.monsterPlacementStrategy = monsterPlacementStrategy;
    }

    public void deploy(final Long numberOfMonsters, final MonsterLocations locations) {
        final Set<Town> towns = locations.getTowns();
        int numberOfTowns = getNumberOfTowns(towns);
        ArrayList<Town> listOfTowns = convertSetOfTownsToList(towns);
        for (int i = 0; i < numberOfMonsters; i++) {
            Monster monster = createMonster(i);
            int itemIndex = monsterPlacementStrategy.getIndex(numberOfTowns);
            getMonstersList(locations, listOfTowns, itemIndex).add(monster);
        }
    }

    private int getNumberOfTowns(final Set<Town> towns) {
        return towns.size();
    }

    private List<Monster> getMonstersList(MonsterLocations locations, ArrayList<Town> listOfTowns, int itemIndex) {
        return locations.getListOfMonsters(listOfTowns.get(itemIndex));
    }

    private Monster createMonster(int i) {
        return monsterFactory.create(i);
    }

    private ArrayList<Town> convertSetOfTownsToList(final Set<Town> towns) {
        return new ArrayList<>(towns);
    }
}
