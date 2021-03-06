package com.team7.texasHoldem.game;
import com.team7.texasHoldem.view.SystemLog;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private SystemLog systemLog;
    private Deck deck;
    private Ranker ranker;

    private List<Player> players;
    private List<Player> activePlayers;
    private static Card[] tableCards = new Card[3];
    private int pot = 1000;
    private int round = 0;

    public Game(SystemLog systemLog, Player player1, Player player2, Player player3, Player player4) {
        this.systemLog = systemLog;
        this.deck = new Deck(systemLog);
        players = new ArrayList<Player>();
        ranker = new Ranker();
        activePlayers = new ArrayList<Player>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public static Card[] getTableCards(){
    	return tableCards;
    }
    
    public Player getWinner(){
        return ranker.getTopPlayer(getPlayers());
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

    public void setRound(int i) {
        round = i;
    }

    public int getRound() {
        return round;
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
            if (!activePlayers.contains(player)) {
                activePlayers.add(player);
            }
        }
        ranker.setHighCards(getPlayers());
    }

    public void callFlop() {
        deck.getCard(); //Burn Card
        tableCards[0] = deck.getCard();

    }

    public void betTurn() {
        deck.getCard();
        tableCards[1] = deck.getCard();

    }

    public void betRiver() {
        deck.getCard();
        tableCards[2] = deck.getCard();

    }

}