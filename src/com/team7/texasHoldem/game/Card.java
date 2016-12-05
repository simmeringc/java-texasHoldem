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

    public int getRankToInt(){
    	if(rank == "J") return 11;
    	else if(rank == "Q") return 12;
    	else if(rank == "K") return 13;
    	else if(rank == "A") return 14;
    	else return Integer.parseInt(rank);
    }

    public int getSuitToInt(){
    	if(suit == "\u2260") return 4;
    	else if(suit == "\u2265") return 3;
    	else if(suit == "\u2666") return 2;
    	else if(suit == "\u2663") return 1;
    	else return 0;
    	
    }
    //spade, heart, diamond, club
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
        return "Suit: " + suit + ", Rank: " + rank;
    }
}
