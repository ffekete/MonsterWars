package monsterwars.gamemechanics;

import com.google.inject.Inject;
import monsterwars.gamemechanics.calculator.MonsterFightCalculator;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;

import java.util.List;

public class GameRunner {

    private final MonsterFightCalculator monsterFightCalculator;
    private final MovementCalculator movementCalculator;

    @Inject
    public GameRunner(MonsterFightCalculator monsterFightCalculator, MovementCalculator movementCalculator) {
        this.monsterFightCalculator = monsterFightCalculator;
        this.movementCalculator = movementCalculator;
    }

    public void runWith(WorldMap worldMap, MonsterLocations monsterLocations, MonsterContainer monsterContainer) {
        for(int i = 0; i < 10000; i++) {
            if(monsterContainer.getNumberOfMonsters() < 2) break;
            monsterLocations.getTowns().forEach(town -> {
                List<Monster> newList = monsterFightCalculator.calculate(monsterLocations.getListOfMonsters(town), town, worldMap, worldMap.getMap().get(town));
                monsterLocations.addMonstersToTown(town, newList);
            });
            monsterContainer.getContainerAsStream().forEach(monster -> {
                Town townWherMonsterStays = monsterLocations.getTowns().stream().filter(town -> monsterLocations.getListOfMonsters(town).contains(monster)).findFirst().get();
                movementCalculator.moveMonster(monster, townWherMonsterStays, worldMap.getMap().get(townWherMonsterStays), monsterLocations);
            });
        }
    }
}
