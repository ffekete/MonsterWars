package monsterwars.gamemechanics.calculator;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Calculates {@link Monster} movements on map.
 */
public class MovementCalculator {

    /**
     * Moves one monster on the map.
     * @param monster to move.
     * @param actualTown where is the monster now?
     * @param possibleDirections where can the monster move?
     * @param monsterLocations Storage where towns are linked with monsters.
     */
    public void moveMonster(Monster monster, Town actualTown, Map<Directions, Town> possibleDirections, MonsterLocations monsterLocations) {
        Directions directionToMove = getRandomDirectionToMove(possibleDirections);
        monsterLocations.addMonsterToTown(getDestinationTown(possibleDirections, directionToMove), monster);
        monsterLocations.removeMonsterFromTown(actualTown, monster);
    }

    private Town getDestinationTown(Map<Directions, Town> possibleDirections, Directions directionToMove) {
        return possibleDirections.get(directionToMove);
    }

    private Directions getRandomDirectionToMove(Map<Directions, Town> possibleDirections) {
        return new ArrayList<>(getPossibleDirections(possibleDirections)).get(getRandomIndex(possibleDirections));
    }

    private Set<Directions> getPossibleDirections(Map<Directions, Town> possibleDirections) {
        return possibleDirections.keySet();
    }

    private int getRandomIndex(Map<Directions, Town> possibleDirections) {
        return new Random().nextInt(getPossibleDirections(possibleDirections).size());
    }
}
