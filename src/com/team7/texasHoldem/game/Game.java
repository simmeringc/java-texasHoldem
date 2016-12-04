package com.team7.texasHoldem.game;
import com.team7.texasHoldem.view.SystemLog;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private SystemLog systemLog;
    private Deck deck;
    private List<Player> players;
    private List<Player> activePlayers;
    private List<Card> tableCards;
    private int pot = 1000;

    public Game(SystemLog systemLog, Player player1, Player player2, Player player3, Player player4) {
        this.systemLog = systemLog;
        this.deck = new Deck(systemLog);
        tableCards = new ArrayList<Card>();
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
    }

    public String resetPot() {
        pot = 0;
        return "0";
    }

    public void increasePot(int i) {
        pot = pot + i;
    }

    public String getPot() {
        String potToString = Integer.toString(pot);
        return potToString;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    public void removeActivePlayer(Player player) {
        activePlayers.remove(player);
    }

    public void deal() {
        for (Player player : players) {
            player.getCards()[0] = deck.getCard();
            player.getCards()[1] = deck.getCard();
            activePlayers.add(player);
        }
    }

    public void callFlop() {
        deck.getCard(); //Burn Card
        tableCards.add(deck.getCard());
        tableCards.add(deck.getCard());
        tableCards.add(deck.getCard());
    }

    public void betTurn() {
        deck.getCard();
        tableCards.add(deck.getCard());
    }

    public void betRiver() {
        deck.getCard();
        tableCards.add(deck.getCard());
    }

//    public List<Player> getWinner() {
//      RankingUtil.
//    }
}
