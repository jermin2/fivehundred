package cardgame;

import java.awt.Graphics;

import common.Sprite;

public class Card {
	private short rank, suit, value;
	private String picture = "b";			//Shorthand name of picture
	private static String[] suits = {"spades", "clubs", "diamonds", "hearts" };
	private static String[] ranks = {"ace", "2", "3", "4","5", "6", "7","8","9","ten","jack","queen","king"};
	private static String[] jokers = {"joker", "joker"};
	private static String[] ranks2 = {"0","0"};
	
	public static short joker_suit = 5;
	public static short spades_suit = 0;
	public static short clubs_suit = 1;
	public static short diamonds_suit = 2;
	public static short hearts_suit = 3;
	
	public static String rankAsString( int __rank ) {
		if( __rank != 0){
			return ranks[__rank];
		} //End of if statement
		return ranks2[__rank];
	}//End of rankAsString
	
	public Card(short suit, short rank){
		this.rank = rank;
		this.suit = suit;
		
		if(suit<5)
			this.picture = String.valueOf(ranks[rank].charAt(0))+suits[suit].charAt(0);
		else
			this.picture= String.valueOf(jokers[0].charAt(0));
		System.out.println("New Card: " + picture);
	}//End of Card Initializer
	
	public @Override String toString(){
		if(suit==Card.joker_suit){
			return "Joker";
		}
		return ranks[rank] + " of " + suits[suit];
	}//End of toString method
	
	public short getRank() {
		return rank;
	}//End of getRank method
	
	public short getSuit() {
		return suit;
	}//End of getSuit method
	
	/** Return the String representing the picture associated with this card
	 * 
	 * @return
	 */
	public String getPicture(){
		return picture;
	}//End of picture
	
	/**
	 * Set the relative value of this card. Values are set externally by the game
	 * @param v
	 */
	public void setValue(short v){
		this.value = v;
	}// End setValue
	
	/**
	 * Get the relative value of this card. Values are set by calling the setValue(short v) function
	 * @return
	 */
	public short getValue(){
		return value;
	}// End getValue
	
	
}//End of Card
