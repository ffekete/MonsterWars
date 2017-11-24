package monsterwars.data;

public enum Directions {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private String name;

    Directions(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
