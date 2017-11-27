package monsterwars.monster.strategy;

/**
 * Stratey gor placing {@link monsterwars.monster.Monster} instances on {@link monsterwars.monster.MonsterLocations}.
 */
public interface MonsterPlacementStrategy {

    /**
     * Selects a monster index.
     *
     * @param upperLimit upper limit for the index.
     * @return chosen index.
     */
    int getIndex(int upperLimit);
}
