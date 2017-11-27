package monsterwars.monster;

import com.google.inject.Inject;
import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.initializer.LocationsInitializer;
import monsterwars.worldmap.data.Town;

import java.util.Set;

public class MonsterLocationsInitializerFacade {

    private final LocationsFactory locationsFactory;
    private final LocationsInitializer locationsInitializer;
    private final MonsterDeployer monsterDeployer;

    @Inject
    public MonsterLocationsInitializerFacade(final LocationsFactory locationsFactory, final LocationsInitializer locationsInitializer, final MonsterDeployer monsterDeployer) {
        this.locationsFactory = locationsFactory;
        this.locationsInitializer = locationsInitializer;
        this.monsterDeployer = monsterDeployer;
    }

    public MonsterLocations init(final Long numberOfMonsters, Set<Town> towns) {
        MonsterLocations locations = new MonsterLocations(locationsFactory.create());
        locationsInitializer.initialize(locations, towns);
        monsterDeployer.deployAll(numberOfMonsters, locations);
        return locations;
    }
}
