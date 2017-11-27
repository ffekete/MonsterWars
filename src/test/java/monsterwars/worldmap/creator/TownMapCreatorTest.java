package monsterwars.worldmap.creator;

import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;
import monsterwars.worldmap.factory.TownDirectionsMapFactory;
import monsterwars.worldmap.factory.TownFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TownMapCreatorTest {

    private final IMocksControl control = EasyMock.createControl();

    private TownMapCreator underTest;
    private TownFactory townFactory;

    @BeforeClass
    public void setUp() {
        townFactory = control.createMock(TownFactory.class);
        underTest = new TownMapCreator(new RawMapFactory(), new TownDirectionsMapFactory(), townFactory);
    }

    @BeforeMethod
    public void beforeMethod() {
        control.reset();
    }

    @Test
    public void fillShouldFillWorldMapWhenInputIsProper() {
        // GIVEN
        Set<String> rawData = new TreeSet<>();
        rawData.add("A north=B south=C");
        rawData.add("C north=A");
        rawData.add("B south=A");
        Town townA = new Town("A");
        Town townB = new Town("B");
        Town townC = new Town("C");
        expect(townFactory.create("A")).andReturn(townA);
        expect(townFactory.create("B")).andReturn(townB);
        expect(townFactory.create("C")).andReturn(townC);
        control.replay();
        //WHEN
        ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map = underTest.createFrom(rawData);
        // THEN
        control.verify();
        assertEquals(map.get(townA).get(Directions.NORTH), townB);
        assertEquals(map.get(townB).get(Directions.SOUTH), townA);
        assertEquals(map.get(townC).get(Directions.NORTH), townA);
    }

    @Test
    public void fillShouldFillWorldMapWhenInputLinksAreNotSymmetric() {
        // GIVEN
        Set<String> rawMap = new TreeSet<>();
        rawMap.add("A north=B south=C");
        rawMap.add("C");
        rawMap.add("B");
        Town townA = new Town("A");
        Town townB = new Town("B");
        Town townC = new Town("C");
        expect(townFactory.create("A")).andReturn(townA);
        expect(townFactory.create("B")).andReturn(townB);
        expect(townFactory.create("C")).andReturn(townC);
        control.replay();
        //WHEN
        ConcurrentMap<Town, ConcurrentMap<Directions, Town>> map = underTest.createFrom(rawMap);
        // THEN
        control.verify();
        assertEquals(map.get(townA).get(Directions.NORTH), townB);
        assertNull(map.get(townB).get(Directions.SOUTH));
        assertNull(map.get(townC).get(Directions.NORTH));
    }

}
