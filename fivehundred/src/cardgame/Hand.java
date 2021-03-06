package cardgame;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Hand {

	private ArrayList<Card> hand = new ArrayList<Card>();
	private Card selected = null;
	private Card hover = null;
	
	public void hover(int index){
		if(index < 0)
			this.hover = null;
		else
			this.hover = hand.get(index);
	}
	
	/**
	 * Get the currently selected card
	 */
	public Card getHovered () {
		return hover;
	}
	
	public void select(int index){
		if(index < 0){
			this.selected = null;
		} else{
			if(hand.get(index) == this.getSelected())
				this.selected = null;
			else
				this.selected = hand.get(index);
		}
	}
	
	/**
	 * Get the currently selected card
	 */
	public Card getSelected () {
		return selected;
	}
	
	public Card removeSelected(){
		
		return removeCard(selected);
	}
	
	/**
	 * Add a card to this hand
	 * @param card card to be added
	 */
	public void addCard (Card card) {
		hand.add( card);
	}//End of addCard
	
	/**
	 * Find the card at the index. Does not remove the card
	 * @param index
	 * @return
	 */
	public Card getCard(int index) {
		return hand.get(index);
	}//End of getCard
	
	/**
	 * Return the size of the hand
	 * @return
	 */
	public int size(){
		return hand.size();
	}//End of size()
	
	/**
	 * Removes the specified card from the current hand.
	 * If the card is not present, return null
	 * @param card
	 * @return
	 */
	public Card removeCard( Card card){
		int index = hand.indexOf(card);
		if (index<0)
			return null;
		else
			return (Card) hand.remove(index);
	}//End of removeCard(card)
	
	/**
	 * Removes the card at the index
	 * @param index
	 * @return
	 */
	public Card removeCard(int index) {
		return hand.remove(index);
	}//End of removeCard(index)
	
	public void sort(){
		//TODO Collections.sort(hand);
	}
	
	/**
	 * Writes out all the cards in this hand in a readable string
	 */
	@Override
	public String toString(){
		String s = "";
		for (Card c : hand){
			/*if(c==null){
				return "null card";
			}*/
			s = s+c.toString() + "\n";
		}
		return s;
	}//End of toString
	
	/**
	 * Get the list of cards in this hand
	 * @return
	 */
	public ArrayList<Card> getCards(){
		return hand;
	}// End of getCards()
}
