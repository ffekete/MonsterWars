package monsterwars.monster.factory;

import monsterwars.monster.Monster;

public class MonsterFactory {

    private static final String MONSTER_NAME_PREFIX = "Monster (";
    private static final String MONSTER_NAME_POSTFIX = ")";

    public Monster create(final int index) {
        return new Monster(MONSTER_NAME_PREFIX + index + MONSTER_NAME_POSTFIX);
    }
}
