
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;
import javax.swing.Timer;
// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class tetrisDraft extends JFrame implements ActionListener, ChangeListener, KeyListener {
    
    //grid-type attribute
    
    private grid data;
    
    private ArrayList<shape> list;	
    
	//declare widgets out of constructor
	private JPanel mainPanel;
	private JPanel displayPanel;
	private JTextArea scoreAff;
	private JTextArea bestScoreAff;
	private JButton soundButton;
    private JButton playPauseButton;
	private JButton helpButton;
	private JButton exitButton;
    private JPanel gamePanel;
    private JPanel nextPanel;
	private helpPopUp help;
	
	//for the music
	public static boolean sound = true;
    protected static Clip tetrisSoundtrack;
    private Clip helpSound;

    //public static Timer T;                 // public > so that we can start the timer form theinfo1PlayerPopUp class
    private Timer mt;
	
    public tetrisDraft (grid g, ArrayList<shape> l){
            
        data = g;
        
        list = l;
		
		//Creation of principle window
		
		this.setTitle("Tetr'INSA");
		this.setSize(800,600);
		this.setLocation(200,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create main panel
		mainPanel = new JPanel();
		mainPanel.setBounds(0,0,800,600);
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.white);
		this.addKeyListener(this);
		this.add(mainPanel);
		
		
		//Create display panel with the scores and the "future" tetris
		displayPanel = new JPanel();
		displayPanel.setBounds(530,20,250,530);
		displayPanel.setLayout(null);
		displayPanel.setBackground(new Color(224, 224, 224, 50));
		this.addKeyListener(this);
		mainPanel.add(displayPanel);
		
		//Create panel for next tetrimino
		JPanel nextPanel = new JPanel();
		nextPanel.setBounds(20,80,130,130);
		nextPanel.setLayout(null);
		nextPanel.setBackground(Color.red);
		this.addKeyListener(this);
		displayPanel.add(nextPanel); 

		/**
         * SCORE
         * */
         //"Score"
		JLabel scoreTitle = new JLabel();
		scoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		scoreTitle.setText("Your Score:");
        scoreTitle.setForeground(Color.black);
        scoreTitle.setBackground(Color.red);
		scoreTitle.setBounds(20,205,210,50);
		displayPanel.add(scoreTitle);
		
		/*JPanel scorePanel = new JPanel();
		scorePanel.setBounds(20,265,210,50);
		scorePanel.setLayout(null);
		scorePanel.setBackground(Color.red);
		displayPanel.add(scorePanel);
		*/
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(20,265,210,50);	
		scoreAff.setBackground(Color.red);
		//scorePanel.add(scoreAff);
        displayPanel.add(scoreAff);
		
		/**
         * BEST SCORE
         * */
         //"Score"
		JLabel bestScoreTitle = new JLabel();
		bestScoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		bestScoreTitle.setText("Score to beat:");
		bestScoreTitle.setForeground(Color.black);
        bestScoreTitle.setBackground(Color.red);
		bestScoreTitle.setBounds(20,325,210,50);
		displayPanel.add(bestScoreTitle);
		
		/*
		JPanel bestScorePanel = new JPanel();
		bestScorePanel.setBounds(20,385,210,50);
		bestScorePanel.setLayout(null);
		bestScorePanel.setBackground(Color.red);
		displayPanel.add(bestScorePanel);
		*/ 
		
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(20,385,210,50);	
		scoreAff.setBackground(Color.red);
		//bestScorePanel.add(scoreAff);
        displayPanel.add(scoreAff);
        
        /**
         * SOUND BUTTON
         * */
		soundButton = new JButton ("􀊣)");
        
		soundButton.setBounds(20,10,50,20);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		soundButton.addActionListener(this);
		mainPanel.add(soundButton);
        
		/**
         * HELP BUTTON
         * */
        
		helpButton = new JButton ("?");
		helpButton.setBounds(75,10,20,20);	
		helpButton.setBackground(Color.white);
		helpButton.setForeground(Color.black);
		mainPanel.add(helpButton);
		helpButton.addActionListener(this);
	    
	    /**
         * EXIT BUTTON
         * */
		exitButton = new JButton ("EXIT");
        
		exitButton.setBounds(20,35,50,20);	
		exitButton.setBackground(Color.red);
		exitButton.setForeground(Color.black);
		exitButton.addActionListener(this);
		mainPanel.add(exitButton);
		
		/**
         * PLAY/PAUSE
         * */
     
		//Icon playPauseIcon = new ImageIcon("playPauseIcon.png");
		playPauseButton = new JButton (/*playPauseIcon*/);
        playPauseButton.setText("􀊈)");
        playPauseButton.setFont(new Font("Ariel", Font.BOLD, 30));
		playPauseButton.setBounds(20,455,210,50);	
		playPauseButton.setBackground(Color.red);
		playPauseButton.setForeground(Color.RED);
		playPauseButton.addActionListener(this);
		displayPanel.add(playPauseButton);
        
        /**
		 * GAME PANEL
		 * */
         
        /*
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(20,40,470,530);
		gamePanel.setLayout(null);
		gamePanel.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(gamePanel);
        * */
        
        /**
         * TIMER
         * the grid will be repaint each 100ms
         * the timer start 1s after we push the "Play" button of the info1pLayerPopUp window
         * */
        
		mt = new Timer(700, this);
		mt.start();
        
        //T = new Timer(500, this);
		//T.start();
		
		
        /**
         * Making all visible
         * */
		

		
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
		
		this.setVisible(true);
		this.addKeyListener(this);


	}
   
    public void paint (Graphics g) {
        
        super.paint(g);
    
        /* game area
        g.setColor(Color.orange);
        g.fillRect(160,40,278,538);
        * */
                
 
        switch (data.area.length){
            case 20:
            
                //background panel
                g.setColor(Color.orange);
                this.addKeyListener(this);
                g.fillRect(160,40,278,538);
                this.addKeyListener(this);
                this.setFocusable(true);
                
                //area
                g.setColor(Color.black);
                for (int i=0; i < 10 ; i++){
                    for (int j=0; j< 20 ; j++){
                        if (data.getCell(j, i) != 0){
                            g.fillRect (170+i*(24+2), 50+j*(24+2), 24, 24);
                        }
                    }
                }
                
                //tetrimino
                g.setColor(Color.black);
                for (int i=0; i < data.T1.tab.length; i++){
                    for (int j=0; j< data.T1.tab[0].length ; j++){
                        if (data.T1.tab[i][j]!=0){
                            g.fillRect (170+(data.T1.X*(24+2))+j*(24+2), 50+(data.T1.Y*(24+2))+i*(24+2), 24, 24);
                        }
                    }
                }
            break;
            
            case 24:
                //background panel
                g.setColor(Color.orange);
                g.fillRect(160,40,282,546);
                this.addKeyListener(this);
                this.setFocusable(true);
                
                //area
                g.setColor(Color.black);
                for (int i=0; i < 12 ; i++){
                    for (int j=0; j< 24 ; j++){
                        if (data.getCell(j, i) != 0){
                            g.fillRect (170+i*(20+2), 50+j*(20+2), 20, 20);
                        }
                    }
                }
                
                //tetrimino
                g.setColor(Color.black);
                for (int i=0; i < data.T1.tab.length ; i++){
                    for (int j=0; j< data.T1.tab[0].length  ; j++){
                        if (data.T1.tab[i][j]!=0){
                            g.fillRect (170+(data.T1.X*(20+2))+j*(20+2), 50+(data.T1.Y*(20+2))+i*(20+2), 20, 20);
                        }
                    }
                }
            break;
            
            case 28:
                //background panel
                g.setColor(Color.orange);
                g.fillRect(160,40,284,549);
                this.addKeyListener(this);
                this.setFocusable(true);

                //area
                g.setColor(Color.black);
                for (int i=0; i < 14 ; i++){
                    for (int j=0; j< 28 ; j++){
                        if (data.getCell(j, i) != 0){
                            g.fillRect (170+i*(17+2), 50+j*(17+2), 17, 17);
                        }
                    }
                }
                
                //tetrimino
                g.setColor(Color.black);
                for (int i=0; i < data.T1.tab.length  ; i++){
                    for (int j=0; j< data.T1.tab[0].length ; j++){
                        if (data.T1.tab[i][j]!=0){
                            g.fillRect (170+(data.T1.X*(17+2))+j*(17+2), 50+(data.T1.Y*(17+2))+i*(20+2), 17, 17);
                        }
                        
                    }
                }
            break;
        }
        
        /*
        //next coming tetrimino panel
        g.setColor(Color.red);
        g.fillRect(550,60,130,175);
        * */
 
        //printing the next coming tetrimino
        for (int i=0; i < data.T2.tab.length; i++){
            for (int j=0; j< data.T2.tab[0].length ; j++){
                if (data.T2.tab[i][j]!=0){
                    g.setColor(data.T2.ColorTetrimino);
                    g.fillRect (565+j*(24+2), 143+i*(24+2), 24, 24);
                }/*else{
                    g.setColor(Color.yellow);
                    g.fillRect (565+j*(24+2), 143+i*(24+2), 24, 24);
                } 
                    //g.fillRect (170+(data.T1.Y*(24+2))+j*(24+2), 70+(data.T1.X*(24+2))+i*(24+2), 24, 24);/*/
            }
        }
	}
    
    
    
    /********************************************************************************************************
     * ACTIONLISTENER
     * */

    public void actionPerformed (ActionEvent e){
        
        // no need of any "if" for the timer t
        if (data.pause==false){
            repaint();
        }
        //System.out.println("repaint");
        
        // CRASH
        // to be modified so that we can dispose the window when
        if (data.restart == false){
            this.tetrisSoundtrack.stop();
            this.dispose();
        }
        
        if (e.getSource() == helpButton){
            data.pauseTheGame();
			helpPopUp HelpWindow = new helpPopUp ();
			HelpWindow.setVisible(true);
			System.out.println("you clicked on help");
			helpSound.start();
			tetrisSoundtrack.stop();
			helpPopUp.helpSoundtrack.start();
			helpPopUp.closedWindow = true;
		}
        
        if (e.getSource() == playPauseButton){
            data.pauseTheGame();
            if (data.pause==true){
                mt.stop();
            }else{
                mt.start();
            }
        }
	    
        if (e.getSource() == exitButton){
            this.tetrisSoundtrack.stop();
            WelcomeGUI.tetrisSoundtrack.start();
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
		
	}
	
	/********************************************************************************************************
     * CHANGELISTENER
     * */
	
	public void stateChanged(ChangeEvent e) {
	}
        
    /********************************************************************************************************
     * MOUSELISTENER
     * */
        
    public void mouseClicked (MouseEvent e){
    }
    
    public void mouseEntered(MouseEvent e){
    }
        
    public void mouseExited(MouseEvent e){
    }
    
    public void mousePressed(MouseEvent e){ 
    }
    
    public void mouseReleased(MouseEvent e){  
    }
    
    /********************************************************************************************************
     * KEYLISTENER
     * */
    
    public void keyPressed(KeyEvent e) {
		System.out.println("coucou");
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
			System.out.println("⬇");
            mainGame.dropTetrimino(data, 1);
        }else if (e.getKeyCode()==KeyEvent.VK_UP){
			System.out.println("↻");
            mainGame.rotateTetrimino(data);
        }else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            System.out.println("➡︎");
            mainGame.moveTetrimino(data,1);
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            System.out.println("⬅︎︎");
            mainGame.moveTetrimino(data,-1);
        }else if (e.getKeyCode()==KeyEvent.VK_SPACE){
            data.pauseTheGame();
        }
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public void messageDialogNBS(){
        JOptionPane.showMessageDialog(this, "New Best Score !"+"/n"+
            data.score,"", JOptionPane.OK_OPTION);
        //JOptionPane.OK_OPTION.addActionListener(this);
        //il faut couper la music alors et fermer la fenêtre
    }
    
    public void messageDialogGO(){
        JOptionPane.showMessageDialog(this, "Game Over !"+"/n"+data.score+"/n"+
            "High Score : "+data.bestScore,"", JOptionPane.OK_OPTION);
        //JOptionPane.OK_OPTION.addActionListener(this);
        //il faut couper la music alors et fermer la fenêtre
    }
    public void setFocusable (){
	}
    
}
