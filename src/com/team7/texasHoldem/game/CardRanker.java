package com.team7.texasHoldem.game;

public class CardRanker {
	
	public /*Card*/ void getPlayerHighCard(Card[]playerCards){
		for(int i = 0; i < playerCards.length; i++){
			System.out.println(playerCards[i].getRank());
		}
		
	}

}
