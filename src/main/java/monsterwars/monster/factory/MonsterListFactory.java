package monsterwars.monster.factory;

import monsterwars.monster.Monster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonsterListFactory {

    public List<Monster> createEmpty() {
        return Collections.synchronizedList(new ArrayList<>());
    }
}
