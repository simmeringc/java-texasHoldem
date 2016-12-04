package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.HandRankEnum;
import java.util.List;


public class Player {

    private Card[] cards = new Card[2];
    private Card highCard = null;

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

    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}
