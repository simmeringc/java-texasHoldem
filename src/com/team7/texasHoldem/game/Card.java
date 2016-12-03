package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;
import java.awt.*;

public class Card {

    private String suit;
    private String rank;
    private java.awt.Color color;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        if (suit == "\u2665") {
            this.color = Color.RED;
        }
        if (suit == "\u2666") {
            this.color = Color.RED;
        }
        if (suit == "\u2660") {
            this.color = Color.BLACK;
        }
        if (suit == "\u2663") {
            this.color = Color.BLACK;
        }
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Suit: " + suit + ", Rank :" + rank;
    }

//    public Integer getRankToInt() {
//        return rank.ordinal();
//    }
}
