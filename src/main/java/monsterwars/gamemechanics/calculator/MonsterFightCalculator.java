package monsterwars.gamemechanics.calculator;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;

import java.util.List;

/**
 * Calculates the aftermath of monsters fight.
 */
public class MonsterFightCalculator {

    private final MonsterListFactory monsterListFactory;

    @Inject
    public MonsterFightCalculator(final MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
    }

    /**
     * Destroys town and monsters during a fight.
     *
     * @param town             to destroy.
     * @param worldMap         to remove town from the map.
     * @param monsterLocations to remove monsters as well.
     * @return list of monsters after the fight calculation in the town.
     */
    public List<Monster> calculate(Town town, WorldMap worldMap, MonsterLocations monsterLocations) {
        List<Monster> monsters = monsterLocations.getListOfMonsters(town);
        if (areThereMoreMonstersInThisTown(monsters)) {
            printMonsterFightMessage(town, monsters);
            destroyTown(town, worldMap);
            return getEmptyMonsterList();
        }
        return monsters;
    }

    private List<Monster> getEmptyMonsterList() {
        return monsterListFactory.createEmpty();
    }

    private void destroyTown(Town town, WorldMap worldMap) {
        worldMap.removeTownFromWorldMap(town);
    }

    private void printMonsterFightMessage(Town town, List<Monster> monsters) {
        System.out.print(town.getName() + " has been destroyed by ");
        for (int i = 0; i < monsters.size(); i++) {
            System.out.print(monsters.get(i).getName());
            if (i != monsters.size() - 1) System.out.print(" and ");
        }
        System.out.println("!");
    }

    private boolean areThereMoreMonstersInThisTown(List<Monster> monsters) {
        return monsters.size() > 1;
    }
}
