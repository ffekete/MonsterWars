package monsterwars.monster.factory;

import monsterwars.monster.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for creating a list of {@link Monster} instances.
 */
public class MonsterListFactory {

    /**
     * Creates empty list of {@link Monster}.
     *
     * @return list.
     */
    public List<Monster> createEmpty() {
        return new ArrayList<>();
    }
}
