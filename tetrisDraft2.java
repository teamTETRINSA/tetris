import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;
import java.awt.geom.RoundRectangle2D;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class tetrisDraft2 extends JFrame implements ActionListener {
		 
	//grid-type attribute
    
	private grid data;
		
	//Widgets declarations
		
	private JPanel panelImage;
		
	private JPanel mainPanel;
		
	private JPanel gamePanel;
	
	private JPanel infoPanel;
	
	private JPanel nextPanel;
	
	private JPanel animPanel;
		
	private JTextArea scoreAffPlayer;

	private JTextArea bestScoreAff;
		
	private JButton playPauseButton;
		
	private JButton soundButton;
		
	private JButton helpButton;
	
	private JButton exitButton;
	
	private helpPopUp help;
	
	private Timer mt;
	
	private String difficultyString;
	
	private String backGround;
	
	// For the music
	
	public static boolean sound = true;

    protected static Clip tetrisSoundtrack;

    private Clip helpSound;

	public tetrisDraft2 (grid g) {
		
		data = g;
		
		// Definition of the Frame //
        
        this.setTitle("Tetr'INSA");
        this.setSize(1200,800);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, 1200, 800, 50, 50));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
		// Panel containing the image // 
		
        panelImage = new JPanel();
        panelImage.setBounds(0,0,this.getWidth(),this.getHeight());
        panelImage.setLayout(null);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
		// Settings Buttons // 
   		
		soundButton = new JButton (new ImageIcon("Soundicon.png"));
		soundButton.setBounds(30,30,20,20);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
		
        helpButton = new JButton (new ImageIcon("help.png"));
		helpButton.setBounds(60,30,20,20);	
		helpButton.setBackground(Color.WHITE);
		panelImage.add(helpButton);
		helpButton.addActionListener(this);
		
		exitButton = new JButton(new ImageIcon("back.png"));
		exitButton.setBounds(1130,20,40,40);
		exitButton.setBackground(Color.WHITE);
		panelImage.add(exitButton);
		exitButton.addActionListener(this);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Panels in Foreground //	
		
		// Create info panel with the scores and the "future" tetris
		
		infoPanel = new RoundedJPanel(30,new Color(255,255,255,100),Color.WHITE, true, false);
		infoPanel.setBounds(570,100,600,330);
		infoPanel.setLayout(null);
		infoPanel.setOpaque(false);
		
		// Create gif area showing gif according to actions in the game
		
		animPanel = new RoundedJPanel(30,new Color(255,255,255,100),Color.WHITE, true, false);
		animPanel.setBounds(880,460,290,290);
		animPanel.setLayout(null);
		animPanel.setOpaque(false);
		
	
		// Create panel for next tetrimino in the info Panel
		
		nextPanel = new RoundedJPanel(30,new Color(255,255,255,100),Color.WHITE, true, false);
		nextPanel.setBounds(570,460,290,290);
		nextPanel.setLayout(null);
		nextPanel.setOpaque(false);
		
		// Game Area Panel
		
		gamePanel = new RoundedJPanel(30,new Color(255,255,255,100),Color.WHITE, true, false);
		gamePanel.setBounds(30,100,500,650);
		gamePanel.setLayout(null);
		gamePanel.setOpaque(false);

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Gif Area Display //	
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("animation1.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(10,10,270,270); 
		//this.getContentPane().add(label);	
		animPanel.add(label);	
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// InfoPanel Widgets //	

		JLabel infoTitle = new JLabel("- INFO AREA -");
		infoTitle.setFont(new Font("Times Roman", Font.BOLD, 22));
        infoTitle.setForeground(Color.WHITE);
		infoTitle.setBounds((infoPanel.getWidth()-180)/2,15,180,30);
		infoPanel.add(infoTitle);
		
		playPauseButton = new JButton (new ImageIcon("playPauseIcon.png"));
		playPauseButton.setBounds(250,280,100,40);	
		playPauseButton.setBackground(Color.WHITE);
		playPauseButton.addActionListener(this);
		infoPanel.add(playPauseButton);
		

		JLabel scoreTitle = new JLabel(new ImageIcon("score.png"));
		scoreTitle.setFont(new Font("Times Roman", Font.PLAIN, 16));
		scoreTitle.setText(" Your Score :");
        scoreTitle.setForeground(Color.white);
		scoreTitle.setBounds(40,60,200,30);
		infoPanel.add(scoreTitle);

		scoreAffPlayer = new JTextArea (String.valueOf(data.score));
		scoreAffPlayer.setBounds(60,110,180,30);	
		scoreAffPlayer.setFont(new Font("Times Roman", Font.PLAIN, 16));
		scoreAffPlayer.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		scoreAffPlayer.setBackground(new Color(0,0,0,0));
        infoPanel.add(scoreAffPlayer);
        
        JPanel scorePanel = new RoundedJPanel(30,new Color(0,0,0,50),Color.WHITE, true, false);
		scorePanel.setBounds(50,100,200,50);
		scorePanel.setLayout(null);
		infoPanel.add(scorePanel);
		
		JLabel bestScoreTitle = new JLabel(new ImageIcon("bestscore.png"));
		bestScoreTitle.setFont(new Font("Times Roman", Font.PLAIN, 16));
		bestScoreTitle.setText(" Highest Score : ");
		bestScoreTitle.setForeground(Color.white);
		bestScoreTitle.setBounds(340,60,200,30);
		infoPanel.add(bestScoreTitle);

		bestScoreAff = new JTextArea (String.valueOf(data.bestScore));
		bestScoreAff.setBounds(360,110,180,30);	
		bestScoreAff.setFont(new Font("Times Roman", Font.PLAIN, 16));
		bestScoreAff.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		bestScoreAff.setBackground(new Color(0,0,0,0));
        infoPanel.add(bestScoreAff);
        
        JPanel bestscorePanel = new RoundedJPanel(30,new Color(0,0,0,50),Color.WHITE, true, false);
		bestscorePanel.setBounds(350,100,200,50);
		bestscorePanel.setLayout(null);
		infoPanel.add(bestscorePanel);
		
		JLabel pseudo = new JLabel(new ImageIcon("pseudo.png"));
		pseudo.setFont(new Font("Times Roman", Font.PLAIN, 16));
		pseudo.setText(" Pseudo : "+info1PlayerPopUp.pseudoPlayer);
        pseudo.setForeground(Color.white);
		pseudo.setBounds(40,160,200,30);
		infoPanel.add(pseudo);
		
		if (info1PlayerPopUp.difficulty == 1) {
			difficultyString = "Easy Peasy !";
		} else if (info1PlayerPopUp.difficulty == 2) {
			difficultyString = "Spicy dude !";
		} else if (info1PlayerPopUp.difficulty == 2) {
			difficultyString = "Don't even dare !";
		}
		
		JLabel Difficulty = new JLabel(new ImageIcon("difficulty2.png"));
		Difficulty.setFont(new Font("Times Roman", Font.PLAIN, 16));
		Difficulty.setText(" Current Difficulty : "+difficultyString);
        Difficulty.setForeground(Color.white);
		Difficulty.setBounds(40,200,300,30);
		infoPanel.add(Difficulty);
		
		JLabel gridSize = new JLabel(new ImageIcon("size2.png"));
		gridSize.setFont(new Font("Times Roman", Font.PLAIN, 16));
		gridSize.setText(" Grid Size : "+info1PlayerPopUp.sizeGrid);
        gridSize.setForeground(Color.white);
		gridSize.setBounds(40,240,200,30);
		infoPanel.add(gridSize);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Insertion of images inside the Panels //
        
        JLabel logotetrimino = new JLabel(new ImageIcon("tetrimino.png"));
		logotetrimino.setBounds(270,10,148,80);
		panelImage.add(logotetrimino);
		
		JLabel logoINSA = new JLabel(new ImageIcon("insa_logo2.png"));
		logoINSA.setBounds(700,25,355,40);
		panelImage.add(logoINSA);
		
		if (info1PlayerPopUp.backGroundname == "Sunset") {
			this.backGround = "background1.jpg";
		} else if (info1PlayerPopUp.backGroundname == "Oasis") {
			this.backGround = "background2.jpg";
		} else if (info1PlayerPopUp.backGroundname == "Dinosaurs") {
			this.backGround = "background3.jpg";
		} else if (info1PlayerPopUp.backGroundname == "Night Lake") {
			this.backGround = "background4.jpg";
		}
		
		JLabel BackgroundHelp = new JLabel(new ImageIcon(backGround));
		BackgroundHelp.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(BackgroundHelp);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Creation of the global Panel //
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,1200,800);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.white);
        
        mainPanel.add(gamePanel);
        mainPanel.add(infoPanel);
        mainPanel.add(animPanel);
        mainPanel.add(nextPanel);
        mainPanel.add(panelImage);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Adding this Panel to the general Frame //
		
        this.add(mainPanel);
        this.setVisible(true);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
			// Audio SoundTrack //
        
       try {
         
         File tetrisSoundFile = new File("Tetris-Song.wav");		// Open an audio input stream.
         AudioInputStream audiotetrisSound = AudioSystem.getAudioInputStream(tetrisSoundFile);
         tetrisSoundtrack = AudioSystem.getClip();		// Get a sound clip resource.
         tetrisSoundtrack.open(audiotetrisSound);		// Open audio clip and load samples from the audio input stream.
		
		if (helpPopUp.closedWindow == true) {
			tetrisSoundtrack.start();
		}
         
         
         File helpSoundFile = new File("pacman_death.wav");
         AudioInputStream audiohelpSound = AudioSystem.getAudioInputStream(helpSoundFile);
         helpSound = AudioSystem.getClip();
         helpSound.open(audiohelpSound);
         
		}catch(Exception e){ e.printStackTrace(); } 
		
	/**
         * TIMER
         * the grid will be repaint each 100ms
         * the timer start 1s after we push the "Play" button of the info1pLayerPopUp window
         * */
        
		mt = new Timer(700, this);
		mt.start();
        
        //T = new Timer(500, this);
		//T.start();
		
	}
    
    
    
   
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
		
		// Window Title Animation (scintillement) //
		
	public void paint(Graphics g) {
		 
		super.paint(g);
        
		Graphics2D G = (Graphics2D) g;
		G.setFont(new Font("Times Roman", Font.BOLD, 40));     
        
		G.setColor(Color.white);
		G.drawString("TETRINSA", 500, 60);
		G.setColor(Color.black);
		G.drawString("TETRINSA", 500+2, 60+2);

		try {
			
			Thread.sleep(400);
			repaint();
                
		} 
        
		catch (InterruptedException ex) {}
        
    }
    
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
	
		// ActionListener //

    public void actionPerformed (ActionEvent e){
		// no need of any "if" for the timer t
        //repaint();
        
        if (e.getSource()== exitButton){
			
			this.dispose();
			
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
		
		if (e.getSource() == helpButton){
			
			data.pauseTheGame();
			helpPopUp HelpWindow = new helpPopUp ();
			HelpWindow.setVisible(true);
			helpSound.start();
			tetrisSoundtrack.stop();
			helpPopUp.helpSoundtrack.start();
			helpPopUp.closedWindow = true;

		}
        
        if (e.getSource()== playPauseButton){
            data.pauseTheGame();
        }
	}
	
    
}
