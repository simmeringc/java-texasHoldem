package com.team7.texasHoldem.game;

import java.util.List;

public class Ranker {
	
	public Card getHighCard(Card[] cards){
		//Card[] cards = player.getCards();
		Card max = cards[0];
		
		for(int i = 0; i < cards.length; i++){
			if(cards[i].getRankToInt() == max.getRankToInt()){
				if(cards[i].getSuitToInt() > max.getSuitToInt()){
					max = cards[i];
				}
			}
			else if (cards[i].getRankToInt() > max.getRankToInt()) {
		        max = cards[i];
		      }			
		}
		return max;

	}
	
	public Player getTopPlayer(List<Player> players){
		Player topPlayer = null;
		Card[] topCards = new Card[players.size()+1];
		for(int i = 0; i < players.size(); i++){
			topCards[i] = players.get(i).getHighCard();
		}
		topCards[players.size()+1] = getHighCard(Game.getTableCards());
		Card top = getHighCard(topCards);
		for(int i = 0; i < players.size(); i++){
			if (players.get(i).getHighCard() == top){
				topPlayer = players.get(i);
			}
		}
		return topPlayer;
	}
	
	
	public void setHighCards(List<Player> players){
		for(int i = 0; i < players.size(); i++){
			players.get(i).setHighCard(getHighCard(players.get(i).getCards()));
		}
	}

}
