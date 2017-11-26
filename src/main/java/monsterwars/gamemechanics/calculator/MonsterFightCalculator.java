package monsterwars.gamemechanics.calculator;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;

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

    public List<Monster> calculate(List<Monster> monsters, Town town, WorldMap worldMap, Map<Directions, Town> directionsMap) {
        if(monsters.size() > 1) {
            monsters.forEach(monster -> System.out.print(monster.getName() + " and "));
            System.out.println("killed each other  in town " + town.getName() + ".");
            monsterContainer.removeMonsters(monsters);
            directionsMap.keySet().forEach(directions -> {
                Town northTown = worldMap.getMap().get(town).get(Directions.NORTH);
                Town southTown = worldMap.getMap().get(town).get(Directions.SOUTH);
                Town eastTown = worldMap.getMap().get(town).get(Directions.EAST);
                Town westTown = worldMap.getMap().get(town).get(Directions.WEST);
                if(northTown != null) worldMap.getMap().get(northTown).remove(Directions.SOUTH);
                if(southTown != null) worldMap.getMap().get(southTown).remove(Directions.NORTH);
                if(eastTown != null) worldMap.getMap().get(eastTown).remove(Directions.WEST);
                if(westTown != null) worldMap.getMap().get(westTown).remove(Directions.EAST);
                directionsMap.keySet().remove(town);
            });
            System.out.println("Town " + town.getName() + " destroyed!");
            return monsterListFactory.createEmpty();
        }
        return monsters;
    }
}
