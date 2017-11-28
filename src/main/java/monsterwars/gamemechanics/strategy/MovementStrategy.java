package monsterwars.gamemechanics.strategy;

import monsterwars.worldmap.data.Directions;

import java.util.Map;

/**
 * Used to determine the direction for monsters to move on the world map.
 */
public interface MovementStrategy {

    /**
     * Get a direction.
     *
     * @param possibleDirections all possible directions.
     * @return one direction form the list.
     */
    Directions getDirection(Map<Directions, String> possibleDirections);
}
