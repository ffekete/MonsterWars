package monsterwars.monster;

import com.google.inject.AbstractModule;
import monsterwars.monster.strategy.MonsterPlacementStrategy;
import monsterwars.monster.strategy.RandomMonsterPlacementStrategy;

public class MonsterModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MonsterPlacementStrategy.class).to(RandomMonsterPlacementStrategy.class);
    }
}
