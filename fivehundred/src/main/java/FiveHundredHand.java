package main.java;

import cardgame.Card;
import cardgame.Hand;

public class FiveHundredHand extends Hand {

	public Card tableCard;					//The card played during this round
	public String playerName;				//Name of the player
	
	/**
	 * Create a new Hand (player) for the FiveHundred Game. Player positions are assigned using the GUI
	 * @param name of the player
	 */
	public FiveHundredHand(String name){
		this.playerName = name;
	}
}
