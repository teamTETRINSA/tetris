import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

public class WelcomeGUI extends JFrame implements ActionListener {	
    
    private JButton Player1;
    
    private JButton Player2;
    
    private JButton HelpButton;
    
    private JButton soundButton;
    
    private helpPopUp HelpWindow;
    
    private tetrisGUI OnePlayer;
    
    private AudioClip tetrisSoundtrack;
    
    
    public WelcomeGUI (){
		
		/*URL urlClick = WelcomeGUI.class.getResource("tetris-theme-officiel.wav");
		tetrisSoundtrack = Applet.newAudioClip(urlClick);*/
 
        /* 
         * // Definition of the Frame //
         */
        
        this.setTitle("Welcome Screen Tetris:");
        //Set size of the window
        this.setSize(600,400);
        //Intital position
        this.setLocation(300,200);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* 
         * // Panel containing the image // 
         */
         
        JPanel panelImage = new JPanel();
        panelImage.setBounds(0,0,600,400);
        panelImage.setLayout(null);
        
        /* 
         * // Definition of the buttons // 
         */
        Border roundedBorder = new LineBorder(Color.WHITE, 2, true);
        
        Player1 = new JButton();
		Player1.setText("One Player");
		Player1.setBounds(200,110,200,60);
		Player1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		Player1.setForeground(Color.WHITE);
		Player1.setBorder(roundedBorder);
		panelImage.add(Player1);
		Player1.addActionListener(this);
		
		Player2 = new JButton();
		Player2.setText("Two Players");
		Player2.setBounds(200,180,200,60);
		Player2.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		Player2.setForeground(Color.WHITE);
		Player2.setBorder(roundedBorder);
		panelImage.add(Player2);
		Player2.addActionListener(this);
		
		HelpButton = new JButton();
		HelpButton.setText("Help - Tetris Rules");
		HelpButton.setBounds(200,250,200,60);
		HelpButton.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		HelpButton.setForeground(Color.WHITE);
		HelpButton.setBackground(Color.WHITE);
		HelpButton.setBorder(roundedBorder);
		panelImage.add(HelpButton);
		HelpButton.addActionListener(this);
		
		soundButton = new JButton (new ImageIcon("Soundicon.png"));
		soundButton.setBounds(20,20,20,20);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
        
        /* 
         * // Copyrights // 
         */
        
        JLabel Names = new JLabel();
		Names.setFont(new Font("Times Roman", Font.BOLD, 14));
		Names.setText("Project realised by Joseph B, Paul TD, Bich-Lien P, Flora G");
		Names.setBounds(130,315,480,40);
		panelImage.add(Names);
		
		/* 
         * // Insertion of the images // 
         */
		
		/*ImageIcon logoINSA = new ImageIcon("insa_logo.png");
		Image scaledlogoINSA = logoINSA.getScaledImage(60,20,Image.SCALE_SMOOTH);
		JLabel LogoINSA = new JLabel(logoINSA);
		LogoINSA.setBounds(20,320,60,20);
		panelImage.add(LogoINSA);*/
		
		JLabel LogoINSA = new JLabel(new ImageIcon("insa_logo.png"));
		LogoINSA.setBounds(20,320,100,40);
		panelImage.add(LogoINSA);
		
		JLabel LogoGame = new JLabel(new ImageIcon("logo.png"));
		LogoGame.setBounds(140,20,320,80);
		panelImage.add(LogoGame);
		
		JLabel Background = new JLabel(new ImageIcon("imageTetrisFondWelcome.jpg"));
		Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(Background);
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBounds(0,0,600,400);
        welcomePanel.setLayout(null);
        welcomePanel.setBackground(Color.white);
        welcomePanel.add(panelImage);
        
        this.add(welcomePanel);
        
        OnePlayer = new tetrisGUI ();
        OnePlayer.setVisible(false);
        
        HelpWindow = new helpPopUp ();
        HelpWindow.setVisible(false);
        
        this.setVisible(true);
        
        tetrisSoundtrack.play();
        
    }
    
    public void actionPerformed (ActionEvent e){
		
		if (e.getSource()== Player1){
			
			OnePlayer.setVisible(true);
		
		}
		
		if (e.getSource()== Player2){
			
			System.out.println("No 2 players mode for now ...");
		
		}
		
		if (e.getSource()== HelpButton){
			
			HelpWindow.setVisible(true);
			
		}
		
		if (e.getSource() == soundButton) {
            URL urlClick = WelcomeGUI.class.getResource("tetris-theme-officiel.wav");
            tetrisSoundtrack = Applet.newAudioClip(urlClick);
            tetrisSoundtrack.loop();
        }
     
	}
    
    
}
