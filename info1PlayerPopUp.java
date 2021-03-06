/**
 * info1PlayerPopUp
 * 
 * this window opens when we choose ethe 1 plyer mode
 * it allows to set the different parameters of the game : profile, 
 * level, size of the grid, bakcground...
 * before strating the game
 * */


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
    
    public static int difficulty = 1;
    
    public static String backGroundname = "Night Lake"; // Default Value
    
    public static String pseudoPlayer;
    
    public static String sizeGrid = "20x10";            // Default Value
    
    public tetrisGUI OnePlayer;
    
    private ArrayList<shape> list ;
    

/** CONSTRUCTOR **/
	
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
		nameFrame.setIcon(new ImageIcon("Images (.png and .jpg)/oneplayer.png"));
		nameFrame.setHorizontalAlignment(JLabel.CENTER);
		nameFrame.setBounds((panelImage.getWidth()-300)/2,20,300,30);
		panelImage.add(nameFrame); 
		
		JPanel displayPanel = new RoundedJPanel(30,new Color(224,224,224,50),Color.WHITE, true, false);
		displayPanel.setBounds(40,60,520,520);
		displayPanel.setLayout(null);
		displayPanel.setOpaque(false);
		//displayPanel.setBackground(new Color(224, 224, 224, 50));
		panelImage.add(displayPanel);
		
		JLabel choosePseudo = new JLabel ();
		choosePseudo.setFont(new Font("Ariel", Font.PLAIN, 16));
		choosePseudo.setText("Choose your pseudo or enter a new one :");
		choosePseudo.setIcon(new ImageIcon("Images (.png and .jpg)/info.png"));
        choosePseudo.setForeground(Color.white);
		choosePseudo.setBounds(70,65,400,50);
		panelImage.add(choosePseudo);
		
		JLabel chooseSize = new JLabel ();
		chooseSize.setFont(new Font("Ariel", Font.PLAIN, 16));
		chooseSize.setText("Choose the size of your grid :");
		chooseSize.setIcon(new ImageIcon("Images (.png and .jpg)/size.png"));
        chooseSize.setForeground(Color.white);
		chooseSize.setBounds(70,155,400,50);
		panelImage.add(chooseSize);
		
		JLabel chooseBackground = new JLabel ();
		chooseBackground.setFont(new Font("Ariel", Font.PLAIN, 16));
		chooseBackground.setText("Choose your favorite background :");
		chooseBackground.setIcon(new ImageIcon("Images (.png and .jpg)/background.png"));
        chooseBackground.setForeground(Color.white);
		chooseBackground.setBounds(70,285,400,50);
		panelImage.add(chooseBackground);
		
		JLabel chooseDifficulty = new JLabel ();
		chooseDifficulty.setFont(new Font("Ariel", Font.PLAIN, 16));
		chooseDifficulty.setText("Choose your difficulty level :");
		chooseDifficulty.setIcon(new ImageIcon("Images (.png and .jpg)/difficulty.png"));
        chooseDifficulty.setForeground(Color.white);
		chooseDifficulty.setBounds(70,380,400,50);
		panelImage.add(chooseDifficulty);
        
		soundButton = new JButton (new ImageIcon("Images (.png and .jpg)/sound.png"));
		soundButton.setBounds(20,20,20,20);	
		soundButton.setOpaque(false);
		//soundButton.setBackground(Color.red);
		//soundButton.setForeground(Color.black);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
		
		helpButton = new JButton (new ImageIcon("Images (.png and .jpg)/help.png"));
		helpButton.setBounds(60,20,20,20);	
		//helpButton.setText("?");
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
		playButton.setIcon(new ImageIcon("Images (.png and .jpg)/play.png"));
		panelImage.add(playButton);
		playButton.addActionListener(this);
        
        exitButton = new JButton(new ImageIcon("Images (.png and .jpg)/back.png"));
		//exitButton.setText("<- BACK");
		exitButton.setBounds(540,10,40,40);
		exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(Color.WHITE);
		panelImage.add(exitButton);
		exitButton.addActionListener(this);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// JSpinner to change the background //

        SpinnerListModel backGroundModel = new SpinnerListModel(new String[] {"Sunset","Oasis","Dinosaurs","Night Lake"});
		
		//String[] backGroundNames = {"Sunset","Oasis","Dinosaurs","Night Lake"} ;
		
		//SpinnerListModel backGroundModel = new SpinnerListModel(backGroundNames);
		
		backGroundSpinner = new JSpinner(backGroundModel);
		
		JFormattedTextField backGroundSpinnerField = ((JSpinner.DefaultEditor)backGroundSpinner.getEditor()).getTextField();
		backGroundSpinnerField.setEditable(false);
		
		backGroundSpinner.setBounds((displayPanel.getWidth()-200)/2,330,200,50);
		
		backGroundSpinner.addChangeListener(this);
		panelImage.add(backGroundSpinner);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// JSlider to change difficulty //	-> Reprendre Tool Tip Text !! 
		
		Hashtable<Integer, JLabel> difficultyLabels = new Hashtable<>();
        JLabel labelLevel1 = new JLabel(new ImageIcon("Images (.png and .jpg)/easypeasy.png"));
        labelLevel1.setToolTipText("Easy Peasy");
        
        JLabel labelLevel2 = new JLabel(new ImageIcon("Images (.png and .jpg)/spicy.png"));
        labelLevel2.setToolTipText("Spicy");
        
        JLabel labelLevel3 = new JLabel(new ImageIcon("Images (.png and .jpg)/don'tevendare.png"));
        labelLevel1.setToolTipText("Don't even dare !");
        
        labelLevel1.setForeground(Color.WHITE);
        labelLevel2.setForeground(Color.WHITE);
        labelLevel3.setForeground(Color.WHITE);
        difficultyLabels.put(1, labelLevel1);
        difficultyLabels.put(2, labelLevel2);
        difficultyLabels.put(3, labelLevel3);
		
		JPanel sliderPanel = new JPanel();
        sliderPanel.setBounds(160,430,280,80);
        sliderPanel.setLayout(null);
        //sliderPanel.setOpaque(false);
        sliderPanel.setBackground(new Color(255,255,255,10));
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
		sliderDifficulty.setBounds(170,440,260,60);
		sliderDifficulty.setLabelTable(difficultyLabels);
		
		panelImage.add(sliderDifficulty);
        sliderDifficulty.addChangeListener(this);
        
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// JCombo Box for the pseudo choice //
		
		playerPseudo = new JComboBox<String>();
		
		playerPseudo.addItem("SELECT PSEUDO");
		playerPseudo.addItem("New Player");
		playerPseudo.addItem("Jean Castex");
		playerPseudo.addItem("Kylie Jenner");
		playerPseudo.addItem("I don't remember my name");
		
		/**>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
		/**>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ADD HERE THE PLAYERS FORM THE DATABASE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
		/**>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
		
		playerPseudo.setForeground(Color.black);
		playerPseudo.setBackground(Color.LIGHT_GRAY);
		playerPseudo.setFont(new Font("Arial", Font.PLAIN, 14));
		playerPseudo.setMaximumRowCount(5);
		playerPseudo.setBounds(80,110,200,50);
		playerPseudo.setEditable(false);
		playerPseudo.addActionListener(this);
		
		panelImage.add(playerPseudo, BorderLayout.CENTER);
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
		// JTextField for new players //
    
        newName = new JTextField();
        
        newName.setText("Enter your name");
        
        newName.setForeground(Color.BLACK);
		newName.setBackground(Color.LIGHT_GRAY);
		newName.setFont(new Font("Arial", Font.PLAIN, 14));
		newName.setBounds(320,110,200,50);
		newName.setEditable(true);
		newName.addActionListener(this);
		
		panelImage.add(newName, BorderLayout.CENTER);
    
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	
		// JRadioButton for the size of the grid //
        
		gridSize1 = new JRadioButton ();
		gridSize2 = new JRadioButton ();
		gridSize3 = new JRadioButton ();
        
        gridSize1.setText("Grid 1 : 20 x 10");
        gridSize2.setText("Grid 2 : 24 x 12");
        gridSize3.setText("Grid 3 : 28 x 14");
		
		gridSize1.setBounds(100,200,150,30);
		gridSize2.setBounds(100,230,150,30);
		gridSize3.setBounds(100,260,150,30);
        
        gridSize1.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        gridSize2.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        gridSize3.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
        
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
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("Animated Images (.gif)/settings.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(0,0,600,600); 
		panelImage.add(label);	
		
		/*
		Background = new JLabel(new ImageIcon("tetrisBackgroundOption8.jpg"));
		Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
		panelImage.add(Background);
		*/
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
    
	/**
     * STATE CHANGED
     * */

    public void stateChanged(ChangeEvent e) {

        int fps = 0;

        if (e.getSource() == sliderDifficulty) {
			fps = (int)sliderDifficulty.getValue();
        }
        if (fps == 2){
            data.selectSpeed(600);
            this.difficulty = 2;
        }else if (fps == 3){
            data.selectSpeed(400);
            this.difficulty = 3;
        }else{
			data.selectSpeed(800);
            this.difficulty = 1;
        }

        if (e.getSource() == backGroundSpinner) {
			this.backGroundname = (String) backGroundSpinner.getValue();
        }
    }
    
    /*
	public void stateChanged(ChangeEvent e) {
       int fps = 0;

        if (e.getSource() == sliderDifficulty) {
			fps = (int)sliderDifficulty.getValue();
        }
        if (fps == 2){
            data.selectSpeed(600);
            this.difficulty = 2;
        }else if (fps == 3){
            data.selectSpeed(400);
            this.difficulty = 3;
        }else{
			data.selectSpeed(800);
            this.difficulty = 1;
        }

        if (e.getSource() == backGroundSpinner) {
			this.backGroundname = (String) backGroundSpinner.getValue();
        }
	
	}
    */

    
	
	/**
	 * ACTION LISTENER
	 * */
    
    public void actionPerformed (ActionEvent e) {
        
        
        
        //PSEUDO JCOMBOBOX
        
        if (e.getSource() == playerPseudo ){
			
			if (playerPseudo.getSelectedItem() == "New Player") {
				newName.setEditable(true);
				this.pseudoPlayer = newName.getText();
				playerPseudo.addItem(this.pseudoPlayer);
			}
			
			else {
				newName.setEditable(false);
				this.pseudoPlayer = (String) playerPseudo.getSelectedItem();
			}
			
		}
			
		//SOUND BUTTON
        
        if (e.getSource() == soundButton) {
		
			if (data.soundOn == true) {
				WelcomeGUI.tetrisSoundtrack.stop();
				data.soundOn = false;
			
			}else if (data.soundOn == false) {
				WelcomeGUI.tetrisSoundtrack.start();
				data.soundOn = true;
				
			}
			
		}
		
		//PLAY BUTTON
     
        if (e.getSource() == playButton){
			
			if (playerPseudo.getSelectedItem() == "New Player") {
				this.pseudoPlayer = newName.getText();
			}
			
			else {
				newName.setEditable(false);
				this.pseudoPlayer = (String) playerPseudo.getSelectedItem();
			}
			
            data.restart=true;
            OnePlayer = new tetrisGUI (data);
            this.setVisible(false);
            OnePlayer.setVisible(true);
            //OnePlayer.T.start();
            timePause();
            if (data.soundOn==true){
				WelcomeGUI.tetrisSoundtrack.stop();
			}
            //timePause();
            //OnePlayer.closedWindow = true;
            this.dispose();
        }
        
        //HELP BUTTON
        
        if (e.getSource() == helpButton){	
            helpPopUp HelpWindow = new helpPopUp (data);
			HelpWindow.setVisible(true);
			//helpSound.start();
			//tetrisSoundtrack.stop();
			helpPopUp.helpSoundtrack.start();
			helpPopUp.closedWindow = true;
        }
        
        //EXIT BUTTON
        
        if (e.getSource() == exitButton){
            this.dispose();
        }
        
        //GRID SIZE JRadioButton
        
        if (e.getSource() == gridSize1){
            data.selectAreaSize(20,10);
            sizeGrid = "20x10";
        }
        
        if (e.getSource() == gridSize2){
            data.selectAreaSize(24,12);
            sizeGrid = "24x12";
        }
        
        if (e.getSource() == gridSize3){
            data.selectAreaSize(28,14);
            sizeGrid = "28x14";
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
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //


}
