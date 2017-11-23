package monsterwars.main;

import monsterwars.facade.GameInitializerFacade;

public class Main {

    public static void main(String[] args) {
        GameInitializerFacade gameInitializerFacade = new GameInitializerFacade();
        gameInitializerFacade.init();
    }
}
