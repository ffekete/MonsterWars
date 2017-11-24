package monsterwars.main;

import monsterwars.facade.GameInitializerFacade;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        GameInitializerFacade gameInitializerFacade = new GameInitializerFacade();
        gameInitializerFacade.init();
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Ms: " + time.until(endTime, ChronoUnit.MILLIS));
    }
}
