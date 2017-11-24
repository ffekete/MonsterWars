package monsterwars.worldmap.data;

/**
 * Pojo representing a single town.
 */
public final class Town implements Comparable<Town> {

    private final String name;

    public Town(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(final Town o) {
        return name.compareTo(o.getName());
    }
}
