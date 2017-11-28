package monsterwars.worldmap.creator;

import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.factory.TownDirectionsMapFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Unit tests for {@link TownMapCreator}.
 */
public class TownMapCreatorTest {

    private TownMapCreator underTest;

    @BeforeClass
    public void setUp() {
        underTest = new TownMapCreator(new RawMapFactory(), new TownDirectionsMapFactory());
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
        //WHEN
        Map<String, Map<Directions, String>> map = underTest.createFrom(rawData);
        // THEN
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
        //WHEN
        Map<String, Map<Directions, String>> map = underTest.createFrom(rawMap);
        // THEN
        assertEquals(map.get(townA).get(Directions.NORTH), townB);
        assertNull(map.get(townB).get(Directions.SOUTH));
        assertNull(map.get(townC).get(Directions.NORTH));
    }

}
