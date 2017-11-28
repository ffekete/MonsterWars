package monsterwars.monster.initializer;

import com.google.inject.Inject;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;

import java.util.List;
import java.util.Set;

/**
 * Used for initializing {@link MonsterLocations}.
 */
public class LocationsInitializer {

    private final MonsterListFactory monsterListFactory;

    @Inject
    public LocationsInitializer(final MonsterListFactory monsterListFactory) {
        this.monsterListFactory = monsterListFactory;
    }

    /**
     * Initializes {@link MonsterLocations}.
     *
     * @param locations to initialize.
     * @param towns     set of towns where monsters may roam.
     */
    public void initialize(MonsterLocations locations, final Set<String> towns) {
        towns.forEach(town -> locations.setMonstersListToTown(town, getEmptyListOfMonsters()));
    }

    private List<Monster> getEmptyListOfMonsters() {
        return monsterListFactory.createEmpty();
    }
}
