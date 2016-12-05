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
    private ArrayList<Card> flopCards = new ArrayList<Card>();
    private Card turnCard;
    private Card riverCard;
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
        flopCards.add(deck.getCard());
        flopCards.add(deck.getCard());
        flopCards.add(deck.getCard());
        System.out.println("Drawing flop cards");
        MainWindow.drawFlopCardsCC(flopCards.get(0).getRank(), flopCards.get(0).getSuit(), flopCards.get(0).getColor(), flopCards.get(1).getRank(), flopCards.get(1).getSuit(), flopCards.get(1).getColor(), flopCards.get(2).getRank(), flopCards.get(2).getSuit(), flopCards.get(2).getColor());
    }

    public void dealTurn() {
        turnCard = (deck.getCard());
    }

    public void dealRiver() {
        riverCard = deck.getCard();
    }

    public boolean doBetRound() {
        System.out.println("In doBetRound");

        currentBet = 0;

        while(activePlayers.size() > 0) {
            for (int i = 0; i < activePlayers.size(); i++) {
                System.out.println("In doBetRound loop");
                Player currentPlayer = activePlayers.get(i);
                int choice = currentPlayer.getChoice();
                if (choice == -1) { //fold
                    System.out.println("Folding");
                    activePlayers.remove(currentPlayer);
                    foldedPlayers.add(currentPlayer);
                } else if (choice == 1) { //call
                    System.out.println("Calling");
                    pot += currentBet;
                    currentPlayer.removeChips(currentBet);
                    activePlayers.remove(currentPlayer);
                    calledPlayers.add(currentPlayer);
                } else if (choice == 2) { //raise
                    System.out.println("Raising");
                    pot = pot + currentBet + 25;
                    currentPlayer.removeChips(currentBet + 25);
                    for (int j = 0; j < calledPlayers.size(); j++) {
                        activePlayers.add(calledPlayers.get(j));
                        calledPlayers.remove(calledPlayers.get(j));
                    }
                    activePlayers.remove(currentPlayer);
                    calledPlayers.add(currentPlayer);
                }
            }
            Game.updateChips(Game.player, Game.opponent1, Game.opponent2, Game.opponent3, pot);
        }
        return calledPlayers.size() > 1;
    }

    public ArrayList<Player> playHand() {
        dealPlayersCards();
        doAnte();

        System.out.println("Doing preflop bet round");
        boolean continueToFlop = doBetRound();
        System.out.println("Back from preflop bet round");
        if (!continueToFlop) {
            System.out.println("Returning defeated players");
            return defeatedPlayers;
        }

        dealFlop();
        System.out.println("Doing postflop bet round");
        boolean continueToTurn = doBetRound();
        System.out.println("Back from postflop bet round");
        if (!continueToTurn) {
            calledPlayers.get(0).addChips(pot);
            System.out.println("Returning defeated players");
            return defeatedPlayers;
        }
        activePlayers = new ArrayList<Player>(calledPlayers);
        calledPlayers.clear();

        dealTurn();
        System.out.println("Doing post turn bet round");
        boolean continueToRiver = doBetRound();
        System.out.println("Back from post turn bet round");
        if (!continueToRiver) {
            calledPlayers.get(0).addChips(pot);
            System.out.println("Returning defeated players");
            return defeatedPlayers;
        }
        activePlayers = new ArrayList<Player>(calledPlayers);
        calledPlayers.clear();

        dealRiver();
        System.out.println("Doing post river bet round");
        boolean playersFoldedToWinner = doBetRound();
        System.out.println("Back from post river bet round");
        if (playersFoldedToWinner) {
            calledPlayers.get(0).addChips(pot);
            System.out.println("Returning defeated players");
            return defeatedPlayers;
        }

        Player winningPlayer = ranker.determineWinningPlayer(calledPlayers);
        winningPlayer.addChips(pot);
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
        System.out.println("Returning defeated players");

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
