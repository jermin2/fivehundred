package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import cardgame.Card;
import cardgame.Card.Suits;
import cardgame.Deck;
import common.Listener;
import common.Notifier;

public class FiveHundredGame implements Notifier {

	private Deck deck;
	private FiveHundredHand north, west, south, east, kitty;
	private HashMap<Players,FiveHundredHand> players;
	
	public enum Players {north, west, south, east, kitty, none};	
	
	private ArrayList<String> bidding;
	
	private HashMap<Players,Card> tableCards;
	
	private RoundState gameState;
	
	public enum trumps {spades, clubs, diamonds, hearts, no_trumps, misere}
	
	private String bids[] = {"6s", "6c", "6d","6h","6NT","7s","7c","7d","7h","7NT","CLOSED","8s","8c","8d","8h","8NT","OPEN",
			"9s","9c","9d","9h","9NT","10s","10c","10d","10h","10NT"};
	
	
	/**
	 * Internal class to keep track of the Hand (as in all 10 cards played. There should be 10 RoundState objects
	 * used/created. Needs to know the winning bid
	 * @author Jermin
	 *
	 */
	class HandState {
		private trumps current_trump;
		private RoundState current_round;
		
		//Keep track of score
		private int score_north_south = 0;
		private int score_west_east = 0;
		
		public HandState() {
			Players firstPlayer = doBid();
			current_round = new RoundState();
			current_round.setFirstPlayer(firstPlayer);
		}
		
		/**
		 * Checks the state to see if the round has ended. If it has, then calculate 
		 * the winning hand, increment the score, and start the next round
		 */
		public void checkForEnd(){
			//Change the state if stuff happens
			if(current_round.roundEnd()){
				//TODO: find a way to calculate the winner of the round
				Players round_winner = Players.north; 
				
				//Add the score to the winning team
				if(round_winner == Players.north || round_winner == Players.south)
					score_north_south++;
				else
					score_west_east++;
				
				//Reset the round and start the next round
				current_round.reset();
				current_round.setFirstPlayer(round_winner);
			}
		}// End checkForEnd()

		
		/**
		 * Sets the trump and returns the winning player
		 * @return the
		 */
		public Players doBid(){
			current_trump = trumps.spades;
			return Players.north;
		}
	}
	/**
	 * Internal class to keep track of whos turn it is for the Round (as in each
	 * player plays one hand)
	 * @author jermi
	 *
	 */
	class RoundState {
		
		private Players currentPlayer = Players.none;	
		private Players firstPlayer = Players.none;
		private boolean firstCard = true;
		
		
		public void reset(){
			currentPlayer = Players.none;
			firstPlayer = Players.none;
			firstCard = true;
		}
		/**
		 * Set the first player
		 * @param p The first player of the round
		 */
		public void setFirstPlayer(Players p){
			firstPlayer = p;
			currentPlayer = p;
			firstCard = true;
		}
		
		/**
		 * Return the first player
		 * @return
		 */
		public Players getFirstPlayer(){
			return firstPlayer;
		}
		
		/**
		 * Return the current player
		 * @return
		 */
		public Players getCurrentPlayer(){
			return currentPlayer;
		}
		
		/**
		 * Return the next player in the list. Goes north, west, south, east
		 * @return
		 */
		public Players getNextPlayer(){
			switch(currentPlayer){
			case north:
				currentPlayer = Players.west;
				break;
			case west:
				currentPlayer = Players.south;
				break;
			case south:
				currentPlayer = Players.east;
				break;
			case east:
				currentPlayer = Players.north;
				break;
			default:
				break;
			}
			
			firstCard = false;
			return currentPlayer;
		}
		
		public boolean roundEnd(){
			if(firstCard == false)						//If not the first card anymore
				if (currentPlayer == firstPlayer)		//If we have gone full circle to the first player
					return true;						//End game
			return false;
		}

	}
	
	/**
	 * Create a new Five Hundred Game. Player list is passed in from north, west, south, and east seats
	 * @param players List of FiveHundredHand players. Order passed in is north, west, south, east
	 */
	public FiveHundredGame (HashMap<Players,FiveHundredHand> players){
		this.players = players;
		north = players.get(Players.north);
		west = players.get(Players.west);
		south = players.get(Players.south);
		east = players.get(Players.east);
		kitty = players.get(Players.kitty);
		
		createDeck();
		
		tableCards = new HashMap<Players, Card>();
		
		gameState = new RoundState();
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
		FiveHundredHand currentPlayer = players.get(gameState.getCurrentPlayer());
		
		//Check if that player has selected a card
		if(currentPlayer.getSelected()!=null){
			
			//If so remove the card
			Card playedCard = currentPlayer.removeSelected();
			
			//Add it to the table cards
			tableCards.put(gameState.getCurrentPlayer(), playedCard);
			
			//If everyone has played their cards
			if(gameState.roundEnd()){
				endRound();
			} else {
				//Set the game state to the next player
				gameState.getNextPlayer();
			}
		}

	}
	
	
	public void endRound(){
		//Clear the current player and first player
		gameState.setFirstPlayer(Players.none);
		
		
	}
	
	/**
	 * Need to know the winning suit, and the first card led
	 * @param leadingSuit
	 * @param card
	 * @return false if not valid for current hand
	 */
	public boolean checkValidCard(Suits winningSuit, Card firstCard, Card currentCard){
		//Check if its a trump suit or not
		
		//Check that its the same suit
		if(sameSuit(firstCard, currentCard)){
			
		}
		return false;
	}
	
	/**
	 * 
	 * @param card1
	 * @param card2
	 * @return
	 */
	private boolean sameSuit(Card card1, Card card2){
		if(card1.getSuit() == card2.getSuit())
			return true;
		return false;
	}

/*  Listener/Notifier stuff
 * (non-Javadoc)
 * @see common.Notifier#addListener(common.Listener)
 */
	@Override
	public void addListener(Listener newListener) {
		// TODO Auto-generated method stub
		listeners.add(newListener);
	}

	ArrayList<Listener> listeners = new ArrayList<Listener>(); 
	@Override
	public void notify_all() {
		// TODO Auto-generated method stub
		for(Listener l : listeners){
			l.notifyMe();
		}
	}
}
