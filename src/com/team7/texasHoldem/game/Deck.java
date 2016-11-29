/**
 * Created by Conner on 11/28/16.
 */

package com.team7.texasHoldem.game;
import com.team7.texasHoldem.enums.CardRankEnum;
import com.team7.texasHoldem.enums.CardSuitEnum;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
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

    public void printDeck() {
        int i = 0;
        for (Card card : cards) {
            i++;
            System.out.println(i);
            System.out.println(card.toString());
        }
    }
}

