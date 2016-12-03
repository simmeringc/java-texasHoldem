package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;
import com.team7.texasHoldem.view.SystemLog;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private static SystemLog systemLog;
    private static Deck deck;
    private static ArrayList<Card> cards;
    private static Random random;
    //spade, heart, diamond, club
    private static String[] CardSuitArray = {"\u2260","\u2265","\u2666","\u2663"};
    private static String[] CardRankArray = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public Deck(SystemLog systemLog) {
        this.systemLog = systemLog;
        this.random = new Random();
        shuffleDeck();
    }

    public static Deck getDeck(SystemLog systemLog) {
        if (deck == null) {
            deck = new Deck(systemLog);
        }
        return deck;
    }

    public static Card getCard() {
        if (cards.isEmpty()) {
            shuffleDeck();
            systemLog.newDeck();
        }
        return cards.remove(random.nextInt(cards.size()));
    }

    public static void shuffleDeck() {
        cards = new ArrayList<>();
        for (String suit : CardSuitArray) {
            for (String rank : CardRankArray) {
                cards.add(new Card(suit, rank));
            }
        }
    }
}

