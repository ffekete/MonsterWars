package monsterwars.monster;

import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.factory.MonsterFactory;
import monsterwars.monster.initializer.LocationsInitializer;
import monsterwars.worldmap.data.Town;

import java.util.*;

public class MonsterLocationsInitializerFacade {

    private final LocationsFactory locationsFactory;
    private final LocationsInitializer locationsInitializer;
    private final MonsterDeployer monsterDeployer;

    public MonsterLocationsInitializerFacade(final LocationsFactory locationsFactory, final LocationsInitializer locationsInitializer, final MonsterDeployer monsterDeployer) {
        this.locationsFactory = locationsFactory;
        this.locationsInitializer = locationsInitializer;
        this.monsterDeployer = monsterDeployer;
    }

    public MonsterLocations init(final int numberOfMonsters, final Set<Town> towns) {
        MonsterLocations locations = new MonsterLocations(locationsFactory.create());
        locationsInitializer.initialize(locations, towns);
        monsterDeployer.deploy(numberOfMonsters, locations);
        return locations;
    }
}
