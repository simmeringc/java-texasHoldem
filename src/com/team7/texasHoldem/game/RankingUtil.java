package com.team7.texasHoldem.game;

/**
 * Created by Matt on 12/2/16.
 */

import java.util.ArrayList;
import java.util.List;

public class RankingUtil {

    public static Card getHighCard(Player player, List<Card> tableCards) {
        List<Card> allCards = new ArrayList<Card>();
        allCards.addAll(tableCards);
        allCards.add(player.getCards()[0]);
        allCards.add(player.getCards()[1]);

        Card highCard = allCards.get(0);
        for (Card card : allCards) {
            if (card.getRankToInt() > highCard.getRankToInt()) {
                highCard = card;
            }
        }
        return highCard;
    }

    private void checkPlayersRanking() {
        for (Player player : players) {
            RankingUtil.checkRanking(player, tableCards);
        }
    }
}