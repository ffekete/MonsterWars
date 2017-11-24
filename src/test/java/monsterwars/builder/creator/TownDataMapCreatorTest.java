package monsterwars.builder.creator;

import monsterwars.builder.factory.RawMapFactory;
import monsterwars.data.Directions;
import monsterwars.data.Town;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.testng.Assert.assertEquals;

public class TownDataMapCreatorTest {

    private TownDataMapCreator underTest = new TownDataMapCreator(new RawMapFactory());

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
        Map<Town, Map<Directions, Town>> map = underTest.create(rawMap);
        // THEN
        assertEquals(map.get(a).get(Directions.NORTH).getName(), "B");
        assertEquals(map.get(b).get(Directions.SOUTH).getName(), "A");
        assertEquals(map.get(c).get(Directions.NORTH).getName(), "A");
    }

}
