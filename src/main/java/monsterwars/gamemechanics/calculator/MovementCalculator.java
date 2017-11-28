package monsterwars.gamemechanics.calculator;

import com.google.inject.Inject;
import monsterwars.gamemechanics.strategy.MovementStrategy;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.data.Directions;

import java.util.Map;

/**
 * Calculates {@link Monster} movements on map.
 */
public class MovementCalculator {

    private final MovementStrategy movementStrategy;

    @Inject
    public MovementCalculator(final MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /**
     * Moves one monster on the map.
     *
     * @param monster            to move.
     * @param actualTown         where is the monster now?
     * @param possibleDirections where can the monster move?
     * @param monsterLocations   Storage where towns are linked with monsters.
     */
    public void moveMonster(Monster monster, String actualTown, Map<Directions, String> possibleDirections, MonsterLocations monsterLocations) {
        Directions directionToMove = getRandomDirectionToMove(possibleDirections);
        if (directionToMove != null) {
            monsterLocations.addMonsterToTown(getDestinationTown(possibleDirections, directionToMove), monster);
            monsterLocations.removeMonsterFromTown(actualTown, monster);
        }
    }

    private String getDestinationTown(Map<Directions, String> possibleDirections, Directions directionToMove) {
        return possibleDirections.get(directionToMove);
    }

    private Directions getRandomDirectionToMove(Map<Directions, String> possibleDirections) {
        return movementStrategy.getDirection(possibleDirections);
    }
}
