package com.team7.texasHoldem.game;

public class GameRunner {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player1 = new Player();
        Game game = new Game();
        game.newGame(deck, player1);
        game.deal();
        player1.printCards();
    }
}
