
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;
// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class tetrisDraft extends JFrame implements ActionListener, ChangeListener {
    
    //grid-type attribute
    
    private grid data;	
    
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

    public static Timer T;                 // public > so that we can start the timer form theinfo1PlayerPopUp class
	
    public tetrisDraft (grid g){
            
        data = g;
		
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
		this.add(mainPanel);
		
		//Create display panel with the scores and the "future" tetris
		displayPanel = new JPanel();
		displayPanel.setBounds(530,20,250,530);
		displayPanel.setLayout(null);
		displayPanel.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(displayPanel);
		
		//Create panel for next tetrimino
		JPanel nextPanel = new JPanel();
		nextPanel.setBounds(20,20,210,175);
		nextPanel.setLayout(null);
		nextPanel.setBackground(Color.red);
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
		
		JPanel scorePanel = new JPanel();
		scorePanel.setBounds(20,265,210,50);
		scorePanel.setLayout(null);
		scorePanel.setBackground(Color.red);
		displayPanel.add(scorePanel);
		
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(20,20,210,50);	
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
		
		JPanel bestScorePanel = new JPanel();
		bestScorePanel.setBounds(20,385,210,50);
		bestScorePanel.setLayout(null);
		bestScorePanel.setBackground(Color.red);
		displayPanel.add(bestScorePanel);
		
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(20,20,210,50);	
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
		helpButton.setBounds(70,10,20,20);	
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
		//playPauseButton.addActionListener(this);
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
        
        T = new Timer(100, this);
		
        /**
         * Making all visible
         * */
		
		this.setVisible(true);
		
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
	}
    
   public void paint (Graphics g) {
        
        super.paint(g);
        
	//next coming tetrimino panel
        g.setColor(Color.red);
        g.fillRect(550,60,210,175);
        
        /*
        g.setColor(Color.blue);
        for (int i=0; i < data.T1.tab.length ; i++){
            for (int j=0; j< data.T1.tab[0].length ; j++){
                g.fillRect (30+data.T1.Y*15+i*15, 70+data.T1.X*15+j*15, 15, 15);
            }
        }
        
        g.setColor(Color.black);
        for (int i=0; i < data.area.length ; i++){
            for (int j=0; j< data.area[0].length ; j++){
                if (data.area[i][j] != 0){
                    g.fillRect (30+i*15, 70+j*15, 15, 15);
                }
            }
        }
        * */
        
        
        switch (data.area.length){
            case 20:
            
                //background panel
                g.setColor(Color.orange);
                g.fillRect(160,40,278,538);
                
                //area
                g.setColor(Color.black);
                for (int i=0; i < 10 ; i++){
                    for (int j=0; j< 20 ; j++){
                        if (data.area[i][j] != 0){
                            g.fillRect (170+i*24+i*2, 50+j*24+j*2, 24, 24);
                        }
                    }
                }
                
                //tetrimino
                g.setColor(Color.blue);
                for (int i=0; i < /*data.T1.tab.length*/ 4 ; i++){
                    for (int j=0; j< /*data.T1.tab[0].length*/ 4 ; j++){
                        g.fillRect (170+(data.T1.Y*(24+2))+i*(24+2), 70+(data.T1.X*(24+2))+j*(24+2), 24, 24);
                    }
                }
            break;
            
            case 24:
                g.setColor(Color.orange);
                g.fillRect(160,40,282,546);
                g.setColor(Color.green);
                for (int i=0; i < 12 ; i++){
                    for (int j=0; j< 24 ; j++){
                        g.fillRect (170+i*20+i*2, 50+j*20+j*2, 20, 20);
                    }
                }
            break;
            
            case 28:
                g.setColor(Color.orange);
                g.fillRect(160,40,284,549);
                g.setColor(Color.blue);
                for (int i=0; i < 14 ; i++){
                    for (int j=0; j< 28 ; j++){
                        g.fillRect (170+i*17+i*2, 50+j*17+j*2, 17, 17);
                    }
                }
            break;
        }
        
        
        /*        
        g.setColor(Color.black);
        for (int i=0; i < 10 ; i++){
            for (int j=0; j< 20 ; j++){
                g.fillRect (170+i*24+i*2, 50+j*24+j*2, 24, 24);
            }
        }
        
        g.setColor(Color.green);
        for (int i=0; i < 12 ; i++){
            for (int j=0; j< 24 ; j++){
                g.fillRect (170+i*20+i*2, 50+j*20+j*2, 20, 20);
            }
        }
        
        g.setColor(Color.blue);
        for (int i=0; i < 14 ; i++){
            for (int j=0; j< 28 ; j++){
                g.fillRect (170+i*17+i*2, 50+j*17+j*2, 17, 17);
            }
        }
        * */
        
        
        
        //g.setColor(Color.blue);
        //data.dessine(g);
        //data.T1.dessine(g);

		// g.setColor(Color.red);
		// g.fillRect(200, 200, 200, 200);
        
        /*
		g.setColor(Color.black);
		g.fillOval(250, (int) y, 100, 100);
        * */   
	
	   /*
        g.setColor(Color.orange);
		g.fillRect(20,60,470,520);
        
        g.setColor(Color.red);
        g.fillRect(550,60,210,175);
        
        g.setColor(Color.blue);
        data.dessine(g);
        data.T1.dessine(g);
	*/
 
	}
    
    
    
    /********************************************************************************************************
     * ACTIONLISTENER
     * */

    public void actionPerformed (ActionEvent e){
        
        // no need of any "if" for the timer t
        //repaint();
        
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
        
        if (e.getSource()== playPauseButton){
            data.pauseTheGame();
        }
        
        if (data.start == false){
            this.dispose();
        }
	    
	if (e.getSource() == exitButton){
            this.dispose();
        }
        
        if (e.getSource() == soundButton) {
		
			if (sound == true) {
				tetrisSoundtrack.stop();
				sound = false;
			
			}else if (sound == false) {
				tetrisSoundtrack.start();
				sound = true;
				
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
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            mainGame.dropTetrimino(data, 1);
        }else if (e.getKeyCode()==KeyEvent.VK_UP){
            mainGame.rotateTetrimino(data);
        }else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            mainGame.moveTetrimino(data,1);
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            mainGame.moveTetrimino(data,-1);
        }else if (e.getKeyCode()==KeyEvent.VK_SPACE){
            data.pauseTheGame();
        }
    }
    
    public void messageDialogNBS(){
        JOptionPane.showMessageDialog(this, "New Best Score !"+"/n"+
            data.score,"", JOptionPane.OK_OPTION);
    }
    
    public void messageDialogGO(){
        JOptionPane.showMessageDialog(this, "Game Over !"+"/n"+data.score+"/n"+
            "High Score : "+data.bestScore,"", JOptionPane.OK_OPTION);
    }
    
}
