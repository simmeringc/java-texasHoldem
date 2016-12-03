package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;
import com.team7.texasHoldem.view.SystemLog;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private SystemLog systemLog;
    private ArrayList<Card> cards;
    private Random random;
    //spade, heart, diamond, club
    String[] CardSuitArray = {"\u2660","\u2665","\u2666","\u2663"};
    String[] CardRankArray = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public Deck(SystemLog systemLog) {
        this.systemLog = systemLog;
        this.random = new Random();
        createDeck();
    }

    private void createDeck() {
        cards = new ArrayList<>();
        for (String suit : CardSuitArray) {
            for (String rank : CardRankArray) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public Card pop() {
        if (cards.isEmpty()) {
            createDeck();
            systemLog.newDeck();
        }
        return cards.remove(random.nextInt(cards.size()));
        }
    }

