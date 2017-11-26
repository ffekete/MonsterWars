package monsterwars.gamemechanics.calculator;

import monsterwars.monster.Monster;
import monsterwars.monster.factory.MonsterListFactory;

import java.util.List;

public class MonsterFightCalculator {

    private final MonsterListFactory monsterListFactory;

    public MonsterFightCalculator(final MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
    }

    public List<Monster> calculate(final List<Monster> monsters) {
        if(monsters.size() > 1) {
            return monsterListFactory.create();
        }
        return monsters;
    }
}
