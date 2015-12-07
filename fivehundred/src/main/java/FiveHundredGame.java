package main.java;

import java.util.ArrayList;
import java.util.LinkedList;

import cardgame.Card;
import cardgame.Deck;

public class FiveHundredGame {

	private Deck deck;
	private FiveHundredHand north, west, south, east;
	private ArrayList<FiveHundredHand> players;
	private int currentTurn = 0;
	private FiveHundredHand kitty;
	private ArrayList<String> bidding;
	
	private ArrayList<Card> tableCards;
	
	private String bids[] = {"6s", "6c", "6d","6h","6NT","7s","7c","7d","7h","7NT","CLOSED","8s","8c","8d","8h","8NT","OPEN",
			"9s","9c","9d","9h","9NT","10s","10c","10d","10h","10NT"};
	
	
	
	/**
	 * Create a new Five Hundred Game. Player list is passed in from north, west, south, and east seats
	 * @param players List of FiveHundredHand players. Order passed in is north, west, south, east
	 */
	public FiveHundredGame (ArrayList<FiveHundredHand> players){
		this.players = players;
		north = players.get(0);
		west = players.get(1);
		south = players.get(2);
		east = players.get(3);
		
		
		createDeck();
		
		// Create Kitty here. May change later on
		kitty = new FiveHundredHand("kitty");
		tableCards = new ArrayList<Card>();
	}
	
	/**
	 * Creates a 500 deck. 5 to ace for black suits, 4 to ace for red suits, plus one joker
	 */
	private void createDeck(){
		
		System.out.println("Dealing new deck");
		
		deck = new Deck();
		
		deck.addCard(new Card(Card.joker_suit, (short)0));
		
		deck.addCard(new Card(Card.diamonds_suit, (short)3));
		deck.addCard(new Card(Card.hearts_suit, (short)3));
		
		for (short suit=0; suit<=3; suit++) {
			for (short rank=4; rank<=12; rank++){
				deck.addCard(new Card(suit,rank));
			}
			deck.addCard(new Card(suit, (short)0)); //Add the aces
		} // End of for loop
		
		System.out.println(deck.getTotalCards()+" cards dealt");
		
		deck.shuffleStandard();
	}
	
	/**
	 * Deals a new hand to each player. 
	 * 3 to each player, 1 to kitty, then 4 to each player, 1 to kitty, then 3 to each player, 1 to kitty
	 * TODO: make this tidier
	 */
	public void newHand(){
		
		if(deck.getTotalCards() < 43){
			System.out.println("Not enough cards. Required 43 cards, found " + deck.getTotalCards());
			return;
		}
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
		
		//REMOVE
		south.select(2);
	}
	
	public void run(){
		//Current player
		FiveHundredHand currentPlayer = players.get(currentTurn);
		//TODO tableCards.add(currentPlayer.getSelected())
	}
	
	public void endRound(){
		
	}

}
