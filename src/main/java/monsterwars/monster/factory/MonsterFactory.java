package monsterwars.monster.factory;

import monsterwars.monster.Monster;

/**
 * For creating {@link Monster} instance.
 */
public class MonsterFactory {

    private static final String MONSTER_NAME_PREFIX = "Monster (";
    private static final String MONSTER_NAME_POSTFIX = ")";

    /**
     * Creates {@link Monster} instance.
     *
     * @param number a number representing the index of the monster. Used for naming.
     * @return created instance.
     */
    public Monster create(final int number) {
        return new Monster(MONSTER_NAME_PREFIX + number + MONSTER_NAME_POSTFIX);
    }
}
