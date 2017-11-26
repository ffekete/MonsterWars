package monsterwars.main;

import monsterwars.monster.MonsterLocations;
import monsterwars.monster.MonsterLocationsInitializerFacade;
import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.factory.MonsterFactory;
import monsterwars.monster.initializer.LocationsInitializer;
import monsterwars.monster.strategy.RandomMonsterPlacementStrategy;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.facade.GameInitializerFacade;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException {
        LocalDateTime time = LocalDateTime.now();

        GameInitializerFacade gameInitializerFacade = new GameInitializerFacade();
        MonsterLocationsInitializerFacade monsterLocationsInitializerFacade = new MonsterLocationsInitializerFacade(new LocationsFactory(), new LocationsInitializer(), new MonsterDeployer(new MonsterFactory(), new RandomMonsterPlacementStrategy()));

        WorldMap worldMap = gameInitializerFacade.init();
        MonsterLocations monsterLocations = monsterLocationsInitializerFacade.init(1000, worldMap.getMap().keySet());

        monsterLocations.getTowns().forEach(town -> System.out.println(town.getName() + " " + monsterLocations.getListOfMonsters(town)));

        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Ms: " + time.until(endTime, ChronoUnit.MILLIS));
    }
}
