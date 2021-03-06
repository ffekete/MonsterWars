package monsterwars.worldmap;

import monsterwars.worldmap.creator.TownMapCreator;
import monsterwars.worldmap.creator.WorldMapCreator;
import monsterwars.worldmap.data.Directions;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link WorldMapCreator}.
 */
public class WorldMapCreatorTest {

    private final IMocksControl control = EasyMock.createControl();

    private TownMapCreator townMapCreator;
    private WorldMapCreator underTest;

    @BeforeClass
    public void setUp() {
        townMapCreator = control.createMock(TownMapCreator.class);
        underTest = new WorldMapCreator(townMapCreator);
    }

    @Test
    public void testBuildShouldBuildWorldMap() {
        // GIVEN
        Map<String, Map<Directions, String>> emptyMap = new HashMap<>();
        Set<String> rawData = new TreeSet<>();
        expect(townMapCreator.createFrom(rawData)).andReturn(emptyMap);
        control.replay();
        // WHEN
        WorldMap result = underTest.create(rawData);
        // THEN
        control.verify();
        assertEquals(result.getMap(), emptyMap);
    }
}