package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;
    private Random random;

    public Deck() {
        this.random = new Random();
        createDeck();
    }

    private void createDeck() {
        cards = new ArrayList<>();
        for (CardSuitEnum suit : CardSuitEnum.values()) {
            for (CardRankEnum rank : CardRankEnum.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public Card pop() {
        return cards.remove(random.nextInt(cards.size()));
    }

    public void printDeck() {
        int i = 0;
        for (Card card : cards) {
            i++;
            System.out.println(i);
            System.out.println(card.toString());
        }
    }
}

