package monsterwars.data;

import java.util.Map;

/**
 * Pojo containing direction from a given {@link Town} to different {@link Town} instances.
 */
public class Directions {

    private Town north;
    private Town south;
    private Town east;
    private Town west;

    public Town getNorth() {
        return north;
    }

    public Town getSouth() {
        return south;
    }

    public Town getEast() {
        return east;
    }

    public Town getWest() {
        return west;
    }

    public void setNorth(Town north) {
        this.north = north;
    }

    public void setSouth(Town south) {
        this.south = south;
    }

    public void setEast(Town east) {
        this.east = east;
    }

    public void setWest(Town west) {
        this.west = west;
    }
}
