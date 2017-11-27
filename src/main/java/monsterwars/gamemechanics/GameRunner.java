package monsterwars.gamemechanics;

import com.google.inject.Inject;
import monsterwars.gamemechanics.calculator.MonsterFightCalculator;
import monsterwars.gamemechanics.calculator.MovementCalculator;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;

import java.util.List;

public class GameRunner {

    private static final int MAX_NUMBER_OF_STEPS = 10000;

    private final MonsterFightCalculator monsterFightCalculator;
    private final MovementCalculator movementCalculator;

    @Inject
    public GameRunner(MonsterFightCalculator monsterFightCalculator, MovementCalculator movementCalculator) {
        this.monsterFightCalculator = monsterFightCalculator;
        this.movementCalculator = movementCalculator;
    }

    public void runWith(WorldMap worldMap, MonsterLocations monsterLocations, MonsterContainer monsterContainer) {
        int i;
        for (i = 0; i < MAX_NUMBER_OF_STEPS && areThereMonstersStillAlive(monsterContainer); i++) {
            monsterLocations.getTowns().forEach(town -> {
                List<Monster> newListOfMonstersInTown = fightMonstersInTown(worldMap, monsterLocations, town);
                processListOfMonstersInTown(monsterLocations, town, newListOfMonstersInTown);
                if (isOneMonsterInTown(newListOfMonstersInTown))
                    moveThatMonsterToANewTown(worldMap, monsterLocations, town, newListOfMonstersInTown);
            });
        }
    }

    private void processListOfMonstersInTown(MonsterLocations monsterLocations, Town town, List<Monster> newList) {
        monsterLocations.addMonstersToTown(town, newList);
    }

    private List<Monster> fightMonstersInTown(WorldMap worldMap, MonsterLocations monsterLocations, Town town) {
        return monsterFightCalculator.calculate(town, worldMap, monsterLocations);
    }

    private boolean areThereMonstersStillAlive(MonsterContainer monsterContainer) {
        return monsterContainer.getNumberOfMonsters() >= 2;
    }

    private void moveThatMonsterToANewTown(WorldMap worldMap, MonsterLocations monsterLocations, Town town, List<Monster> newList) {
        movementCalculator.moveMonster(newList.get(0), town, worldMap.getMap().get(town), monsterLocations);
    }

    private boolean isOneMonsterInTown(List<Monster> newList) {
        return newList.size() == 1;
    }
}
