package monsterwars.main;

import monsterwars.monster.MonsterLocations;
import monsterwars.monster.MonsterLocationsInitializerFacade;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.facade.GameInitializerFacade;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        LocalDateTime time = LocalDateTime.now();

        GameInitializerFacade gameInitializerFacade = new GameInitializerFacade();
        MonsterLocationsInitializerFacade monsterLocationsInitializerFacade = new MonsterLocationsInitializerFacade();

        WorldMap worldMap = gameInitializerFacade.init();
        MonsterLocations monsterLocations = monsterLocationsInitializerFacade.init(1000, worldMap.getMap().keySet());

        monsterLocations.getLocations().forEach((town, monsters) -> System.out.println(town.getName() + " " + monsters));

        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Ms: " + time.until(endTime, ChronoUnit.MILLIS));
    }
}
