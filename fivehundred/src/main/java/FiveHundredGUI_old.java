package main.java;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import cardgame.Card;

import javax.swing.JButton;
import java.awt.BorderLayout;

public class FiveHundredGUI_old {

	private JFrame frame;

	private FiveHundredGame game;
	private ArrayList<FiveHundredHand> players;
	private ArrayList<Card> kitty;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FiveHundredGUI_old window = new FiveHundredGUI_old();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FiveHundredGUI_old() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnDeal = new JButton("Deal");
		frame.getContentPane().add(btnDeal, BorderLayout.WEST);
	}
	
	
	/**
	 * Initialize the game
	 */
	public void init(){

	}
	
	/**
	 * Create a new Game of five hundred. Name the players then
	 */
	private void newGame(){
		players = new ArrayList<FiveHundredHand>();
		players.add(new FiveHundredHand("north"));
		players.add(new FiveHundredHand("west"));
		players.add(new FiveHundredHand("south"));
		players.add(new FiveHundredHand("east"));
		game = new FiveHundredGame(players);
	}
}
