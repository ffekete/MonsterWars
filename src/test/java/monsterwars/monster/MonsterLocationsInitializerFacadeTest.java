package monsterwars.monster;

import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.initializer.LocationsInitializer;
import monsterwars.worldmap.data.Town;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

public class MonsterLocationsInitializerFacadeTest {

    private static final String TOWN_A_NAME = "A";
    private static final String TOWN_B_NAME = "B";
    private static final String TOWN_C_NAME = "C";
    private static final long NUMBER_OF_MONSTERS = 10L;

    private final IMocksControl control = EasyMock.createStrictControl();

    private MonsterLocationsInitializerFacade underTest;
    private LocationsFactory locationsFactory;
    private MonsterDeployer monsterDeployer;
    private LocationsInitializer locationsInitializer;
    private MonsterContainer monsterContainer;

    @BeforeClass
    public void setUp() {
        locationsFactory = control.createMock(LocationsFactory.class);
        monsterDeployer = control.createMock(MonsterDeployer.class);
        locationsInitializer = control.createMock(LocationsInitializer.class);
        monsterContainer = control.createMock(MonsterContainer.class);
        underTest = new MonsterLocationsInitializerFacade(locationsFactory, locationsInitializer, monsterDeployer, monsterContainer);
    }

    @Test
    public void testInitShouldCallAllMethodsAndCreateMonstersLocations() {
        // GIVEN
        ConcurrentMap<Town, List<Monster>> locations = new ConcurrentHashMap<>();
        Town townA = new Town(TOWN_A_NAME);
        Town townB = new Town(TOWN_B_NAME);
        Town townC = new Town(TOWN_C_NAME);
        Set<Town> towns = new TreeSet<>(Arrays.asList(townA, townB, townC));
        expect(locationsFactory.create()).andReturn(locations);
        locationsInitializer.initialize(anyObject(), anyObject());
        monsterDeployer.deployAll(anyObject(), anyObject(), anyObject());
        EasyMock.expectLastCall();
        control.replay();
        // WHEN
        underTest.init(NUMBER_OF_MONSTERS, towns);
        // THEN
        control.verify();
    }
}