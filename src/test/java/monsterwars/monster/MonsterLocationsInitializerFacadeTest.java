package monsterwars.monster;

import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.initializer.LocationsInitializer;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

public class MonsterLocationsInitializerFacadeTest {

    private static final String TOWN_A_NAME = "A";
    private static final String TOWN_B_NAME = "B";
    private static final String TOWN_C_NAME = "C";
    private static final int NUMBER_OF_MONSTERS = 10;

    private final IMocksControl control = EasyMock.createStrictControl();

    private MonsterLocationsInitializerFacade underTest;
    private LocationsFactory locationsFactory;
    private MonsterDeployer monsterDeployer;
    private LocationsInitializer locationsInitializer;

    @BeforeClass
    public void setUp() {
        locationsFactory = control.createMock(LocationsFactory.class);
        monsterDeployer = control.createMock(MonsterDeployer.class);
        locationsInitializer = control.createMock(LocationsInitializer.class);
        underTest = new MonsterLocationsInitializerFacade(locationsFactory, locationsInitializer, monsterDeployer);
    }

    @Test
    public void testInitShouldCallAllMethodsAndCreateMonstersLocations() {
        // GIVEN
        ConcurrentMap<String, List<Monster>> locations = new ConcurrentHashMap<>();
        String townA =TOWN_A_NAME;
        String townB = TOWN_B_NAME;
        String townC = TOWN_C_NAME;
        Set<String> towns = new TreeSet<>(Arrays.asList(townA, townB, townC));
        expect(locationsFactory.create()).andReturn(locations);
        locationsInitializer.initialize(anyObject(), anyObject());
        monsterDeployer.deployAll(anyObject(), anyObject());
        EasyMock.expectLastCall();
        control.replay();
        // WHEN
        underTest.init(NUMBER_OF_MONSTERS, towns);
        // THEN
        control.verify();
    }
}