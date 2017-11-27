package monsterwars.gamemechanics.calculator;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterLocations;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MonsterFightCalculatorTest {

    private static final String MONSTER_1_NAME = "1";
    private static final String MONSTER_2_NAME = "2";
    private static final String MONSTER_3_NAME = "3";

    private final IMocksControl control = EasyMock.createControl();

    private MonsterListFactory monsterListFactory;
    private MonsterFightCalculator underTest;

    @BeforeClass
    public void setUp() {
        monsterListFactory = control.createMock(MonsterListFactory.class);

        underTest = new MonsterFightCalculator(monsterListFactory);
    }

    @BeforeMethod
    public void beforeMethod() {
        control.reset();
    }

    @Test
    public void testCalculateShouldReturnWithEmptyListIfMoreMonstersAreInTheList() {
        // GIVEN
        List<Monster> emptyList = Collections.emptyList();
        Monster monster1 = new Monster(MONSTER_1_NAME);
        Monster monster2 = new Monster(MONSTER_2_NAME);
        Monster monster3 = new Monster(MONSTER_3_NAME);
        expect(monsterListFactory.createEmpty()).andReturn(emptyList);
        control.replay();
        // WHEN
        List<Monster> result = underTest.calculate(new Town("a"), new WorldMap(new ConcurrentHashMap<>()), new MonsterLocations(new ConcurrentHashMap<>()));
        // THEN
        control.verify();
        assertTrue(emptyList.equals(result));
    }

    @Test(dataProvider = "provideMonster")
    public void testCalculateShouldReturnWithTheInputListWhenLessThanTwoMonstersAreInTheList(Monster monster) {
        // GIVEN
        List<Monster> listOfMonsters = Collections.singletonList(monster);
        // WHEN
        List<Monster> result = underTest.calculate(new Town("a"), new WorldMap(new ConcurrentHashMap<>()), new MonsterLocations(new ConcurrentHashMap<>()));
        // THEN
        assertTrue(result.equals(listOfMonsters));
    }

    @DataProvider
    public Object[][] provideMonster() {
        return new Object[][]{
                {new Monster(MONSTER_1_NAME)},
                {null}
        };
    }
}