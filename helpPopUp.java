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

/*
 *  Make Sound Functional (problem of superposition ...)
 * 	Make rounded corners + shadows : https://www.codeproject.com/Articles/114959/Rounded-Border-JPanel-JPanel-graphics-improvements
 *  Mouse follower : tetrimino image
 * 	Remplissage JPanels (images, texte ...) + Alignements textes JLabels (layout ...)
 * 	Other components ? (p10 slides Fenetres) + dans les autres classes graphiques ...
 * 
 * */ 

public class helpPopUp extends JFrame implements ActionListener {	
    
    private Clip helpSoundtrack;
    
    private JButton exitButton;
    
    private JButton soundButton;
    
    private boolean sound = true;
    
    public helpPopUp () {
		
		this.setTitle("Help Panel - How to Play : ");
        this.setSize(1000,600);
        this.setLocation(300,200);
        this.setLayout(null);        
        this.setResizable(false);
        this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, 1000, 600, 25, 25));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        
			// Panel containing the background image //
        
        JPanel panelImage = new JPanel();
        panelImage.setBounds(0,0,1000,600);
        panelImage.setLayout(null);
        // panelImage.setBackground(Color.BLACK); ---> for testing purposes only
        
			// Buttons //
			
		Border roundedBorder = new LineBorder(Color.WHITE, 3, true);
			
		exitButton = new JButton();
		exitButton.setText("EXIT");
		exitButton.setBounds(880,10,100,30);
		exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(Color.WHITE);
		panelImage.add(exitButton);
		exitButton.addActionListener(this);
		
		soundButton = new JButton (new ImageIcon("Soundicon.png"));
		soundButton.setBounds(20,20,20,20);	
		soundButton.setOpaque(false);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
        
			// Panels containing the information //
        
        JPanel controlKeys = new JPanel();
        controlKeys.setBounds(30,50,200,520);
        controlKeys.setLayout(null);
        //controlKeys.setOpaque(false);
        controlKeys.setBackground(new Color(0,0,0,100));
        controlKeys.setBorder(roundedBorder);

        // controlKeys.setBackground(Color.RED); ---> for testing purposes only
        
        JPanel generalRules = new JPanel();
        generalRules.setBounds(270,50,700,330);
        generalRules.setLayout(null);
		//generalRules.setOpaque(false);
		generalRules.setBackground(new Color(0,0,0,100));
		generalRules.setBorder(roundedBorder);
        // generalRules.setBackground(Color.RED); ---> for testing purposes only
        
        JPanel tetriminos = new JPanel();
        tetriminos.setBounds(270,420,700,150);
        tetriminos.setLayout(null);
        //tetriminos.setOpaque(false);
        tetriminos.setBackground(new Color(0,0,0,100));
        tetriminos.setBorder(roundedBorder);
        // tetriminos.setBackground(Color.RED); ---> for testing purposes only
        
			// Other components inside the Panels //
        
        JLabel helpText = new JLabel();
        helpText.setFont(new Font("Times Roman", Font.BOLD, 24));
        helpText.setText(" - HOW TO PLAY - ");
        helpText.setForeground(Color.WHITE);
        helpText.setBounds(350,10,400,30);
        
        JLabel titleControlKeys = new JLabel();
        titleControlKeys.setFont(new Font("Times Roman", Font.BOLD, 18));
        titleControlKeys.setText(" CONTROL KEYS");
        titleControlKeys.setHorizontalAlignment(JLabel.CENTER);
        titleControlKeys.setForeground(Color.WHITE);
        titleControlKeys.setBounds(20,20,160,20);
        controlKeys.add(titleControlKeys);
        
        JLabel titleGeneralRules = new JLabel();
        titleGeneralRules.setFont(new Font("Times Roman", Font.BOLD, 18));
        titleGeneralRules.setText(" GENERAL RULES ");
        titleGeneralRules.setHorizontalAlignment(JLabel.CENTER);
        titleGeneralRules.setForeground(Color.WHITE);
        titleGeneralRules.setBounds(300,20,160,20);
        generalRules.add(titleGeneralRules);
        
        JLabel separationLine = new JLabel();
        separationLine.setFont(new Font("Times Roman", Font.BOLD, 18));
        separationLine.setText(" - - - - - - - ");
        separationLine.setHorizontalAlignment(JLabel.CENTER);
        separationLine.setForeground(Color.WHITE);
        separationLine.setBounds(20,40,160,20);
        controlKeys.add(separationLine);
        
			// Insertion of images inside the Panels //
        
		JLabel BackgroundHelp = new JLabel(new ImageIcon("background-help.jpg"));
		BackgroundHelp.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(BackgroundHelp);
        
        
			// Creation of the global Panel //
        
        JPanel helpPanel = new JPanel();
        helpPanel.setBounds(0,0,1000,600);
        // helpPanel.setBackground(Color.yellow); ---> for testing purposes only
        helpPanel.setLayout(null);
        helpPanel.add(helpText);
        helpPanel.add(controlKeys);
        helpPanel.add(generalRules);
        helpPanel.add(tetriminos);
        helpPanel.add(panelImage);
        
			// Adding this Panel to the general Frame //
		
        this.add(helpPanel);
        
        // Audio SoundTrack 
        
    /*    try {
         
         File helpSoundFile = new File("help-menu-music.wav");		// Open an audio input stream.
         AudioInputStream audiohelpSound = AudioSystem.getAudioInputStream(helpSoundFile);
         helpSoundtrack = AudioSystem.getClip();		// Get a sound clip resource.
         helpSoundtrack.open(audiohelpSound);		// Open audio clip and load samples from the audio input stream.
         helpSoundtrack.start();
         
		}catch(Exception e){ e.printStackTrace(); }
    */
    }
    
    public void actionPerformed (ActionEvent e){
		
		if (e.getSource()== exitButton){
			
			this.setVisible(false);
			
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


