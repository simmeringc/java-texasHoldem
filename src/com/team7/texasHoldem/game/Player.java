package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.HandRankEnum;
import java.util.List;


public class Player {

    private Card[] cards = new Card[2];
    private Card highCard = null;
    private int chips = 0;
    private int playerNumber;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Card getHighCard() {
        return highCard;
    }

    public void setHighCard(Card highCard) {
        this.highCard = highCard;
    }

    public Card[] getCards() {
        return cards;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
    public void removeChips(int i) {
        chips = chips-i;
    }
    public String resetChips() {
        chips = 1000;
        return "1000";
    }
    public String getChips() {
        String chipsToString = Integer.toString(chips);
        return chipsToString;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}
