package monsterwars.monster.deployer;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterFactory;
import monsterwars.monster.strategy.MonsterPlacementStrategy;
import monsterwars.worldmap.data.Town;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;

public class MonsterDeployerTest {

    private static final int NUMBER_OF_TOWNS = 3;
    private static final String MONSTER_0_NAME = "0";
    private static final String MONSTER_1_NAME = "1";
    private static final String MONSTER_2_NAME = "2";
    private static final int FIRST_INDEX_OF_TOWN = 0;
    private static final int SECOND_INDEX_OF_TOWN = 2;
    private static final String TOWN_A_NAME = "A";
    private static final String TOWN_B_NAME = "B";
    private static final String TOWN_C_NAME = "C";
    private static final int NUMBER_OF_MONSTERS = 3;

    private final IMocksControl control = EasyMock.createStrictControl();

    private MonsterFactory monsterFactory;
    private MonsterPlacementStrategy monsterPlacementStrategy;
    private MonsterDeployer underTest;

    @BeforeClass
    public void setUp() {
        monsterFactory = control.createMock(MonsterFactory.class);
        monsterPlacementStrategy = control.createMock(MonsterPlacementStrategy.class);
        underTest = new MonsterDeployer(monsterFactory, monsterPlacementStrategy);
    }

    @Test
    public void testShouldDeployMonsters() {
        // GIVEN
        Monster monster0 = new Monster(MONSTER_0_NAME);
        Monster monster1 = new Monster(MONSTER_1_NAME);
        Monster monster2 = new Monster(MONSTER_2_NAME);
        expect(monsterFactory.create(0)).andReturn(monster0);
        expect(monsterPlacementStrategy.getIndex(NUMBER_OF_TOWNS)).andReturn(FIRST_INDEX_OF_TOWN);
        expect(monsterFactory.create(1)).andReturn(monster1);
        expect(monsterPlacementStrategy.getIndex(NUMBER_OF_TOWNS)).andReturn(SECOND_INDEX_OF_TOWN);
        expect(monsterFactory.create(2)).andReturn(monster2);
        expect(monsterPlacementStrategy.getIndex(NUMBER_OF_TOWNS)).andReturn(SECOND_INDEX_OF_TOWN);
        Town townA = new Town(TOWN_A_NAME);
        Town townB = new Town(TOWN_B_NAME);
        Town townC = new Town(TOWN_C_NAME);
        Map<Town, List<Monster>> locationsMap = new HashMap<>();
        locationsMap.put(townA, new ArrayList<>());
        locationsMap.put(townB, new ArrayList<>());
        locationsMap.put(townC, new ArrayList<>());
        MonsterLocations monsterLocations = new MonsterLocations(locationsMap);
        control.replay();
        // WHEN
        underTest.deploy(NUMBER_OF_MONSTERS, monsterLocations);
        // THEN
        control.verify();
        int actualNumberOfMonsters = monsterLocations.getTowns().stream().mapToInt(town -> monsterLocations.getListOfMonsters(town).size()).sum();
        assertEquals(actualNumberOfMonsters, NUMBER_OF_MONSTERS);
        long nrOfTownsWithTwoMonsters = monsterLocations.getTowns().stream().filter(town -> monsterLocations.getListOfMonsters(town).size() == 2).count();
        assertEquals(nrOfTownsWithTwoMonsters, 1);
        long nrOfTownsWithOneMonsters = monsterLocations.getTowns().stream().filter(town -> monsterLocations.getListOfMonsters(town).size() == 1).count();
        assertEquals(nrOfTownsWithOneMonsters, 1);
        long nrOfTownsWithZeroMonsters = monsterLocations.getTowns().stream().filter(town -> monsterLocations.getListOfMonsters(town).size() == 0).count();
        assertEquals(nrOfTownsWithZeroMonsters, 1);
    }


}