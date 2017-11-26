package monsterwars.gamemechanics.calculator;

import monsterwars.monster.Monster;
import monsterwars.monster.MonsterContainer;
import monsterwars.monster.factory.MonsterListFactory;
import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.data.Town;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MonsterFightCalculatorTest {

    private static final String MONSTER_1_NAME = "1";
    private static final String MONSTER_2_NAME = "2";
    private static final String MONSTER_3_NAME = "3";

    private final IMocksControl control = EasyMock.createControl();
    private final MonsterContainer monsterContainer = new MonsterContainer(new MonsterListFactory());

    private MonsterListFactory monsterListFactory;
    private MonsterFightCalculator underTest;

    @BeforeClass
    public void setUp() {
        monsterListFactory = control.createMock(MonsterListFactory.class);

        underTest = new MonsterFightCalculator(monsterListFactory, monsterContainer);
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
        List<Monster> listOfMonsters = Arrays.asList(monster1, monster2);
        expect(monsterListFactory.createEmpty()).andReturn(emptyList);
        monsterContainer.addMonster(monster1);
        monsterContainer.addMonster(monster2);
        monsterContainer.addMonster(monster3);
        control.replay();
        // WHEN
        List<Monster> result = underTest.calculate(listOfMonsters, new Town("a"), new WorldMap(new HashMap<>()), new HashMap<>());
        // THEN
        control.verify();
        assertTrue(emptyList.equals(result));
        assertEquals(monsterContainer.getNumberOfMonsters(), Long.valueOf(1L));
    }

    @Test(dataProvider = "provideMonster")
    public void testCalculateShouldReturnWithTheInputListWhenLessThanTwoMonstersAreInTheList(Monster monster) {
        // GIVEN
        List<Monster> listOfMonsters = Collections.singletonList(monster);
        // WHEN
        List<Monster> result = underTest.calculate(listOfMonsters, new Town("a"), new WorldMap(new HashMap<>()), new HashMap<>());
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