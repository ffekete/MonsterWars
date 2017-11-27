package monsterwars.monster;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import monsterwars.monster.factory.MonsterListFactory;

import java.util.List;

/**
 * A {@link List} of {@link Monster} instances.
 */
@Singleton
public class MonsterContainer {

    private List<Monster> monsterList;

    @Inject
    public MonsterContainer(MonsterListFactory monsterListFactory) {
        this.monsterList = monsterListFactory.createEmpty();
    }

    /**
     * Removes a list of monsters from the list.
     * @param monsters to be removed.
     */
    public void removeMonsters(final List<Monster> monsters) {
        monsterList.removeAll(monsters);
    }

    /**
     * Adds a monster to the list.
     * @param monster
     */
    public void addMonster(final Monster monster) {
        monsterList.add(monster);
    }

    /**
     * Gets the number of monsters in the list.
     * @return sum of monsters.
     */
    public Long getNumberOfMonsters() {
        return Long.valueOf(monsterList.size());
    }
}
