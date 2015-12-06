package main.java;

import java.util.ArrayList;

import cardgame.Card;
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
		
		
		createDeck();
		
		// Create Kitty here. May change later on
		kitty = new FiveHundredHand("kitty");
	}
	
	private void createDeck(){
		
		deck = new Deck();
		
		deck.addCard(new Card(Card.joker_suit, (short)0));
		
		deck.addCard(new Card(Card.spades_suit, (short)4));
		deck.addCard(new Card(Card.clubs_suit, (short)4));
		
		for (short suit=0; suit<=3; suit++) {
			for (short rank=5; rank<=12; rank++){
				deck.addCard(new Card(suit,rank));
			}
		} // End of for loop
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
		System.out.println("kitty");
		System.out.print(kitty.toString());

		System.out.println("north");
		System.out.print(north.toString());
		
		System.out.println("west");
		System.out.print(west.toString());
		
		System.out.println("south");
		System.out.print(south.toString());
		
		System.out.println("east");
		System.out.print(east.toString());
	}
	
	public void endRound(){
		
	}

}
