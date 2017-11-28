package monsterwars.monster.initializer;

import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link LocationsInitializer}.
 */
public class LocationsInitializerTest {

    private static final int NUMBER_OF_TOWNS = 3;
    private static final String TOWN_A_NAME = "A";
    private static final String TOWN_B_NAME = "B";
    private static final String TOWN_C_NAME = "C";

    private final IMocksControl control = EasyMock.createControl();

    private MonsterListFactory monsterListFactory;
    private LocationsInitializer underTest;

    @BeforeClass
    public void setUp() {
        monsterListFactory = control.createMock(MonsterListFactory.class);
        underTest = new LocationsInitializer(monsterListFactory);
    }

    @Test
    public void testShouldInitializeMapWithEmptyListOfMonstersForAllTowns() {
        // GIVEN
        MonsterLocations monsterLocations = new MonsterLocations(new ConcurrentHashMap<>());
        Set<String> towns = new TreeSet<>(Arrays.asList(TOWN_A_NAME, TOWN_B_NAME, TOWN_C_NAME));
        expect(monsterListFactory.createEmpty()).andReturn(Collections.emptyList()).times(NUMBER_OF_TOWNS);
        control.replay();
        // WHEN
        underTest.initialize(monsterLocations, towns);
        // THEN
        control.verify();
        long nrOfEmptyTowns = monsterLocations.getTowns().parallelStream().filter(town -> monsterLocations.getListOfMonsters(town).isEmpty()).count();
        assertEquals(nrOfEmptyTowns, NUMBER_OF_TOWNS);
    }
}