package monsterwars.worldmap.creator;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.factory.TownDirectionsMapFactory;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TownMapCreatorTest {

    private final IMocksControl control = EasyMock.createControl();

    private TownMapCreator underTest;

    @BeforeClass
    public void setUp() {
        underTest = new TownMapCreator(new RawMapFactory(), new TownDirectionsMapFactory());
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
        String townA = "A";
        String townB = "B";
        String townC = "C";
        control.replay();
        //WHEN
        ConcurrentMap<String, ConcurrentMap<Directions, String>> map = underTest.createFrom(rawData);
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
        String townA = "A";
        String townB = "B";
        String townC = "C";
        control.replay();
        //WHEN
        ConcurrentMap<String, ConcurrentMap<Directions, String>> map = underTest.createFrom(rawMap);
        // THEN
        control.verify();
        assertEquals(map.get(townA).get(Directions.NORTH), townB);
        assertNull(map.get(townB).get(Directions.SOUTH));
        assertNull(map.get(townC).get(Directions.NORTH));
    }

}
