package main.java;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JPanel;

import cardgame.Card;

public class FiveHundredPanel extends JPanel {

	
	HashMap<String, Image> images;
	FiveHundredHand hand;
	
	/**
	 * 
	 * @param images Pointer to loaded images
	 */
	public FiveHundredPanel(HashMap<String, Image> images){
		this.images = images;
		
	}                           
	
	/**
	 * Set the hand for the this panel. This ties the player to this panel and this panel will display
	 * the players hand
	 * @param hand
	 */
	public void setHand(FiveHundredHand hand){
		this.hand = hand;
	}
	
	void drawCard(Graphics g, Card card, int x, int y) {
		//draws a card as a 80 by 100 rectangle
		//upper left corner at x, y. Card is drawn in graphics context g. If card is null, then 
		//it is a face-down card
		
		if (card == null) {
			//draw a face-down card
			g.drawImage(images.get("b"), x, y, this);
		}
		else {
			g.drawImage(images.get(card.getPicture()), x, y, this);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x=0;
		for(Card c: hand.getCards()){
			g.drawImage(images.get(c.getPicture()), x, 0, this);
			x+=15;
		}
	}
	
	public void doSomething(){
		repaint();
	}
}
