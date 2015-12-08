package main.java;

import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;

import main.java.FiveHundredGame.Players;

import java.awt.Insets;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FiveHundredGUI {

	private JFrame frmFiveHundred;
	
	private FiveHundredPanel ncardPanel;
	private FiveHundredPanel ecardPanel;
	private FiveHundredPanel scardPanel;
	private FiveHundredPanel wcardPanel;
	
	private HashMap<String, Image> images;
	
	private FiveHundredGame game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FiveHundredGUI window = new FiveHundredGUI();
					window.frmFiveHundred.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FiveHundredGUI() {
		
		loadImages();
		initialize();
		initialize_game();
	}
	
	public void initialize_game(){
		
		HashMap<FiveHundredGame.Players,FiveHundredHand> players = new HashMap<FiveHundredGame.Players,FiveHundredHand>();

		FiveHundredHand n = new FiveHundredHand("north");
		ncardPanel.setHand(n);
		players.put(Players.north,n);
		
		FiveHundredHand w = new FiveHundredHand("west");
		wcardPanel.setHand(w);
		players.put(Players.west,w);
		
		FiveHundredHand s = new FiveHundredHand("south");
		scardPanel.setHand(s);
		players.put(Players.south,s);
		
		FiveHundredHand e = new FiveHundredHand("east");
		ecardPanel.setHand(e);
		players.put(Players.east,e);
		

		
		game = new FiveHundredGame(players);
	}
	
	public void loadImages(){

		//Graphics acceleration
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		images = new HashMap<String, Image>();

		String ranks[] = {"a","2","3","4","5","6","7","8","9","t","j","q","k"};
		String suits[] = {"s","c","d","h"};

		ArrayList<String> cards = new ArrayList<String>();
		
		cards.add("b");
		cards.add("j");
		
		for( int r=0;r<ranks.length;r++)
			for(int s=0;s<suits.length;s++){
				cards.add(ranks[r]+suits[s]);
		
			}// End for loop
		
		for(String s : cards){
				
				String full_name = "/cards/"+s+".gif";
				URL url = getClass().getResource(full_name);
				System.out.println(url);
				BufferedImage sourceImage = null;
				
				try {
					sourceImage = ImageIO.read(url);
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				int pic_width = sourceImage.getWidth();
				int pic_height = sourceImage.getHeight();
				
				Image img = gc.createCompatibleImage(pic_width, pic_height, Transparency.BITMASK);
				img.getGraphics().drawImage(sourceImage, 0, 0, null);
				
				//Place image in the repository
				images.put(s, img);
		}//End of for
		
		
		System.out.println("loaded images");
	}
	
	public void newHand(){
		game.newHand();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFiveHundred = new JFrame();
		frmFiveHundred.setTitle("Five Hundred");
		frmFiveHundred.setBounds(100, 100, 661, 396);
		frmFiveHundred.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {127, 127, 127};
		gridBagLayout.rowHeights = new int[] {82, 82, 82};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0};
		frmFiveHundred.getContentPane().setLayout(gridBagLayout);
		
		ncardPanel = new FiveHundredPanel(images);
		GridBagConstraints gbc_ncardPanel = new GridBagConstraints();
		gbc_ncardPanel.fill = GridBagConstraints.BOTH;
		gbc_ncardPanel.insets = new Insets(0, 0, 5, 5);
		gbc_ncardPanel.gridx = 1;
		gbc_ncardPanel.gridy = 0;
		frmFiveHundred.getContentPane().add(ncardPanel, gbc_ncardPanel);
		
		wcardPanel = new FiveHundredPanel(images);
		GridBagConstraints gbc_wcardPanel = new GridBagConstraints();
		gbc_wcardPanel.fill = GridBagConstraints.BOTH;
		gbc_wcardPanel.insets = new Insets(0, 0, 5, 5);
		gbc_wcardPanel.gridx = 0;
		gbc_wcardPanel.gridy = 1;
		frmFiveHundred.getContentPane().add(wcardPanel, gbc_wcardPanel);
		
		JPanel tablePanel = new JPanel();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.insets = new Insets(0, 0, 5, 5);
		gbc_tablePanel.fill = GridBagConstraints.BOTH;
		gbc_tablePanel.gridx = 1;
		gbc_tablePanel.gridy = 1;
		frmFiveHundred.getContentPane().add(tablePanel, gbc_tablePanel);
		GridBagLayout gbl_tablePanel = new GridBagLayout();
		gbl_tablePanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_tablePanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_tablePanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_tablePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		tablePanel.setLayout(gbl_tablePanel);
		
		JButton button = new JButton("New Hand");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newHand();
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		tablePanel.add(button, gbc_button);
		
		ecardPanel = new FiveHundredPanel(images);
		GridBagConstraints gbc_ecardPanel = new GridBagConstraints();
		gbc_ecardPanel.fill = GridBagConstraints.BOTH;
		gbc_ecardPanel.insets = new Insets(0, 0, 5, 0);
		gbc_ecardPanel.gridx = 2;
		gbc_ecardPanel.gridy = 1;
		frmFiveHundred.getContentPane().add(ecardPanel, gbc_ecardPanel);
		
		scardPanel = new FiveHundredPanel(images);
		GridBagConstraints gbc_scardPanel = new GridBagConstraints();
		gbc_scardPanel.fill = GridBagConstraints.BOTH;
		gbc_scardPanel.insets = new Insets(0, 0, 0, 5);
		gbc_scardPanel.gridx = 1;
		gbc_scardPanel.gridy = 2;
		frmFiveHundred.getContentPane().add(scardPanel, gbc_scardPanel);
	}

}
