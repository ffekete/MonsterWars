package monsterwars.gamemechanics.calculator;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;
import monsterwars.worldmap.decorator.WorldMapDecorator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MonsterFightCalculator {

    private final MonsterListFactory monsterListFactory;
    private final MonsterContainer monsterContainer;

    @Inject
    public MonsterFightCalculator(final MonsterListFactory monsterListFactory, final MonsterContainer monsterContainer) {
        this.monsterListFactory = monsterListFactory;
        this.monsterContainer = monsterContainer;
    }

    public List<Monster> calculate(Town town, WorldMap worldMap, MonsterLocations monsterLocations) {
        List<Monster> monsters = monsterLocations.getListOfMonsters(town);
        if (monsters.size() > 1) {
            monsters.forEach(monster -> System.out.print(monster.getName() + " and "));
            System.out.println("killed each other  in town " + town.getName() + ".");
            monsterContainer.removeMonsters(monsters);
            new WorldMapDecorator(worldMap).removeTownFromWorldMap(town);
            monsterLocations.removeTown(town);
            System.out.println("Town " + town.getName() + " destroyed! " + worldMap.getMap().containsKey(town));
            return monsterListFactory.createEmpty();
        }
        return monsters;
    }
}
