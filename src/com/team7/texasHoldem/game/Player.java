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

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeChips(int amountToRemove) {
        chips = chips - amountToRemove;
    }

    public void addChips(int amountToAdd) { chips = chips + amountToAdd;};

    public void resetChips() {
        chips = 1000;
    }

    public int getChips() {
        return chips;
    }

    public int getChoice() {
        while (choice == -2) {
            continue;
        }
        int choiceToReturn = this.choice;
        this.choice = -2;
        System.out.println("Returning choice:" + Integer.toString(this.choice));

        return choiceToReturn;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
