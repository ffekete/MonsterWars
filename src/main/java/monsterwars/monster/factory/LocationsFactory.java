package monsterwars.monster.factory;


import monsterwars.monster.Monster;
import monsterwars.worldmap.data.Town;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocationsFactory {

    public ConcurrentMap<Town, List<Monster>> create() {
        return new ConcurrentHashMap<>();
    }
}
