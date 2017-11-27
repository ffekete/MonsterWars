package monsterwars.gamemechanics.calculator;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;

import java.util.List;

public class MonsterFightCalculator {

    private final MonsterListFactory monsterListFactory;

    @Inject
    public MonsterFightCalculator(final MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
    }

    public List<Monster> calculate(Town town, WorldMap worldMap, MonsterLocations monsterLocations) {
        List<Monster> monsters = monsterLocations.getListOfMonsters(town);
        if (areThereMoreMonstersInThisTown(monsters)) {
            printMonsterFightMessage(town, monsters);
            destroyTown(town, worldMap);
            printTownDestroyedMessage(town, worldMap);
            return getEmptyMonsterList();
        }
        return monsters;
    }

    private List<Monster> getEmptyMonsterList() {
        return monsterListFactory.createEmpty();
    }

    private void printTownDestroyedMessage(Town town, WorldMap worldMap) {
        System.out.println("Town " + town.getName() + " destroyed! " + worldMap.getMap().containsKey(town));
    }

    private void destroyTown(Town town, WorldMap worldMap) {
        worldMap.removeTownFromWorldMap(town);
    }

    private void printMonsterFightMessage(Town town, List<Monster> monsters) {
        monsters.forEach(monster -> System.out.print(monster.getName() + " and "));
        System.out.println("killed each other  in town " + town.getName() + ".");
    }

    private boolean areThereMoreMonstersInThisTown(List<Monster> monsters) {
        return monsters.size() > 1;
    }
}
