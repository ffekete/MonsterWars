package monsterwars.monster.deployer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterFactory;
import monsterwars.monster.strategy.MonsterPlacementStrategy;
import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Deploys {@link Monster}s in {@link MonsterLocations}.
 */
@Singleton
public class MonsterDeployer {

    private final MonsterFactory monsterFactory;
    private final MonsterPlacementStrategy monsterPlacementStrategy;

    @Inject
    public MonsterDeployer(final MonsterFactory monsterFactory, final MonsterPlacementStrategy monsterPlacementStrategy) {
        this.monsterFactory = monsterFactory;
        this.monsterPlacementStrategy = monsterPlacementStrategy;
    }

    /**
     * Deploys all monsters.
     * @param numberOfMonsters how many monsters to deploy?
     * @param locations possible locations to deploy.
     * @param monsterContainer list of monsters.
     */
    public void deployAll(Long numberOfMonsters, MonsterLocations locations, MonsterContainer monsterContainer) {
        int numberOfTowns = getNumberOfTowns(getPossibleTowns(locations));
        List<Town> listOfTowns = convertSetOfTownsToList(getPossibleTowns(locations));
        for (int i = 0; i < numberOfMonsters; i++) {
            Monster monster = createMonster(i);
            monsterContainer.addMonster(monster);
            int itemIndex = monsterPlacementStrategy.getIndex(numberOfTowns);
            getMonstersList(locations, listOfTowns, itemIndex).add(monster);
        }
    }

    private Set<Town> getPossibleTowns(MonsterLocations locations) {
        return locations.getTowns();
    }

    private int getNumberOfTowns(Set<Town> towns) {
        return towns.size();
    }

    private List<Monster> getMonstersList(MonsterLocations locations, List<Town> listOfTowns, int itemIndex) {
        return locations.getListOfMonsters(listOfTowns.get(itemIndex));
    }

    private Monster createMonster(int i) {
        return monsterFactory.create(i);
    }

    private ArrayList<Town> convertSetOfTownsToList(Set<Town> towns) {
        return new ArrayList<>(towns);
    }
}
