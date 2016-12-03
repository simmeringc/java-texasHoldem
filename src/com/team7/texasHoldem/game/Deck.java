package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private static Deck deck;
    private static ArrayList<Card> cards;
    private static Random random;

    private Deck() {
        random = new Random();
        shuffleDeck();
    }

    public static Deck getDeck() {
        if (deck == null) {
            deck = new Deck();
        }
        return deck;
    }

    public static Card getCard() {
        return cards.remove(random.nextInt(cards.size()));
    }

    public static void shuffleDeck() {
        cards = new ArrayList<>();
        for (CardSuitEnum suit : CardSuitEnum.values()) {
            for (CardRankEnum rank : CardRankEnum.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }
}

