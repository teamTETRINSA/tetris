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
import javax.swing.event.*;
import java.util.Hashtable;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// Modifier image background (GIF ???)
// Rajouter différents éléments (Slider -> difficulté, Spinner -> choix background, JCombo -> Liste joueurs à sélectionner pseudo, RadioButton -> size of grid among 3 possibilities, TextArea -> enter player name if new player)
// Finir créer widgets, les placer correctement, les custom graphiquement, ajouter listeners

public class info1PlayerPopUp extends JFrame implements ActionListener, ChangeListener {
    
    private grid data;	

	private JPanel panelImage;
	
	private JButton soundButton;
	
	private JButton helpButton;
	
	private JButton playButton;
    
    private JButton exitButton;
	
	private JLabel nameFrame;
	
	private JLabel Background;
	
	private JPanel onePlayerInfoPanel;
	
	private JSlider sliderDifficulty;
	
    private JSpinner backGroundSpinner;
    
    private JTextArea newplayerPseudo;
    
    private JComboBox <String> playerPseudo;
    
    private JTextField newName;
    
    private ButtonGroup gridSizeButtons;
    
    private JRadioButton gridSize1;
    
    private JRadioButton gridSize2;
    
    private JRadioButton gridSize3;
    
    private int difficulty;
    
    private String backGroundname;
    
    private String pseudoPlayer;
    
    private String sizeGrid;
    
    private tetrisDraft OnePlayer;
	
