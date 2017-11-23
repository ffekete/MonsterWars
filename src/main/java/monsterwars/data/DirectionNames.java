package monsterwars.data;

public enum DirectionNames {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private String name;

    DirectionNames(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
