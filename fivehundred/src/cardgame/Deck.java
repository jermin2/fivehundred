package cardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of cards. A standard deck can be created using createStandardDeck()
 * 
 * @author jermi
 *
 */
public class Deck {

	private ArrayList<Card> cards;				//List containing all the cards
	
	/**
	 * Create an empty Deck object with no cards. The deck needs to be populated by calling
	 * addCard() method, or by calling one of the static create deck methods
	 */
	public Deck(){
		cards = new ArrayList<Card>();
	}
	
	/**
	 * Creates an standard 54 card deck with 2 jokers and shuffles it
	 * @return Standard shuffled 54 card deck
	 */
	public static Deck createStandardDeck() {
		Deck deck = new Deck();
		
		short jokerSuit = 5;
		short jokerRank = 0;
		
		deck.cards.add(new Card(jokerSuit, jokerRank));
		deck.cards.add(new Card(jokerSuit, jokerRank));
		
		for (short suit=0; suit<=3; suit++) {
			for (short rank=0; rank<=12; rank++){
				deck.cards.add(new Card(suit,rank));
			}
		} // End of for loop
		
		deck.shuffleStandard();
		
		return deck;
	}//End of Deck()
	
	/**
	 * Draw the top card from the deck
	 * @return
	 */
	public Card dealCard(){
		if(cards.size() <= 0){
			return null;					//Return null if no cards left
		}
		return cards.remove(0);
	}//End of dealCard
	
	/**
	 * Get the total number of cards left in the deck
	 * @return
	 */
	public int getTotalCards(){
		return cards.size();
	}//End of getTotalCards()
	
	/**
	 * Add a card to the bottom of the deck
	 * @param card
	 */
	public void addCard(Card card){
		cards.add(card);
	}//End of addCard()
	
	/**
	 * Returns true if the deck is empty, otherwise false
	 * @return
	 */
	public boolean isEmpty(){
		if(cards.size()>0)
			return false;
		return true;
	}//End of isEmpty()
	
	/**
	 * Shuffles the deck by swapping 2 random cards, and doing this 1000 times
	 */
	public void shuffleStandard(){
		int index_1, index_2;
		Random generator = new Random();
		Card temp;
		
		for(int i=0; i<1000; i++){
			index_1 = generator.nextInt(cards.size());
			index_2 = generator.nextInt(cards.size());
			temp=cards.get(index_2);
			cards.set(index_2, cards.get(index_1));
			cards.set(index_1,  temp);
		}		
	}
	
	/**
	 * Shuffle more realistically, by grouping cards together when shuffling
	 */
	public void shuffleRealistic(){}
	
	
}
