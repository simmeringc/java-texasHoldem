package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;

public class Card {

    private CardSuitEnum suit;
    private CardRankEnum rank;

    public Card(CardSuitEnum suit, CardRankEnum rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public CardSuitEnum getSuit() {
        return suit;
    }

    public CardRankEnum getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Suit: " + suit.toString() + ", Rank :" + rank.toString();
    }

    public Integer getRankToInt() {
        return rank.ordinal();
    }
}
