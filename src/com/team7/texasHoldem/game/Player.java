package com.team7.texasHoldem.game;

import java.util.ArrayList;

public class Player {

    protected ArrayList<Card> cards = new ArrayList<Card>();
    protected Card highCard;
    protected int chips;
    private Ranker ranker = new Ranker();
    private int choice = -2;

    public void setHighCard() {
        this.highCard = ranker.determineHighCard(cards);
    }

    public Card getHighCard () {
        return this.highCard;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeChips(int amountToRemove) {
        chips = chips - amountToRemove;
    }

    public void addChips(int amountToAdd) {
        chips = chips + amountToAdd;
        Game.updateChips(Game.player, Game.opponent1, Game.opponent2, Game.opponent3, 0);
    }

    public void resetChips() {
        chips = 1000;
    }

    public int getChips() {
        return chips;
    }

    public int getChoice() {
        System.out.println("In player getChoice");
        while (this.choice == -2) {
            System.out.println("loop this.choice: " + Integer.toString(this.choice));
            continue;
        }
        int choiceToReturn = this.choice;
        System.out.println("choiceToReturn: " + Integer.toString(choiceToReturn));
        this.choice = -2;
        System.out.println("Returning choice:" + Integer.toString(choiceToReturn));

        return choiceToReturn;
    }

    public void setChoice(int choice) {
        this.choice = choice;
        System.out.println("this.choice: " + Integer.toString(this.choice));
    }
}
