package monsterwars.gamemechanics.calculator;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MovementCalculator {

    public void moveMonster(Monster monster, Town actualTown, Map<Directions, Town> possibleDirections, MonsterLocations monsterLocations) {
        Directions directionToMove = new ArrayList<>(possibleDirections.keySet()).get(new Random().nextInt(possibleDirections.keySet().size()));
        monsterLocations.addMonsterToTown(possibleDirections.get(directionToMove), monster);
        monsterLocations.removeMonsterFromTown(actualTown, monster);
    }
}
