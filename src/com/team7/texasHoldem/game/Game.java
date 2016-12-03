package com.team7.texasHoldem.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Deck deck;

    private List<Player> players;

    private List<Card> tableCards;

    public Game(Player player1, Player player2, Player player3, Player player4) {
        this.deck = Deck.getDeck();
        tableCards = new ArrayList<Card>();
        players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
    }

    public Deck getDeck() {
        return deck;
    }

    public void deal() {
        for (Player player : players) {
            player.getCards()[0] = deck.getCard();
            player.getCards()[1] = deck.getCard();
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
