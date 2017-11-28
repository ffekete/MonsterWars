package monsterwars.worldmap.inverter;

import monsterwars.worldmap.data.Directions;

import java.util.HashMap;
import java.util.Map;

/**
 * For inverting {@link Directions}.
 */
public final class DirectionsInverter {

    private final Map<Directions, Directions> invertDirections = new HashMap<>();

    public DirectionsInverter() {
        invertDirections.put(Directions.NORTH, Directions.SOUTH);
        invertDirections.put(Directions.SOUTH, Directions.NORTH);
        invertDirections.put(Directions.EAST, Directions.WEST);
        invertDirections.put(Directions.WEST, Directions.EAST);
    }

    /**
     * Inverts a direction.
     *
     * @param directions to invert.
     * @return inverse direction.
     */
    public Directions get(Directions directions) {
        return invertDirections.get(directions);
    }
}
