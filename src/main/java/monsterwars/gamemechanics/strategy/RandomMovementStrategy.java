package monsterwars.gamemechanics.strategy;

import monsterwars.worldmap.data.Directions;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * For random movement direction selection.
 */
public class RandomMovementStrategy implements MovementStrategy {

    @Override
    public Directions getDirection(Map<Directions, String> possibleDirections) {
        if (possibleDirections.size() > 0) {
            int index = getRandomIndex(possibleDirections);
            return getDirection(possibleDirections, index);
        } else {
            return null;
        }
    }

    private int getRandomIndex(Map<Directions, String> possibleDirections) {
        return new Random().nextInt(possibleDirections.keySet().size());
    }

    private Directions getDirection(Map<Directions, String> possibleDirections, int index) {
        return new ArrayList<>(possibleDirections.keySet()).get(index);
    }
}
