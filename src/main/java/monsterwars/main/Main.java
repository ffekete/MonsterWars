package monsterwars.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import monsterwars.gamemechanics.GameMechanicsModule;
import monsterwars.gamemechanics.GameRunner;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.MonsterLocationsInitializerFacade;
import monsterwars.monster.MonsterModule;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.decorator.WorldMapDecorator;
import monsterwars.worldmap.facade.WorldMapInitializerFacade;

import java.io.IOException;

public class Main {

    private static final String NO_PARAMETERS_SUPPLIED_ERROR_MESSAGE = "No parameter was supplied! Please supply the number of monsters as a positive int number!";

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println(NO_PARAMETERS_SUPPLIED_ERROR_MESSAGE);
        } else {
            Long numberOfMonsters = InputArgumentValidator.validate(args);
            if (numberOfMonsters != null && numberOfMonsters > 0) {
                // Initializing Environment
                Injector injector = Guice.createInjector(new MonsterModule(), new GameMechanicsModule());
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
}
