package com.team7.texasHoldem.game;

public class CardRanker {
	
	public Card getHighCard(Card[]cards){
		Card max = cards[0];
		
		for(int i = 0; i < cards.length; i++){
		    if (cards[i].getRankToInt() > max.getRankToInt()) {
		        max = cards[i];
		      }			
		}
		return max;

	}
}
