package monsterwars.monster.strategy;

import java.util.Random;

/**
 * Selects a random index.
 */
public class RandomMonsterPlacementStrategy implements MonsterPlacementStrategy {

    @Override
    public int getIndex(int upperLimit) {
        return new Random().nextInt(upperLimit);
    }
}
