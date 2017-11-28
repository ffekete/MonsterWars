package monsterwars.monster.strategy;

/**
 * Strategy for placing {@link monsterwars.monster.Monster} instances on {@link monsterwars.monster.MonsterLocations}.
 * Selects an index related to a {@link monsterwars.worldmap.data.Town}.
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
