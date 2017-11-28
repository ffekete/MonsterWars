package monsterwars.gamemechanics.calculator;

import monsterwars.gamemechanics.strategy.MovementStrategy;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.data.Directions;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Unit tests for {@link MovementCalculator}.
 */
public class MovementCalculatorTest {

    private static final String MONSTER_A_NAME = "A";
    private static final String TOWN_A_NAME = "A";
    private static final String TOWN_B_NAME = "B";
    private static final String TOWN_C_NAME = "C";
    private static final String TOWN_D_NAME = "D";
    private static final String TOWN_E_NAME = "E";

    private final IMocksControl control = EasyMock.createControl();

    private MovementStrategy movementStrategy;
    private MovementCalculator underTest;

    @BeforeClass
    public void setUp() {
        movementStrategy = control.createMock(MovementStrategy.class);
        underTest = new MovementCalculator(movementStrategy);
    }

    @Test
    public void testMoveMonsterShouldMoveAllOfThem() {
        // GIVEN
        Monster monsterA = new Monster(MONSTER_A_NAME);
        Map<Directions, String> possibleDirections = new HashMap<>();
        String townA = TOWN_A_NAME;
        String townB = TOWN_B_NAME;
        String townC = TOWN_C_NAME;
        String townD = TOWN_D_NAME;
        String townE = TOWN_E_NAME;
        possibleDirections.put(Directions.NORTH, townB);
        possibleDirections.put(Directions.SOUTH, townC);
        possibleDirections.put(Directions.EAST, townD);
        possibleDirections.put(Directions.WEST, townE);
        ConcurrentMap<String, List<Monster>> locationsMap = new ConcurrentHashMap<>();
        List<Monster> monstersInTownA = new ArrayList<>();
        monstersInTownA.add(monsterA);
        locationsMap.put(townA, monstersInTownA);
        locationsMap.put(townB, new ArrayList<>());
        locationsMap.put(townC, new ArrayList<>());
        locationsMap.put(townD, new ArrayList<>());
        locationsMap.put(townE, new ArrayList<>());
        MonsterLocations monsterLocations = new MonsterLocations(locationsMap);
        expect(movementStrategy.getDirection(possibleDirections)).andReturn(Directions.NORTH);
        control.replay();
        // WHEN
        underTest.moveMonster(monsterA, townA, possibleDirections, monsterLocations);
        // THEN
        control.verify();
        assertTrue(monsterLocations.getListOfMonsters(townA).isEmpty());
        assertEquals(1, monsterLocations.getListOfMonsters(townB).size());
        assertEquals(monsterA, monsterLocations.getListOfMonsters(townB).get(0));
    }
}