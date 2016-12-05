package com.team7.texasHoldem.game;
import com.team7.texasHoldem.view.MainWindow;
import com.team7.texasHoldem.view.SystemLog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private ArrayList<Player> activePlayers;
    private ArrayList<Player> defeatedPlayers;
    public static Player player;
    public static Opponent opponent1;
    public static Opponent opponent2;
    public static Opponent opponent3;
    private static Hand currentHand;

    public Game() {
        activePlayers = new ArrayList<Player>();
        defeatedPlayers = new ArrayList<Player>();

        player = new Player();
        opponent1 = new Opponent();
        opponent2 = new Opponent();
        opponent3 = new Opponent();

        activePlayers.add(player);
        activePlayers.add(opponent1);
        activePlayers.add(opponent2);
        activePlayers.add(opponent3);

        for (Player activePlayer : activePlayers) {
            activePlayer.resetChips();
        }

        updateChips(player, opponent1, opponent2, opponent3, 0);
    }

    public void playGame() {
        while (activePlayers.size() > 1) {
            currentHand = new Hand(activePlayers);

            ArrayList<Player> handsDefeatedPlayers = currentHand.playHand();
            for (Player defeatedPlayer : handsDefeatedPlayers) {
                activePlayers.remove(defeatedPlayer);
                defeatedPlayers.add(defeatedPlayer);
            }
        }
    }

    public static void updateChips (Player player, Player opponent1, Player opponent2, Player opponent3, int pot) {
        String playerChips = Integer.toString(player.getChips());
        String opponent1Chips = Integer.toString(opponent1.getChips());
        String opponent2Chips = Integer.toString(opponent2.getChips());
        String opponent3Chips = Integer.toString(opponent3.getChips());
        String potChips = Integer.toString(pot);
        MainWindow.drawChips(playerChips, opponent1Chips, opponent2Chips, opponent3Chips, potChips);
    }

    public static void setCards () {
        MainWindow.setCards(player.getCards());
    }

}
