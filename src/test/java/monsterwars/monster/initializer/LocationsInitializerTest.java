package monsterwars.monster.initializer;

import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.data.Town;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class LocationsInitializerTest {

    private static final int NUMBER_OF_TOWNS = 3;
    private static final String TOWN_A_NAME = "A";
    private static final String TOWN_B_NAME = "B";
    private static final String TOWN_C_NAME = "C";
    private final LocationsInitializer underTest = new LocationsInitializer();

    @Test
    public void testShouldInitializeMapWithEmptyListOfMonstersForAllTowns() {
        // GIVEN
        MonsterLocations monsterLocations = new MonsterLocations(new TreeMap<>());
        Town townA = new Town(TOWN_A_NAME);
        Town townB = new Town(TOWN_B_NAME);
        Town townC = new Town(TOWN_C_NAME);
        Set<Town> towns = new TreeSet<>(Arrays.asList(townA, townB, townC));
        // WHEN
        underTest.initialize(monsterLocations, towns);
        // THEN
        long nrOfEmptyTowns = monsterLocations.getTowns().stream().filter(town -> monsterLocations.getListOfMonsters(town).isEmpty()).count();
        assertEquals(nrOfEmptyTowns, NUMBER_OF_TOWNS);
    }
}