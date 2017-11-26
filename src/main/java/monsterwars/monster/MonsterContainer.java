package monsterwars.monster;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import monsterwars.monster.factory.MonsterListFactory;

import java.util.List;
import java.util.stream.Stream;

@Singleton
public class MonsterContainer {

    private MonsterListFactory monsterListFactory;
    private List<Monster> monsterList;

    @Inject
    public MonsterContainer(MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
        this.monsterList = monsterListFactory.createEmpty();
    }

    public void removeMonsters(final List<Monster> monsters) {
        monsterList.removeAll(monsters);
    }

    public void addMonster(final Monster monster) {
        monsterList.add(monster);
    }

    public Long getNumberOfMonsters() {
        return Long.valueOf(monsterList.size());
    }

    public Stream<Monster> getContainerAsStream() {
        return monsterList.stream();
    }
}
