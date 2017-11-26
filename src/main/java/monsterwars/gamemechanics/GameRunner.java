package monsterwars.gamemechanics;

import com.google.inject.Inject;
import monsterwars.gamemechanics.calculator.MonsterFightCalculator;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.WorldMap;

import java.util.List;

public class GameRunner {

    private final MonsterFightCalculator monsterFightCalculator;
    private final MovementCalculator movementCalculator;

    @Inject
    public GameRunner(MonsterFightCalculator monsterFightCalculator, MovementCalculator movementCalculator) {
        this.monsterFightCalculator = monsterFightCalculator;
        this.movementCalculator = movementCalculator;
    }

    public void runWith(final WorldMap worldMap, final MonsterLocations monsterLocations, final MonsterContainer monsterContainer) {
        for(int i = 0; i < 10000; i++) {
            if(monsterContainer.getNumberOfMonsters() == 0) break;

            monsterLocations.getTowns().forEach(town -> {
                List<Monster> newList = monsterFightCalculator.calculate(monsterLocations.getListOfMonsters(town));
                monsterLocations.addMonstersToTown(town, newList);
            });
        }
    }
}
