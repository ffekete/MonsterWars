package monsterwars.worldmap.data;

/**
 * Directions used for town directions.
 */
public enum Directions {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private final String name;

    Directions(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
