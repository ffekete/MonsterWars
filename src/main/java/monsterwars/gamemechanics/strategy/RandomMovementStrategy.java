package monsterwars.gamemechanics.strategy;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * For random movement direction selection.
 */
public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public Directions getDirection(Map<Directions, Town> possibleDirections) {
        int index = getRandomIndex(possibleDirections);
        return getDirection(possibleDirections, index);
    }

    private int getRandomIndex(Map<Directions, Town> possibleDirections) {
        return new Random().nextInt(possibleDirections.keySet().size());
    }

    private Directions getDirection(Map<Directions, Town> possibleDirections, int index) {
        return new ArrayList<>(possibleDirections.keySet()).get(index);
    }
}
