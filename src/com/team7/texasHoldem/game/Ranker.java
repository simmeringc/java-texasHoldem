package com.team7.texasHoldem.game;

import java.util.ArrayList;

public class Ranker {
	
	public Card determineHighCard(ArrayList<Card> cards){
        Card highCard = null;
        int maxRank = 0;
        int maxSuit = 0;
		for(Card card : cards){
			if (card.getRankToInt() > maxRank) {
                highCard = card;
            } else if (card.getRankToInt() == maxRank) {
				if(card.getSuitToInt() > maxSuit){
					highCard = card;
				}
			}
		}
		return highCard;

	}
	
	public Player determineWinningPlayer(ArrayList<Player> callingPlayers){
		System.out.println("determineWinningPlayer callingPlayers length: " + callingPlayers.size());
		Player topPlayer = null;
		ArrayList<Card> topCards = new ArrayList<Card>();
		for(Player callingPlayer: callingPlayers){
			topCards.add(callingPlayer.getHighCard());
		}
		Card topCard = determineHighCard(topCards);
		for(Player callingPlayer : callingPlayers){
			if (determineHighCard(callingPlayer.getCards()) == topCard){
				topPlayer = callingPlayer;
                break;
			}
		}
		return topPlayer;
	}
}
