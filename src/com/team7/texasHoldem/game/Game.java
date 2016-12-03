package com.team7.texasHoldem.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Deck deck;

    private List<Player> players;

    private List<Card> tableCards;

    public void newGame(Deck deck, Player player1, Player... _players) {
        this.deck = deck;
        tableCards = new ArrayList<Card>();
        players = new ArrayList<Player>();
        //the game needs at least one player
        players.add(player1);
        players.addAll(Arrays.asList(_players));
    }

    public void removePlayer(Player player) {
        players.remove(player);
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
