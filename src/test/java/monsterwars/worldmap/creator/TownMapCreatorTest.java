package monsterwars.worldmap.creator;

import monsterwars.worldmap.factory.RawMapFactory;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TownMapCreatorTest {

    private TownMapCreator underTest = new TownMapCreator(new RawMapFactory());

    @Test
    public void fillShouldFillWorldMapWhenInputIsProper() {
        // GIVEN
        Set<String> rawMap = new TreeSet<>();
        rawMap.add("A north=B south=C");
        rawMap.add("C north=A");
        rawMap.add("B south=A");
        Town a = new Town("A");
        Town b = new Town("B");
        Town c = new Town("C");
        //WHEN
        Map<Town, Map<Directions, Town>> map = underTest.createFrom(rawMap);
        // THEN
        assertEquals(map.get(a).get(Directions.NORTH).getName(), "B");
        assertEquals(map.get(b).get(Directions.SOUTH).getName(), "A");
        assertEquals(map.get(c).get(Directions.NORTH).getName(), "A");
    }

    @Test
    public void fillShouldFillWorldMapWhenInputLinksAreNotSymmetric() {
        // GIVEN
        Set<String> rawMap = new TreeSet<>();
        rawMap.add("A north=B south=C");
        rawMap.add("C");
        rawMap.add("B");
        Town a = new Town("A");
        Town b = new Town("B");
        Town c = new Town("C");
        //WHEN
        Map<Town, Map<Directions, Town>> map = underTest.createFrom(rawMap);
        // THEN
        assertEquals(map.get(a).get(Directions.NORTH).getName(), "B");
        assertNull(map.get(b).get(Directions.SOUTH));
        assertNull(map.get(c).get(Directions.NORTH));
    }

}
