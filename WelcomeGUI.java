import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.event.*;
import javax.swing.border.*;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class WelcomeGUI extends JFrame implements ActionListener {	
    
    private grid data;
    
    private JButton Player1;
    
    private JButton Player2;
    
    private JButton NextButton;
    
    private JButton HelpButton;
    
    private JButton soundButton;
    
    public static info1PlayerPopUp OnePlayerInfo;
    
    private info2PlayerPopUp TwoPlayersInfo;
    
    public static boolean sound = true;
    
    protected static Clip tetrisSoundtrack;
    
    private Clip playSound;
    
    private Clip helpSound;
    
    private JLabel LogoINSA;
    
    private JLabel LogoGame;
    
    private JPanel welcomePanel;
    
    //private String backGround = "imageTetrisWelcome.jpg";
    
    private int backGroundNumber = 1;
    
    private JLabel Background;
    
    private JPanel panelImage;
    
    private JLabel Names;
    
    private int choice = 0;
    
    private ArrayList<shape> list;
    
    
    // ImageIcons : //
    /*
    private ImageIcon imageIconTetrisWelcome1 = new ImageIcon("imageTetrisWelcome.jpg");
    
    private ImageIcon imageIconTetrisWelcome2 = new ImageIcon("imageTetrisWelcome2.jpg");
    
    private ImageIcon imageIconTetrisWelcome3 = new ImageIcon("imageTetrisWelcome4.jpg");
    */
    
    /** CONSTRUCTOR **/
    
    public WelcomeGUI (grid g /*ArrayList<shape> l*/){
		
		data = g;
        //list = l;
		
		// Definition of the Frame //
        
        this.setTitle("Welcome Screen Tetris:");
        //Set size of the window
        this.setSize(600,400);
        //Intital position
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
		// Panel containing the image // 
		
        panelImage = new JPanel();
        panelImage.setBounds(0,0,600,400);
        panelImage.setLayout(null);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	 
		/***********************************************************************************
         * BUTTONS
         * */ 
        
    
        Border roundedBorder = new LineBorder(Color.WHITE, 2, true);
        
        /**
         * 1 PLAYER choice BUTTON
         * open the infoPlayerPopUp corresponding window
         * */
        
        Player1 = new JButton();
		Player1.setText("One Player");
		Player1.setBounds(200,130,200,60);
		Player1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		Player1.setForeground(Color.WHITE);
		Player1.setBackground(new Color(255,255,255,100));
		Player1.setBorder(roundedBorder);
		Player1.setOpaque(false);
		Player1.setIcon(new ImageIcon("emoji-video-game.png"));
		
		/*JLabel icon1 = new JLabel(new ImageIcon("emoji-video-game.jpg"));
		icon1.setBounds(10,0,40,40);
		Player1.add(icon1);*/
		
		panelImage.add(Player1);
		Player1.addActionListener(this);
        
        /**
         * 2 PLAYERS choice BUTTON
         * open the infoPlayerPopUp corresponding window
         * */
		
		Player2 = new JButton();
		Player2.setText("Two Players");
		Player2.setBounds(200,220,200,60);
		Player2.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		Player2.setForeground(Color.WHITE);
		Player2.setBackground(new Color(255,255,255,100));
		Player2.setBorder(roundedBorder);
		Player2.setOpaque(false);
		Player2.setIcon(new ImageIcon("videogame.png"));
		panelImage.add(Player2);
		Player2.addActionListener(this);
        
        /**
         * SOUND BUTTON
         * to turn off/on the music
         * */
		
		soundButton = new JButton (new ImageIcon("Soundicon.png"));
		soundButton.setBounds(20,20,20,20);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
        
        /**
         * HELP BUTTON
         * open the helpPopUp window
         * */
        
        HelpButton = new JButton (new ImageIcon("help.png"));
		HelpButton.setBounds(50,20,20,20);	
		//HelpButton.setText("?");
		HelpButton.setFont(new java.awt.Font("Times Roman", Font.BOLD, 14));
		HelpButton.setForeground(Color.BLACK);
		HelpButton.setBackground(Color.WHITE);
		panelImage.add(HelpButton);
		HelpButton.addActionListener(this);
		
		/*String SQR = "\u1f43b";
		JButton b = new JButton(SQR);
		b.setBounds(60,20,20,20);	
		panelImage.add(b);*/

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
		// Copyrights // 
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(80,325,400,40);
		namePanel.setLayout(null);
		namePanel.setBackground(new Color(255,255,255,155));
		panelImage.add(namePanel); 
        
        Names = new JLabel();
        Names.setForeground(Color.black);
		Names.setBackground(Color.black);
		Names.setFont(new Font("Times Roman", Font.BOLD, 14));
		Names.setText("Project by Joseph B, Paul TD, Bich-Lien P, Flora G");
		Names.setBounds(0,0,400,40);
		Names.setBorder(new BevelBorder(BevelBorder.LOWERED));
		Names.setIcon(new ImageIcon ("laptop.png"));
		namePanel.add(Names); 
			
		
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //		
	 
		// Insertion of the images // 
		
		/*ImageIcon logoINSA = new ImageIcon("insa_logo.png");
		Image scaledlogoINSA = logoINSA.getScaledImage(60,20,Image.SCALE_SMOOTH);
		JLabel LogoINSA = new JLabel(logoINSA);
		LogoINSA.setBounds(20,320,60,20);
		panelImage.add(LogoINSA);*/
		
		LogoINSA = new JLabel(new ImageIcon("insa_logo.png"));
		LogoINSA.setBounds(510,20,60,24);
		panelImage.add(LogoINSA);
		
		/*LogoGame = new JLabel(new ImageIcon("logo.png"));
		LogoGame.setBounds(140,20,320,80);
		panelImage.add(LogoGame);
		*/
		
		Background = new JLabel(new ImageIcon("backgroundPropositionWelcome.png"));
		Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(Background);
        
        welcomePanel = new JPanel();
        welcomePanel.setBounds(0,0,600,400);
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(Color.white);
        welcomePanel.add(panelImage);
        
        this.add(welcomePanel);
        
        /*OnePlayer = new tetrisGUI ();
        OnePlayer.setVisible(false);
        
        TwoPlayers = new tetrisGUI2 ();
        TwoPlayers.setVisible(false);*/ // -> Create from infoPlayer...
        
        //HelpWindow = new helpPopUp ();
        //HelpWindow.setVisible(false);
        
        this.setVisible(true);
        
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
		
		// Audio SoundTrack //
        
        try {
         
         File tetrisSoundFile = new File("tetris-theme-officiel.wav");		// Open an audio input stream.
         AudioInputStream audiotetrisSound = AudioSystem.getAudioInputStream(tetrisSoundFile);
         tetrisSoundtrack = AudioSystem.getClip();		// Get a sound clip resource.
         tetrisSoundtrack.open(audiotetrisSound);		// Open audio clip and load samples from the audio input stream.
		
		if (helpPopUp.closedWindow == true) {
			tetrisSoundtrack.start();
		}
         
         File playSoundFile = new File("here-we-go.wav");
         AudioInputStream audioplaySound = AudioSystem.getAudioInputStream(playSoundFile);
         playSound = AudioSystem.getClip();
         playSound.open(audioplaySound);
         
         File helpSoundFile = new File("pacman_death.wav");
         AudioInputStream audiohelpSound = AudioSystem.getAudioInputStream(helpSoundFile);
         helpSound = AudioSystem.getClip();
         helpSound.open(audiohelpSound);
         
		}catch(Exception e){ e.printStackTrace(); }
        
    }
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
    
    public void actionPerformed (ActionEvent e){
		
		if (e.getSource()== Player1){
			choice = 1;
            OnePlayerInfo = new info1PlayerPopUp (data);
            OnePlayerInfo.setVisible(true);
            //this.setVisible(false);
            //OnePlayer.setVisible(true);
            playSound.start();
            //tetrisSoundtrack.stop();
		}
		
		if (e.getSource()== Player2){
			choice = 2;
            // SAME AS CHOICE 1 FOR THE MOMENT
            //OnePlayerInfo = new info1PlayerPopUp (data, list);
            //OnePlayerInfo.setVisible(true);
            //this.setVisible(false);
            //OnePlayer.setVisible(true);
            //playSound.start();
            //tetrisSoundtrack.stop();
            
            /** it should be the code just below but we create a single player mode game for now **/
            
            TwoPlayersInfo = new info2PlayerPopUp (data);
            TwoPlayersInfo.setVisible(true);
            //this.setVisible(false);
            //TwoPlayers.setVisible(true);
            playSound.start();
            //tetrisSoundtrack.stop();
            //System.out.println("No 2 players mode for now ...");
            
		}
		
		if (e.getSource() == HelpButton){
			
			helpPopUp HelpWindow = new helpPopUp (data);
			HelpWindow.setVisible(true);
			helpSound.start();
			tetrisSoundtrack.stop();
			helpPopUp.helpSoundtrack.start();
			helpPopUp.closedWindow = true;
			
		}
		
		if (e.getSource() == soundButton) {
		
			if (data.soundOn == true) {
				tetrisSoundtrack.stop();
				data.soundOn = false;
			
			}else if (data.soundOn == false) {
				tetrisSoundtrack.start();
				data.soundOn = true;
				
			}
			
		}
		
		/*if (e.getSource() == soundButton) {
            URL urlClick = WelcomeGUI.class.getResource("tetris-theme-officiel.wav");
            tetrisSoundtrack = Applet.newAudioClip(urlClick);
            tetrisSoundtrack.loop();
        }*/
     
	}
    
    
}
