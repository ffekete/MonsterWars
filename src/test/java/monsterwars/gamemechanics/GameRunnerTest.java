package monsterwars.gamemechanics;

import monsterwars.gamemechanics.calculator.MonsterFightCalculator;
import monsterwars.gamemechanics.calculator.MovementCalculator;
import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.expect;

/**
 * Unit tests for {@link GameRunner}.
 */
public class GameRunnerTest {

    private final IMocksControl control = EasyMock.createControl();

    private MonsterFightCalculator monsterFightCalculator;
    private MovementCalculator movementCalculator;
    private GameRunner underTest;

    @BeforeClass
    public void setUp() {
        monsterFightCalculator = control.createMock(MonsterFightCalculator.class);
        movementCalculator = control.createMock(MovementCalculator.class);
        underTest = new GameRunner(monsterFightCalculator, movementCalculator);
    }

    @Test
    public void testRunWithShouldRunGameAndCallCalculators() {
        // GIVEN
        Town townA = new Town("A");
        Town townB = new Town("B");
        WorldMap worldMap = new WorldMap(new ConcurrentHashMap<>());
        Monster monsterA = new Monster("A");
        Monster monsterB = new Monster("B");
        Monster monsterC = new Monster("C");
        ConcurrentMap<Town, List<Monster>> monstersList = new ConcurrentHashMap<>();
        monstersList.put(townA, Collections.singletonList(monsterA));
        monstersList.put(townB, Arrays.asList(monsterB, monsterC));
        MonsterLocations monsterLocations = new MonsterLocations(monstersList);
        expect(monsterFightCalculator.calculate(townA, worldMap, monsterLocations)).andReturn(monstersList.get(townA));
        expect(monsterFightCalculator.calculate(townB, worldMap, monsterLocations)).andReturn(Collections.emptyList());
        movementCalculator.moveMonster(monsterA, townA, worldMap.getMap().get(townA), monsterLocations);
        EasyMock.expectLastCall();
        control.replay();
        // WHEN
        underTest.runWith(worldMap, monsterLocations);
        // THEN
        control.verify();
    }
}