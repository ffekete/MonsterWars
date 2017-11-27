package monsterwars.worldmap.facade;

import monsterwars.worldmap.WorldMap;
import monsterwars.worldmap.WorldMapBuilder;
import monsterwars.worldmap.reader.WorldMapFileReader;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import static org.easymock.EasyMock.expect;
import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link WorldMapInitializerFacade}.
 */
public class WorldMapInitializerFacadeTest {

    private final IMocksControl control = EasyMock.createControl();

    private WorldMapBuilder worldMapBuilder;
    private WorldMapFileReader worldMapFileReader;
    private WorldMapInitializerFacade underTest;

    @BeforeClass
    public void setUp() {
        worldMapBuilder = control.createMock(WorldMapBuilder.class);
        worldMapFileReader = control.createMock(WorldMapFileReader.class);
        underTest = new WorldMapInitializerFacade(worldMapBuilder, worldMapFileReader);
    }

    @BeforeMethod
    public void beforeMethod() {
        control.reset();
    }

    @Test
    public void testInitShouldReadFileAndBuildWorldWhenInputIsSupplied() throws IOException {
        // GIVEN
        Set<String> rawData = new TreeSet<>();
        WorldMap worldMap = new WorldMap(new ConcurrentHashMap<>());
        expect(worldMapFileReader.read("map.txt")).andReturn(rawData);
        expect(worldMapBuilder.build(rawData)).andReturn(worldMap);
        control.replay();
        // WHEN
        WorldMap result = underTest.init();
        // THEN
        control.verify();
        assertEquals(result, worldMap);
    }

    @Test(expectedExceptions = IOException.class)
    public void testInitShouldThrowExceptionWhenFileReaderThrowsException() throws IOException {
        // GIVEN
        expect(worldMapFileReader.read("map.txt")).andThrow(new IOException());
        control.replay();
        // WHEN
        underTest.init();
        // THEN
        control.verify();
    }

}