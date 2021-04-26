/* This class is a GUI Frame called when the player presses on the help button.
	It details the general rules and the main keyboard commands to move the tetriminos on the grid 
*/
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
import java.awt.geom.RoundRectangle2D;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class helpPopUp extends JFrame implements ActionListener {	
    
    public static Clip helpSoundtrack;
    
    public static boolean closedWindow = true;
    
    private JButton exitButton;
    
    private JButton soundButton;
    
    private boolean sound = true;
    
    private grid data ;
    
    /** CONSTRUCTOR **/
    
    public helpPopUp (grid g) {
		
		data = g;
			// Frame definition //
		
		this.setTitle("Help Panel - How to Play : ");
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);        
        this.setResizable(false);
        this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, 1000, 600, 25, 25));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Panel containing the background image //
        
        JPanel panelImage = new JPanel();
        panelImage.setBounds(0,0,1000,600);
        panelImage.setLayout(null);
        // panelImage.setBackground(Color.BLACK); ---> for testing purposes only
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Buttons //
			
		Border roundedBorder = new LineBorder(Color.WHITE, 3, true);
			
		exitButton = new JButton(new ImageIcon("back.png"));
		//exitButton.setText("EXIT");
		exitButton.setBounds(930,10,40,40);
		exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(Color.WHITE);
		panelImage.add(exitButton);
		exitButton.addActionListener(this);
		
		soundButton = new JButton (new ImageIcon("Soundicon.png"));
		soundButton.setBounds(30,20,20,20);	
		soundButton.setOpaque(false);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Panels containing the information //
        
        JPanel controlKeys = new RoundedJPanel(30,new Color(0,0,0,100),Color.WHITE, true, true);
        controlKeys.setBounds(30,55,200,520);
        controlKeys.setLayout(null);
        controlKeys.setOpaque(false);
        //controlKeys.setBackground(new Color(0,0,0,100));
        //controlKeys.setBorder(roundedBorder);

        // controlKeys.setBackground(Color.RED); ---> for testing purposes only
        
        JPanel generalRules = new RoundedJPanel(30,new Color(0,0,0,100),Color.WHITE, true, true);
        generalRules.setBounds(250,375,730,200);
        generalRules.setLayout(null);
		generalRules.setOpaque(false);
		//generalRules.setBackground(new Color(0,0,0,100));
		//generalRules.setBorder(roundedBorder);
        // generalRules.setBackground(Color.RED); ---> for testing purposes only
        
        //JPanel generalRulesBis = new RoundedJPanel(30,new Color(0,0,0,100),Color.WHITE, true, true);
        JPanel generalRulesBis = new JPanel();
        generalRulesBis.setBounds(250,55,300,300);
        generalRulesBis.setLayout(null);
		//generalRulesBis.setOpaque(false);
        generalRulesBis.setBorder(roundedBorder);
        
        JPanel tetriminos = new JPanel();
        tetriminos.setBounds(570,55,402,302);
        tetriminos.setLayout(null);
        //tetriminos.setOpaque(false);
        tetriminos.setBackground(Color.white);
        tetriminos.setBorder(roundedBorder);
        // tetriminos.setBackground(Color.RED); ---> for testing purposes only
        
        Icon imgIcon = new ImageIcon(this.getClass().getResource("tetriminos.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(2,2,398,298); 
		//this.getContentPane().add(label);	
		tetriminos.add(label);	
		
		JLabel tetrisvintage = new JLabel(new ImageIcon("tetrisvintage.jpg"));
 		tetrisvintage.setBounds(0,0,generalRulesBis.getWidth(),generalRulesBis.getHeight());
 		generalRulesBis.add(tetrisvintage);
		
		/*Icon imgIcon2 = new ImageIcon(this.getClass().getResource("loading4.gif"));
		JLabel label2 = new JLabel(imgIcon2);
		label2.setBounds(0,0,300,300); 
		//this.getContentPane().add(label);	
		generalRulesBis.add(label2);	*/
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Text Areas in Panels // 
			
		JTextPane textPaneControlKeys = new JTextPane();
		textPaneControlKeys.setBounds(20,60,160,420);
		textPaneControlKeys.setOpaque(false);
		//textPaneGeneralRules.setContentType("text/html");
		controlKeys.add(textPaneControlKeys);
		
		
		JTextPane textPaneGeneralRules = new JTextPane();
		textPaneGeneralRules.setBounds(20,50,660,250);
		textPaneGeneralRules.setOpaque(false);
		textPaneGeneralRules.setEditable(false);
		textPaneGeneralRules.setForeground(Color.black);
		generalRules.add(textPaneGeneralRules);
		//textPaneGeneralRules.setContentType("text/html");
		
		/*
		JScrollPane scrollPane = new JScrollPane(textPaneGeneralRules);
		scrollPane.setBounds(20,50,690,130);
		scrollPane.setOpaque(false);
		scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		generalRules.add(scrollPane);
		*/
		
		JTextPane textPaneGeneralRulesBis = new JTextPane();
		textPaneGeneralRulesBis.setBounds(20,20,290,260);
		textPaneGeneralRulesBis.setOpaque(false);
		//textPaneGeneralRules.setContentType("text/html");
		generalRulesBis.add(textPaneGeneralRulesBis);
		
		//JLabel tetriminoImage = new JLabel(new ImageIcon("tetrimino.jpg"));
		//tetriminoImage.setBounds(0,0,tetriminos.getWidth(),tetriminos.getHeight());
		//tetriminos.add(tetriminoImage);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
			// Text Strings for rules //
		
		// KEYS //
			
		JLabel upperArrow = new JLabel();
        upperArrow.setFont(new Font("Times Roman", Font.BOLD, 12));
        upperArrow.setText(" 1/4 Rotation ");
        upperArrow.setForeground(Color.WHITE);
        upperArrow.setBounds(20,70,150,30);
        upperArrow.setIcon(new ImageIcon("upperarrow.jpg"));
        controlKeys.add(upperArrow);
        
        JLabel lowerArrow = new JLabel();
        lowerArrow.setFont(new Font("Times Roman", Font.BOLD, 12));
        lowerArrow.setText(" Drop the piece ");
        lowerArrow.setForeground(Color.WHITE);
        lowerArrow.setBounds(20,110,150,30);
		lowerArrow.setIcon(new ImageIcon("lowerarrow.jpg"));
        controlKeys.add(lowerArrow);
        
        JLabel rightArrow = new JLabel();
        rightArrow.setFont(new Font("Times Roman", Font.BOLD, 12));
        rightArrow.setText(" Right translation ");
        rightArrow.setForeground(Color.WHITE);
        rightArrow.setBounds(20,150,150,30);
        rightArrow.setIcon(new ImageIcon("rightarrow.jpg"));
        controlKeys.add(rightArrow);
        
        JLabel leftArrow = new JLabel();
        leftArrow.setFont(new Font("Times Roman", Font.BOLD, 12));
        leftArrow.setText(" Right translation ");
        leftArrow.setForeground(Color.WHITE);
        leftArrow.setBounds(20,190,150,30);
        leftArrow.setIcon(new ImageIcon("leftarrow.jpg"));
        controlKeys.add(leftArrow);
        
        JLabel soundLabel = new JLabel();
		soundLabel.setFont(new Font("Times Roman", Font.BOLD, 12));
        soundLabel.setText(" SOUND ON / OFF ");
        soundLabel.setForeground(Color.WHITE);
        soundLabel.setBounds(20,230,150,30);
        soundLabel.setIcon(new ImageIcon("Soundicon.png"));
        controlKeys.add(soundLabel);
        
        JLabel helpLabel = new JLabel();
        helpLabel.setFont(new Font("Times Roman", Font.BOLD, 12));
        helpLabel.setText(" Help Panel ");
        helpLabel.setForeground(Color.WHITE);
        helpLabel.setBounds(20,270,150,30);
        helpLabel.setIcon(new ImageIcon("help.png"));
        controlKeys.add(helpLabel);
        
        
        JLabel backLabel = new JLabel();
        backLabel.setFont(new Font("Times Roman", Font.BOLD, 12));
        backLabel.setText(" Return back ");
        backLabel.setForeground(Color.WHITE);
        backLabel.setBounds(20,310,140,30);
        backLabel.setIcon(new ImageIcon("back.png"));
        controlKeys.add(backLabel);

        JLabel pauseLabel = new JLabel();
        pauseLabel.setFont(new Font("Times Roman", Font.BOLD, 12));
        pauseLabel.setText(" Play / Pause ");
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setBounds(20,350,150,30);
        pauseLabel.setIcon(new ImageIcon("playbis.png"));
        controlKeys.add(pauseLabel);
        
        // [ Other commands on the same model -> just copy + change labelName, name image and add 40 to the vertical component ]
        
		/*String Keys1 = "Left Arrow : " + "\n" + " - Move Left -" + "\n";
		String Keys2 = "Right Arrow : " + "\n" + " - Move Right -" + "\n";
		String Keys3 = "Upper Arrow : " + "\n" + " - 1/4 Rotation - " + "\n";
		String Keys4 = "Down Arrow : " + "\n" + " - Move Down -" + "\n";
		
		textPaneControlKeys.setText(textPaneControlKeys.getText() + Keys1 + "\n");
		textPaneControlKeys.setText(textPaneControlKeys.getText() + Keys2 + "\n");
		textPaneControlKeys.setText(textPaneControlKeys.getText() + Keys3 + "\n");
		textPaneControlKeys.setText(textPaneControlKeys.getText() + Keys4 + "\n");*/
        
        // RULES //
		
		textPaneGeneralRules.setForeground(Color.WHITE);
		textPaneGeneralRules.setText("   The game of Tetris consists of dropping several shapes to form the biggest possible number of lines inside a grid.");
 		textPaneGeneralRules.setText(textPaneGeneralRules.getText() + "You are able to move these shapes left and right and to rotate them. ");
 		textPaneGeneralRules.setText(textPaneGeneralRules.getText() + "The goal is to get all the blocks to fill all the empty space in a line to avoid stacking. ");
 		textPaneGeneralRules.setText(textPaneGeneralRules.getText() + "Whenever you do this, the blocks of this line will vanish and you will be awarded some points. ");
		textPaneGeneralRules.setText(textPaneGeneralRules.getText() + "Once the screen is full and you can't place any more shapes, the game is over." + "\n");
		textPaneGeneralRules.setText(textPaneGeneralRules.getText() + "   You're able to modify multiple set-up to spice up your game experience. For instance, several different backgrounds, grid sizes or difficulties will be available. "+ "\n");
 		textPaneGeneralRules.setText(textPaneGeneralRules.getText() + "   Hope you will enjoy it ! TEAM TETR'INSA." + "\n");

	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Other components inside the Panels //
        
        JLabel helpText = new JLabel();
        helpText.setFont(new Font("Times Roman", Font.BOLD, 24));
        helpText.setText(" - HOW TO PLAY - ");
        helpText.setForeground(Color.WHITE);
        helpText.setBounds((panelImage.getWidth()-310)/2,10,310,30);
        helpText.setIcon(new ImageIcon("rules.png"));
        
        JLabel titleControlKeys = new JLabel();
        titleControlKeys.setFont(new Font("Times Roman", Font.BOLD, 18));
        titleControlKeys.setText(" CONTROL KEYS ");
        titleControlKeys.setHorizontalAlignment(JLabel.CENTER);
        titleControlKeys.setForeground(Color.WHITE);
        titleControlKeys.setBounds((controlKeys.getWidth()-160)/2,20,160,20);
        controlKeys.add(titleControlKeys);
        
        JLabel titleGeneralRules = new JLabel();
        titleGeneralRules.setFont(new Font("Times Roman", Font.BOLD, 18));
        titleGeneralRules.setText(" GENERAL RULES ");
        titleGeneralRules.setForeground(Color.WHITE);
        titleGeneralRules.setBounds((generalRules.getWidth()-160)/2,15,160,20);
        generalRules.add(titleGeneralRules);
        
        JLabel separationLine = new JLabel();
        separationLine.setFont(new Font("Times Roman", Font.BOLD, 18));
        separationLine.setText(" - - - - - - - ");
        separationLine.setHorizontalAlignment(JLabel.CENTER);
        separationLine.setForeground(Color.WHITE);
        separationLine.setBounds(20,40,160,20);
        controlKeys.add(separationLine);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Insertion of images inside the Panels //
        
		JLabel BackgroundHelp = new JLabel(new ImageIcon("background-help.jpg"));
		BackgroundHelp.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(BackgroundHelp);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Creation of the global Panel //
        
        JPanel helpPanel = new JPanel();
        helpPanel.setBounds(0,0,1000,600);
        // helpPanel.setBackground(Color.yellow); ---> for testing purposes only
        helpPanel.setLayout(null);
        helpPanel.add(helpText);
        helpPanel.add(controlKeys);
        helpPanel.add(generalRules);
        helpPanel.add(generalRulesBis);
        helpPanel.add(tetriminos);
        helpPanel.add(panelImage);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Adding this Panel to the general Frame //
		
        this.add(helpPanel);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Audio SoundTrack //
        
       try {
         
         File helpSoundFile = new File("help-menu-music.wav");		// Open an audio input stream.
         AudioInputStream audiohelpSound = AudioSystem.getAudioInputStream(helpSoundFile);
         helpSoundtrack = AudioSystem.getClip();		// Get a sound clip resource.
         helpSoundtrack.open(audiohelpSound);		// Open audio clip and load samples from the audio input stream.
         //helpSoundtrack.start();
         
		}catch(Exception e){ e.printStackTrace(); } 
		
		
		
    }
    
    public void actionPerformed (ActionEvent e){
		
		if (e.getSource()== exitButton){
			data.pauseTheGame();
			this.setVisible(false);
			helpSoundtrack.stop();
			closedWindow = true;
			
		}
		
		if (e.getSource() == soundButton) {
		
			if (sound == true) {
				helpSoundtrack.stop();
				sound = false;
			
			}else if (sound == false) {
				helpSoundtrack.start();
				sound = true;
				
			}
			
		}
	
	}
    
}


