package monsterwars.monster.factory;


import monsterwars.monster.Monster;
import monsterwars.worldmap.data.Town;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationsFactory {

    public Map<Town, List<Monster>> create() {
        return new HashMap<>();
    }
}
