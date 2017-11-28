package monsterwars.monster.strategy;

/**
 * Selects an index.
 */
public interface MonsterPlacementStrategy {

    /**
     * Selects a town by index.
     *
     * @param upperLimit upper limit for the index.
     * @return chosen index.
     */
    int getIndex(int upperLimit);
}
