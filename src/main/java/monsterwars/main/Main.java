package monsterwars.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import monsterwars.gamemechanics.GameRunner;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.MonsterLocationsInitializerFacade;
import monsterwars.monster.MonsterModule;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.WorldMapModule;
import monsterwars.worldmap.facade.WorldMapInitializerFacade;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new MonsterModule(), new WorldMapModule());
        WorldMapInitializerFacade worldMapInitializerFacade = injector.getInstance(WorldMapInitializerFacade.class);
        MonsterLocationsInitializerFacade monsterLocationsInitializerFacade = injector.getInstance(MonsterLocationsInitializerFacade.class);
        GameRunner gameRunner = injector.getInstance(GameRunner.class);
        WorldMap worldMap = worldMapInitializerFacade.init();
        MonsterLocations monsterLocations = monsterLocationsInitializerFacade.init(1000L, worldMap.getMap().keySet());
        gameRunner.runWith(worldMap, monsterLocations);
        worldMap.getMap().keySet().forEach(town -> {
            System.out.print(town.getName() + " ");
            worldMap.getMap().get(town).forEach((directions, town1) -> System.out.print(directions.getName() + ": " + town1.getName() + " "));
            System.out.println("");
        });
    }
}
