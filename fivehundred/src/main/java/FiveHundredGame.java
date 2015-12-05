package main.java;

import java.util.ArrayList;

import cardgame.Deck;

public class FiveHundredGame {

	private Deck deck;
	private FiveHundredHand north, west, south, east;
	private FiveHundredHand kitty;
	
	/**
	 * Create a new Five Hundred Game. Player list is passed in from north, west, south, and east seats
	 * @param players List of FiveHundredHand players. Order passed in is north, west, south, east
	 */
	public FiveHundredGame (ArrayList<FiveHundredHand> players){
		north = players.get(0);
		west = players.get(1);
		south = players.get(2);
		east = players.get(3);
		
		deck = Deck.createStandardDeck();
	}
	
	/**
	 * Deals a new hand to each player. 
	 * TODO: make this tidier
	 */
	public void newHand(){
		
		//First pass
		for (int i=0;i<3;i++){
			north.addCard(deck.dealCard());		
		}
		for (int i=0;i<3;i++){
			west.addCard(deck.dealCard());		
		}
		for (int i=0;i<3;i++){
			south.addCard(deck.dealCard());		
		}
		for (int i=0;i<3;i++){
			east.addCard(deck.dealCard());		
		}
		kitty.addCard(deck.dealCard());
		
		//Second pass
		for (int i=0;i<4;i++){
			north.addCard(deck.dealCard());		
		}
		for (int i=0;i<4;i++){
			west.addCard(deck.dealCard());		
		}
		for (int i=0;i<4;i++){
			south.addCard(deck.dealCard());		
		}
		for (int i=0;i<4;i++){
			east.addCard(deck.dealCard());		
		}
		kitty.addCard(deck.dealCard());
		
		//Third pass
		for (int i=0;i<3;i++){
			north.addCard(deck.dealCard());		
		}
		for (int i=0;i<3;i++){
			west.addCard(deck.dealCard());		
		}
		for (int i=0;i<3;i++){
			south.addCard(deck.dealCard());		
		}
		for (int i=0;i<3;i++){
			east.addCard(deck.dealCard());		
		}
		kitty.addCard(deck.dealCard());

	}
	
	public void endRound(){
		
	}

}
