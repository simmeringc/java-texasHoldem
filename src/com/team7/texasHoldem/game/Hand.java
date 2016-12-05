package com.team7.texasHoldem.game;

import com.team7.texasHoldem.view.*;

import java.util.ArrayList;


/**
 * Created by Matt on 12/3/16.
 */
public class Hand {
    private int pot;
    private int currentBet;
    private ArrayList<Player> activePlayers = new ArrayList<Player>();
    private ArrayList<Player> foldedPlayers = new ArrayList<Player>();
    private ArrayList<Player> calledPlayers = new ArrayList<Player>();
    private ArrayList<Player> defeatedPlayers = new ArrayList<Player>();
    private Deck deck;
    private ArrayList<Card> tableCards = new ArrayList<Card>();
    private Ranker ranker;

    public Hand (ArrayList<Player> players) {
        pot = 0;
        currentBet = 0;

        activePlayers = players;

        deck = Deck.getDeck();
        deck.shuffleDeck();

        ranker = new Ranker();
    }

    public int getPot () {
        return pot;
    }

    public void dealPlayersCards() {
        for (Player player : activePlayers) {
            ArrayList<Card> playersCards = new ArrayList<Card>();
            playersCards.add(deck.getCard());
            playersCards.add(deck.getCard());
            player.setCards(playersCards);
            player.setHighCard();
        }
        Game.setCards();
    }

    public void dealFlop() {
        tableCards.add(deck.getCard());
        tableCards.add(deck.getCard());
        tableCards.add(deck.getCard());
    }

    public void dealTurn() {
        tableCards.add(deck.getCard());
    }

    public void dealRiver() {
        tableCards.add(deck.getCard());
    }

    public boolean doBetRound() {
        System.out.println("In doBetRound");

        currentBet = 0;
        while(activePlayers.size() > 0) {
            for (int i = 0; i < activePlayers.size(); i++) {
                System.out.println("In doBetRound loop");
                Player currentPlayer = activePlayers.get(i);
                int choice = currentPlayer.getChoice();
                System.out.println("Choice: " + Integer.toString(choice));
                if (choice == -1) { //fold
                    System.out.println("Folding");

                    activePlayers.remove(currentPlayer);
                    foldedPlayers.add(currentPlayer);
                } else if (choice == 1) { //call
                    System.out.println("Callling");

                    pot += currentBet;
                    currentPlayer.removeChips(currentBet);
                    activePlayers.remove(currentPlayer);
                    calledPlayers.add(currentPlayer);
                } else if (choice == 2) { //raise
                    System.out.println("Raising");

                    pot = pot + currentBet + 25;
                    currentPlayer.removeChips(currentBet + 25);
                    for (Player calledPlayer : calledPlayers) {
                        calledPlayers.remove(calledPlayer);
                        activePlayers.add(calledPlayer);
                    }
                    activePlayers.remove(currentPlayer);
                    calledPlayers.add(currentPlayer);
                }
            }
            Game.updateChips(Game.player, Game.opponent1, Game.opponent2, Game.opponent3, pot);
        }
        return calledPlayers.size() > 0;
    }

    public ArrayList<Player> playHand() {
        dealPlayersCards();
        doAnte();
        System.out.println("Doing preflop bet round");

        boolean continueToFlop = doBetRound();
        if (!continueToFlop) {
            return defeatedPlayers;
        }
        dealFlop();
        boolean continueToTurn = doBetRound();
        if (!continueToTurn) {
            return defeatedPlayers;
        }
        dealTurn();
        boolean continueToRiver = doBetRound();
        if (!continueToRiver) {
            return defeatedPlayers;
        }
        dealRiver();
        boolean playersFoldedToWinner = doBetRound();
        if (playersFoldedToWinner) {
            return defeatedPlayers;
        }
        Player winningPlayer = ranker.determineWinningPlayer(calledPlayers);

        for (Player calledPlayer : calledPlayers) {
            if (calledPlayer.getChips() == 0 && calledPlayer != winningPlayer) {
                defeatedPlayers.add(calledPlayer);
            }
        }
        for (Player foldedPlayer : foldedPlayers) {
            if (foldedPlayer.getChips() == 0) {
                defeatedPlayers.add(foldedPlayer);
            }
        }
        return defeatedPlayers;
    }

    public void doAnte() {
        for (Player activePlayer : activePlayers) {
            activePlayer.removeChips(25);
            pot = pot + 25;
        }
        Game.updateChips(Game.player, Game.opponent1, Game.opponent2, Game.opponent3, pot);
    }

    public void doRaise() {

    }
}
