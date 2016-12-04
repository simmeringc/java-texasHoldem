package com.team7.texasHoldem.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Deck deck;
    private Ranker ranker;
    private List<Player> players;

    private List<Card> tableCards;

    public Game(Player player1, Player player2, Player player3, Player player4) {
        this.deck = new Deck();
        tableCards = new ArrayList<Card>();
        players = new ArrayList<Player>();
        ranker = new Ranker();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
    }

    public List<Player> getPlayers(){
    	return players;
    }
    
    public Deck getDeck() {
        return deck;
    }
    
    public Player getWinner(){
    	return ranker.getTopPlayer(getPlayers());
    }
    
    public void deal() {
        for (Player player : players) {
            player.getCards()[0] = deck.pop();
            player.getCards()[1] = deck.pop();
        }
        ranker.setHighCards(getPlayers());
    }

    public void callFlop() {
        deck.pop(); //Burn Card
        tableCards.add(deck.pop());
        tableCards.add(deck.pop());
        tableCards.add(deck.pop());
    }

    public void betTurn() {
        deck.pop();
        tableCards.add(deck.pop());
    }

    public void betRiver() {
        deck.pop();
        tableCards.add(deck.pop());
    }

}
