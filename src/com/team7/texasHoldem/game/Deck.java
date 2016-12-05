package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;
import com.team7.texasHoldem.view.SystemLog;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private static Deck deck;
    private static ArrayList<Card> cards = new ArrayList<Card>();
    private static Random random;
    private static String[] CardSuitArray = {"\u2660","\u2665","\u2666","\u2663"}; //spade, heart, diamond, club
    private static String[] CardRankArray = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

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
        cards.clear();
        for (String suit : CardSuitArray) {
            for (String rank : CardRankArray) {
                cards.add(new Card(suit, rank));
            }
        }
    }
}

