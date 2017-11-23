package monsterwars.data;

/**
 * Pojo representing a single town.
 */
public class Town implements Comparable<Town>{

    private final String name;

    public Town(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Town o) {
        return name.compareTo(o.getName());
    }
}
