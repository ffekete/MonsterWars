package monsterwars.gamemechanics;

import com.google.inject.Inject;
import monsterwars.gamemechanics.calculator.MonsterFightCalculator;
import monsterwars.gamemechanics.calculator.MovementCalculator;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.WorldMap;

import java.util.List;

/**
 * Runs game rules.
 */
public class GameRunner {

    private static final int MAX_NUMBER_OF_STEPS = 10000;

    private final MonsterFightCalculator monsterFightCalculator;
    private final MovementCalculator movementCalculator;

    @Inject
    public GameRunner(MonsterFightCalculator monsterFightCalculator, MovementCalculator movementCalculator) {
        this.monsterFightCalculator = monsterFightCalculator;
        this.movementCalculator = movementCalculator;
    }

    /**
     * Runs game rules periodically.
     *
     * @param worldMap         where monsters may roam.
     * @param monsterLocations map of towns where monsters reside.
     */
    public void runWith(WorldMap worldMap, MonsterLocations monsterLocations) {
        int i;
        for (i = 0; i < MAX_NUMBER_OF_STEPS && areThereMonstersStillAlive(monsterLocations); i++) {
            monsterLocations.getLocations().forEach((town, monsterList) -> {
                fightMonstersInTown(worldMap, monsterLocations, town);
                moveMonsterToANewTown(worldMap, monsterLocations, town, monsterList);
            });
        }

    }

    private void fightMonstersInTown(WorldMap worldMap, MonsterLocations monsterLocations, String town) {
        monsterLocations.setMonstersListToTown(town, monsterFightCalculator.calculate(town, worldMap, monsterLocations));
    }

    private boolean areThereMonstersStillAlive(MonsterLocations monsterLocations) {
        return monsterLocations.getTowns().stream().mapToInt(town -> monsterLocations.getListOfMonsters(town).size()).sum() >= 2;
    }

    private void moveMonsterToANewTown(WorldMap worldMap, MonsterLocations monsterLocations, String town, List<Monster> monstersInTown) {
        if (isOneMonsterInTown(monstersInTown)) {
            movementCalculator.moveMonster(monstersInTown.get(0), town, worldMap.getMap().get(town), monsterLocations);
        }
    }

    private boolean isOneMonsterInTown(List<Monster> monsters) {
        return monsters.size() == 1;
    }
}
