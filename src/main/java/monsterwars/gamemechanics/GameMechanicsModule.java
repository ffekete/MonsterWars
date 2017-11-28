package monsterwars.gamemechanics;

import com.google.inject.AbstractModule;
import monsterwars.gamemechanics.strategy.MovementStrategy;
import monsterwars.gamemechanics.strategy.RandomMovementStrategy;

public class GameMechanicsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MovementStrategy.class).to(RandomMovementStrategy.class);
    }
}
