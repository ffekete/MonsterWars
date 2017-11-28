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
        int index = new Random().nextInt(possibleDirections.keySet().size());
        return new ArrayList<>(possibleDirections.keySet()).get(index);
    }
}
