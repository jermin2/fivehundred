package main.java;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JPanel;

import cardgame.Card;
import common.Listener;

public class FiveHundredPanel extends JPanel implements Listener{

	//TODO: IMplement mouseActionListener for move
	HashMap<String, Image> images;
	FiveHundredHand hand;
	
	private final int CARD_SPACING = 15;
	private final int STARTING_X = 40;
	private final int STARTING_Y = 0;
	private final int CARD_WIDTH = 73;
	private final int CARD_HEIGHT = 97;
	
	/**
	 * 
	 * @param images Pointer to loaded images
	 */
	public FiveHundredPanel(HashMap<String, Image> images){
		this.images = images;
		
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				mouseClick(me);
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent me){
				mouseMove(me);
			}
		});
		
	}                           
	
	private void mouseClick(MouseEvent me){
		if(hand!=null){
			hand.select(returnCard(me.getX(), me.getY()));
			//System.out.println("Hand selected:" + hand.getSelected().toString());
			repaint();
		}
	}
	
	private void mouseMove(MouseEvent me){
		if(hand.size()>0){
			int ind = returnCard(me.getX(), me.getY());
			hand.hover(ind);
			
			//if(ind>=0)
			//System.out.println("Hand hovered:" + hand.getHovered().toString());
			repaint();
		}
	}
	
	/**
	 * Return the card that the mouse is over
	 * @param x
	 * @param y
	 * @return integer between 0 and the number of cards in the hand
	 */
	private int returnCard(int x, int y){
		
		if(y>STARTING_Y && y < (STARTING_Y+CARD_HEIGHT)){
			if(x>STARTING_X && x < ((hand.size()-1)*CARD_SPACING)+STARTING_X+CARD_WIDTH){
				if(hand.size()>=1){
					int cardToReturn = (x-STARTING_X)/CARD_SPACING;
					if(cardToReturn>(hand.size()-1))
						cardToReturn=(hand.size()-1);
					if(cardToReturn<0)
						cardToReturn = -1;
					
					return (int) cardToReturn;
				}
			}
		}
		return -1;
	}// End of return card
	
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
		
		int x=STARTING_X;
		
		if(hand.size()>0)
		for(Card c: hand.getCards()){
			
			if(c == hand.getSelected()){
				g.drawImage(images.get(hand.getSelected().getPicture()), x, 0, CARD_WIDTH, CARD_HEIGHT, this);
			}
			else
				if (c == hand.getHovered()){
					g.drawImage(images.get(hand.getHovered().getPicture()), x, 0,CARD_WIDTH, CARD_HEIGHT,  this);
				}
				else
					g.drawImage(images.get(c.getPicture()), x, 15, CARD_WIDTH, CARD_HEIGHT, this);
			
			x+=CARD_SPACING;
		}

	}
	
	public void doSomething(){
		repaint();
	}

	@Override
	public void notifyMe() {
		repaint();
	}
	

}
