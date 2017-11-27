package monsterwars.worldmap;

import monsterwars.worldmap.creator.TownMapCreator;
import monsterwars.worldmap.data.Directions;
import monsterwars.worldmap.data.Town;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link WorldMapBuilder}.
 */
public class WorldMapBuilderTest {

    private final IMocksControl control = EasyMock.createControl();

    private TownMapCreator townMapCreator;
    private WorldMapBuilder underTest;

    @BeforeClass
    public void setUp() {
        townMapCreator = control.createMock(TownMapCreator.class);
        underTest = new WorldMapBuilder(townMapCreator);
    }

    @Test
    public void testBuildShouldBuildWorldMap() {
        // GIVEN
        ConcurrentMap<Town, ConcurrentMap<Directions, Town>> emptyMap = new ConcurrentHashMap<>();
        Set<String> rawData = new TreeSet<>();
        expect(townMapCreator.createFrom(rawData)).andReturn(emptyMap);
        control.replay();
        // WHEN
        WorldMap result = underTest.build(rawData);
        // THEN
        control.verify();
        assertEquals(result.getMap(), emptyMap);
    }
}