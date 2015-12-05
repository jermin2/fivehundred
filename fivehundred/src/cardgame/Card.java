package cardgame;

import java.awt.Graphics;

import common.Sprite;

public class Card {
	private short rank, suit;
	private String picture = "b";			//Shorthand name of picture
	private static String[] suits = {"hearts", "spades", "diamonds", "clubs" };
	private static String[] ranks = {"Ace", "2", "3", "4","5", "6", "7","8","9","10","Jack","Queen","King"};
	private static String[] jokers = {"Joker", "Joker"};
	private static String[] ranks2 = {"0","0"};
	
	public static String rankAsString( int __rank ) {
		if( __rank != 0){
			return ranks[__rank];
		} //End of if statement
		return ranks2[__rank];
	}//End of rankAsString
	
	public Card(short suit, short rank){
		this.rank = rank;
		this.suit = suit;
		this.picture = ranks[rank]+suits[suit].charAt(0);
		System.out.println("New Card: " + picture);
	}//End of Card Initializer
	
	public @Override String toString(){
		if(suit==5){
			return "Joker";
		}
		if(rank==0){
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
	
}//End of Card
