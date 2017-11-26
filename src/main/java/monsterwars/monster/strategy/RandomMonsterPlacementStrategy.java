package monsterwars.monster.strategy;

import java.util.Random;

public class RandomMonsterPlacementStrategy implements MonsterPlacementStrategy {

    @Override
    public int getIndex(int upperLimit) {
        return new Random().nextInt(upperLimit);
    }
}
