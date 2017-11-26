package monsterwars.gamemechanics.calculator;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.factory.MonsterListFactory;

import java.util.List;

public class MonsterFightCalculator {

    private final MonsterListFactory monsterListFactory;
    private final MonsterContainer monsterContainer;

    @Inject
    public MonsterFightCalculator(final MonsterListFactory monsterListFactory, final MonsterContainer monsterContainer) {
        this.monsterListFactory = monsterListFactory;
        this.monsterContainer = monsterContainer;
    }

    public List<Monster> calculate(final List<Monster> monsters) {
        if(monsters.size() > 1) {
            System.out.println("The following monsters killed each other: " + monsters);
            monsterContainer.removeMonsters(monsters);
            return monsterListFactory.createEmpty();
        }
        return monsters;
    }
}
