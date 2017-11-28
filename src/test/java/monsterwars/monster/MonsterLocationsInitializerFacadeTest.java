package monsterwars.monster;

import monsterwars.monster.deployer.MonsterDeployer;
import monsterwars.monster.factory.LocationsFactory;
import monsterwars.monster.initializer.LocationsInitializer;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

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
        Map<String, List<Monster>> locations = new HashMap<>();
        Set<String> towns = new TreeSet<>(Arrays.asList(TOWN_A_NAME, TOWN_B_NAME, TOWN_C_NAME));
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