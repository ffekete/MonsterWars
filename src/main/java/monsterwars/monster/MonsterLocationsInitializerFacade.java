package monsterwars.monster;

import com.google.inject.Inject;
import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.initializer.LocationsInitializer;

import java.util.Set;

/**
 * Helps creating a {@link MonsterLocations} instance.
 */
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

    /**
     * Initializes {@link MonsterLocations}.
     *
     * @param numberOfMonsters number of monsters to place.
     * @param towns            possible towns where monsters may roam.
     * @return created {@link MonsterLocations} instance.
     */
    public MonsterLocations init(final Integer numberOfMonsters, Set<String> towns) {
        MonsterLocations locations = new MonsterLocations(locationsFactory.create());
        locationsInitializer.initialize(locations, towns);
        monsterDeployer.deployAll(numberOfMonsters, locations);
        return locations;
    }
}
