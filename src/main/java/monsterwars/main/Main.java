package monsterwars.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import monsterwars.gamemechanics.GameRunner;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.MonsterLocationsInitializerFacade;
import monsterwars.monster.MonsterModule;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.decorator.WorldMapDecorator;
import monsterwars.worldmap.facade.WorldMapInitializerFacade;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("No parameter was supplied! Please supply the number of monsters as a positive int number!");
        } else {
            Long numberOfMonsters = validateInputAndGetValue(args);
            if ( numberOfMonsters != null && numberOfMonsters > 0) {
                // Initializing Environment
                Injector injector = Guice.createInjector(new MonsterModule());
                WorldMapInitializerFacade worldMapInitializerFacade = injector.getInstance(WorldMapInitializerFacade.class);
                MonsterLocationsInitializerFacade monsterLocationsInitializerFacade = injector.getInstance(MonsterLocationsInitializerFacade.class);
                GameRunner gameRunner = injector.getInstance(GameRunner.class);
                // Initializing game world
                WorldMap worldMap = worldMapInitializerFacade.init();
                MonsterLocations monsterLocations = monsterLocationsInitializerFacade.init(numberOfMonsters, worldMap.getMap().keySet());
                // running game
                gameRunner.runWith(worldMap, monsterLocations);
                // cleaning up
                new WorldMapDecorator(worldMap).printRemainingWorldMap();
            }
        }
    }

    private static Long validateInputAndGetValue(String[] args) {
        Long numberOfMonsters = null;
        try {
            numberOfMonsters = Long.valueOf(args[0]);
        } catch (NumberFormatException exception) {
            System.out.println("Not a valid number as a parameter: " + args[0]);
        }
        if(numberOfMonsters != null && numberOfMonsters < 1) System.out.println("Too few monsters are specified: " + numberOfMonsters);
        return numberOfMonsters;
    }
}
