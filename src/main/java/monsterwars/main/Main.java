package monsterwars.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.MonsterLocationsInitializerFacade;
import monsterwars.monster.MonsterModule;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.WorldMapModule;
import monsterwars.worldmap.facade.WorldMapInitializerFacade;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        LocalDateTime time = LocalDateTime.now();

        Injector injector = Guice.createInjector(new MonsterModule(), new WorldMapModule());

        WorldMapInitializerFacade worldMapInitializerFacade = injector.getInstance(WorldMapInitializerFacade.class);
        MonsterLocationsInitializerFacade monsterLocationsInitializerFacade = injector.getInstance(MonsterLocationsInitializerFacade.class);

        WorldMap worldMap = worldMapInitializerFacade.init();
        MonsterLocations monsterLocations = monsterLocationsInitializerFacade.init(1000L, worldMap.getMap().keySet());

        monsterLocations.getTowns().forEach(town -> System.out.println(town.getName() + " " + monsterLocations.getListOfMonsters(town)));

        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Ms: " + time.until(endTime, ChronoUnit.MILLIS));
    }
}
