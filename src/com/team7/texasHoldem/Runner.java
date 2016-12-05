package com.team7.texasHoldem;

import com.team7.texasHoldem.game.*;
import com.team7.texasHoldem.view.*;

/**
 * Created by Matt on 12/3/16.
 */
public class Runner {
    SystemLog systemLog = new SystemLog();
    public static Game game;

    public static void main(String[] args) {
        MainWindow.initialize();
        initializeGame();
    }

    public static void initializeGame () {
        game = new Game();
        game.playGame();
    }
}