	public info1PlayerPopUp (grid g) {
        
        data = g;
		
		// Definition of the Frame //
		
		this.setTitle("One Player Mode");
        this.setSize(600,600);
		this.setLocationRelativeTo(null);
        this.setLayout(null);        
        this.setResizable(false);
        this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, 600, 600, 50, 50));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
		// Panel containing the background image // 
		
        panelImage = new JPanel();
        panelImage.setBounds(0,0,600,600);
        panelImage.setLayout(null);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	 
		// Definition of the basic Buttons and Labels // 
        
        Border roundedBorder = new LineBorder(Color.WHITE, 3, true);
        
        nameFrame = new JLabel();
		//nameFrame.setForeground(Color.white);
		nameFrame.setBackground(Color.white);
		nameFrame.setForeground(Color.white);
		nameFrame.setFont(new Font("Times Roman", Font.BOLD, 18));
		nameFrame.setText("- ONE PLAYER MODE -");
		nameFrame.setHorizontalAlignment(JLabel.CENTER);
		nameFrame.setBounds((panelImage.getWidth()-300)/2,20,300,20);
		panelImage.add(nameFrame); 
        
		soundButton = new JButton (new ImageIcon("Soundicon.png"));
		soundButton.setBounds(20,20,20,20);	
		soundButton.setOpaque(false);
		//soundButton.setBackground(Color.red);
		//soundButton.setForeground(Color.black);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
		
		helpButton = new JButton ();
		helpButton.setBounds(60,20,20,20);	
		helpButton.setText("?");
		helpButton.setFont(new java.awt.Font("Times Roman", Font.BOLD, 14));
		helpButton.setForeground(Color.WHITE);
		helpButton.setBackground(Color.BLACK);
		panelImage.add(helpButton);
		helpButton.addActionListener(this);
		
		playButton = new JButton();
		playButton.setText("PLAY");
		playButton.setBounds(250,520,100,50);
		playButton.setFont(new java.awt.Font("Times Roman", Font.BOLD, 16));
		playButton.setForeground(Color.WHITE);
		playButton.setBackground(new Color(255,255,255,100));
		playButton.setBorder(roundedBorder);
		playButton.setOpaque(false);
		panelImage.add(playButton);
		playButton.addActionListener(this);
        
        exitButton = new JButton();
		exitButton.setText("<BACK");
		exitButton.setBounds(480,20,100,20);
		exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(Color.WHITE);
		panelImage.add(exitButton);
		exitButton.addActionListener(this);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// JSpinner to change the background //
		
		String[] backGroundNames = {" Background 1 : Desert "," Background 2 : SCAN "," Background 3 : [...] "," Background 4 : [...] "} ;
		
		SpinnerListModel backGroundModel = new SpinnerListModel(backGroundNames);
		
		backGroundSpinner = new JSpinner(backGroundModel);
		
		backGroundSpinner.setBounds(350,300,200,50);
		
		backGroundSpinner.addChangeListener(this);
		panelImage.add(backGroundSpinner);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// JSlider to change difficulty //	
		
		Hashtable<Integer, JLabel> difficultyLabels = new Hashtable<>();
        JLabel labelLevel1 = new JLabel("Easy peasy !");
        JLabel labelLevel2 = new JLabel("Spicy");
        JLabel labelLevel3 = new JLabel("Don't even dare...");
        //labelLevel1.setForeground(Color.WHITE);
        //labelLevel2.setForeground(Color.WHITE);
        //labelLevel3.setForeground(Color.WHITE);
        difficultyLabels.put(1, labelLevel1);
        difficultyLabels.put(2, labelLevel2);
        difficultyLabels.put(3, labelLevel3);
		
		JPanel sliderPanel = new JPanel();
        sliderPanel.setBounds(140,390,320,80);
        sliderPanel.setLayout(null);
        //sliderPanel.setOpaque(false);
        sliderPanel.setBackground(new Color(255,255,255,20));
        sliderPanel.setBorder(roundedBorder);
        panelImage.add(sliderPanel);
		
		sliderDifficulty = new JSlider(JSlider.HORIZONTAL,1,3,1);
        sliderDifficulty.setMajorTickSpacing(1);
		//sliderDifficulty.setMinorTickSpacing(1);
		//sliderDifficulty.setForeground(Color.WHITE);
		//sliderDifficulty.setBackground(Color.WHITE);
		sliderDifficulty.setValueIsAdjusting(true);
		sliderDifficulty.setSnapToTicks(true);
		sliderDifficulty.setPaintTrack(true);
		sliderDifficulty.setPaintTicks(true);
		sliderDifficulty.setPaintLabels(true);
		sliderDifficulty.setBounds(150,400,300,60);
		sliderDifficulty.setLabelTable(difficultyLabels);
		
		panelImage.add(sliderDifficulty);
        sliderDifficulty.addChangeListener(this);
        
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// JCombo Box for the pseudo choice //
		
		playerPseudo = new JComboBox<String>();
		
		playerPseudo.addItem("Pimprenelle");
		playerPseudo.addItem("Jean Castex");
		playerPseudo.addItem("Kylie Jenner");
		
		playerPseudo.setForeground(Color.RED);
		playerPseudo.setBackground(Color.LIGHT_GRAY);
		playerPseudo.setFont(new Font("Arial", Font.BOLD, 14));
		playerPseudo.setMaximumRowCount(5);
		playerPseudo.setBounds(80,150,200,50);
		playerPseudo.setEditable(true);
		
		panelImage.add(playerPseudo, BorderLayout.CENTER);
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
        newName = new JTextField();
        
        newName.setText("Enter your name");
        
        newName.setForeground(Color.RED);
		newName.setBackground(Color.LIGHT_GRAY);
		newName.setFont(new Font("Arial", Font.BOLD, 14));
		newName.setBounds(80,300,200,50);
		newName.setEditable(true);
		
		panelImage.add(newName, BorderLayout.CENTER);
    
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	
		// JRadioButton for the size of the grid //
        
		gridSize1 = new JRadioButton ();
		gridSize2 = new JRadioButton ();
		gridSize3 = new JRadioButton ();
        
        gridSize1.setText("aaa");
        gridSize2.setText("24x12");
        gridSize3.setText("28x14");
		
		gridSize1.setBounds(350,150,50,30);
		gridSize2.setBounds(350,190,50,30);
		gridSize3.setBounds(350,230,50,30);
        
        gridSize1.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        gridSize2.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        gridSize3.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
        
        gridSize1.setForeground(Color.WHITE);
        gridSize2.setForeground(Color.WHITE);
        gridSize3.setForeground(Color.WHITE);
        
		gridSize1.setBackground(new Color(255,255,255,100));
        gridSize2.setBackground(new Color(255,255,255,100));
        gridSize3.setBackground(new Color(255,255,255,100));
        
        gridSize1.addActionListener(this);
        gridSize2.addActionListener(this);
        gridSize3.addActionListener(this);
        
        panelImage.add(gridSize1);
		panelImage.add(gridSize2);
		panelImage.add(gridSize3);
        
        // grouping the 3 buttons
        
        gridSizeButtons = new ButtonGroup();
		
		gridSizeButtons.add(gridSize1);
		gridSizeButtons.add(gridSize2);
		gridSizeButtons.add(gridSize3);
        
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //		
	 
		// Insertion of the images // 
		
		Background = new JLabel(new ImageIcon("tetrisBackgroundOption3.jpg"));
		Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(Background);
		
		onePlayerInfoPanel = new JPanel();
        onePlayerInfoPanel.setBounds(0,0,600,600);
        onePlayerInfoPanel.setLayout(null);
        onePlayerInfoPanel.setOpaque(false);
        onePlayerInfoPanel.setBackground(Color.white);
        onePlayerInfoPanel.add(panelImage);
        
        this.add(onePlayerInfoPanel);
        
        this.setVisible(true);
		
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	
	public void stateChanged(ChangeEvent e) {
        /*
        JSlider source = (JSlider)e.getSource();
        if (source.getValueIsAdjusting()) {
            int fps = (int)source.getValue();

        } 
        * */
        
        if (e.getSource() == "1"){
            data.selectSpeed(800);
            System.out.println(data.speedLevel);
        } else if (e.getSource() == "2"){
            data.selectSpeed(600);
        }else if (e.getSource() == "3"){
            data.selectSpeed(400);
        }
        
		/*
		if (e.getSource() == backGroundSpinner) {
			
			backGroundNumber = (Integer) backGroundSpinner.getValue();
			// System.out.println(backGroundNumber); - - - > Test to verify 
			
			if (backGroundNumber == 1) {
				
				//backGround = "imageTetrisWelcome.jpg";
				//Background.setIcon(imageIconTetrisWelcome1);
				Background = new JLabel(imageIconTetrisWelcome1);
				Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
				panelImage.add(Background);
			}
			
			if (backGroundNumber == 2) {
				
				//backGround = "imageTetrisWelcome2.jpg";
				//Background.setIcon(imageIconTetrisWelcome2);
				Background = new JLabel(imageIconTetrisWelcome2);
				Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
				Background.repaint();
				Background.revalidate();
				panelImage.add(Background);
				panelImage.repaint();
				
			}
			
			if (backGroundNumber == 3) {
				
				//backGround = "imageTetrisWelcome4.jpg";
				//Background.setIcon(imageIconTetrisWelcome3);
				Background = new JLabel(imageIconTetrisWelcome3);
				Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
				Background.setIcon(imageIconTetrisWelcome3);
				Background.repaint();
			}
			
		}
	*/
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
    
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource() == soundButton) {
		
			if (WelcomeGUI.sound == true) {
				WelcomeGUI.tetrisSoundtrack.stop();
				WelcomeGUI.sound = false;
			
			}else if (WelcomeGUI.sound == false) {
				WelcomeGUI.tetrisSoundtrack.start();
				WelcomeGUI.sound = true;
				
			}
			
		}
     
        if (e.getSource() == playButton){	
            OnePlayer = new tetrisDraft (data);
            OnePlayer.setVisible(true);
            this.setVisible(false);
            WelcomeGUI.tetrisSoundtrack.stop();
            timePause();
            tetrisDraft.T.start();
        }
        
        if (e.getSource() == helpButton){	
            helpPopUp HelpWindow = new helpPopUp ();
			HelpWindow.setVisible(true);
			//helpSound.start();
			//tetrisSoundtrack.stop();
			helpPopUp.helpSoundtrack.start();
			helpPopUp.closedWindow = true;
        }
        
        if (e.getSource() == exitButton){
            this.dispose();
        }
        
        if (e.getSource() == gridSize1){
            data.selectAreaSize(20,10);
        }
        
        if (e.getSource() == gridSize1){
            data.selectAreaSize(24,12);
        }
        
        if (e.getSource() == gridSize1){
            data.selectAreaSize(28,14);
        }
    }
    
    /**
     * TIMEPAUSE
     * used to wait a second before starting printing tetriminos on the game area 
     * */

    public static void timePause () {
		try {
		Thread.sleep(1000);
		}catch(InterruptedException e){}
	}
}
