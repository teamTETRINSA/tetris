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

public class tetrisDraft2 extends JFrame implements ActionListener, ChangeListener  {
		 
		//grid-type attribute
    
		private grid data;
		
		//Widgets declarations
		
		private JTextArea scoreAffP1;
		private JTextArea scoreAffP2;
		private JTextArea bestScoreAff;
		private JButton playPauseButton;
		private JButton soundButton;
		private JButton helpButton;
		private helpPopUp help;
	
	    public tetrisDraft2 (){
		
		//Creation of principle window
		
		this.setTitle("Tetr'INSA");
		this.setSize(929,670);
		this.setLocation(130,20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0,0,929,670);
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.white);
		this.add(mainPanel);
		
		//Create display panel with the scores and the "future" tetris
		JPanel displayPanel = new JPanel();
		displayPanel.setBounds(349,20,231,600);
		displayPanel.setLayout(null);
		displayPanel.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(displayPanel);
		
		//Create panel for next tetrimino
		JPanel nextPanel = new JPanel();
		nextPanel.setBounds(20,20,191,175);
		nextPanel.setLayout(null);
		nextPanel.setBackground(Color.red);
		displayPanel.add(nextPanel); 

		/**
         * SCORE
         * */
         //"Score"
		JLabel scoreTitle = new JLabel();
		scoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		scoreTitle.setText("Scores:");
        scoreTitle.setForeground(Color.black);
        scoreTitle.setBackground(Color.red);
		scoreTitle.setBounds(20,205,191,50);
		displayPanel.add(scoreTitle);
		
		//Player 1
		JLabel scoreTitleP1 = new JLabel();
		scoreTitleP1.setFont(new Font("Ariel", Font.PLAIN, 16));
		scoreTitleP1.setText("Player 1:");
        scoreTitleP1.setForeground(Color.black);
        scoreTitleP1.setBackground(Color.red);
		scoreTitleP1.setBounds(20,235,85,50);
		displayPanel.add(scoreTitleP1);
		
		JPanel scorePanelP1 = new JPanel();
		scorePanelP1.setBounds(20,295,85,50);
		scorePanelP1.setLayout(null);
		scorePanelP1.setBackground(Color.red);
		displayPanel.add(scorePanelP1);
		
		scoreAffP1 = new JTextArea ();
		scoreAffP1.setBounds(20,20,85,50);	
		scoreAffP1.setBackground(Color.red);
		scorePanelP1.add(scoreAffP1);
		
		
		//Player 2
		JLabel scoreTitleP2 = new JLabel();
		scoreTitleP2.setFont(new Font("Ariel", Font.PLAIN, 16));
		scoreTitleP2.setText("Player 2:");
        scoreTitleP2.setForeground(Color.black);
        scoreTitleP2.setBackground(Color.red);
		scoreTitleP2.setBounds(125,235,85,50);
		displayPanel.add(scoreTitleP2);
		
		JPanel scorePanelP2 = new JPanel();
		scorePanelP2.setBounds(125,295,85,50);
		scorePanelP2.setLayout(null);
		scorePanelP2.setBackground(Color.red);
		displayPanel.add(scorePanelP2);
		
		scoreAffP2 = new JTextArea ();
		scoreAffP2.setBounds(20,20,85,50);	
		scoreAffP2.setBackground(Color.red);
		scorePanelP2.add(scoreAffP1);
		
		
		/**
         * BEST SCORE
         * */
         //"Score"
		JLabel bestScoreTitle = new JLabel();
		bestScoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		bestScoreTitle.setText("Score to beat:");
		bestScoreTitle.setForeground(Color.black);
        bestScoreTitle.setBackground(Color.red);
		bestScoreTitle.setBounds(20,355,191,50);
		displayPanel.add(bestScoreTitle);
		
		JPanel bestScorePanel = new JPanel();
		bestScorePanel.setBounds(20,415,191,50);
		bestScorePanel.setLayout(null);
		bestScorePanel.setBackground(Color.red);
		displayPanel.add(bestScorePanel);
		
		//best score textArea
		bestScoreAff = new JTextArea ();
		bestScoreAff.setBounds(20,20,191,50);	
		bestScoreAff.setBackground(Color.red);
		bestScorePanel.add(bestScoreAff);
		
		/**
         * PLAY/PAUSE
         * */
		playPauseButton = new JButton ();
		playPauseButton.setBounds(20,505,191,50);	
		playPauseButton.setBackground(Color.red);
		playPauseButton.setForeground(Color.black);
		playPauseButton.addActionListener(this);
		displayPanel.add(playPauseButton);
		
		/**
         * SOUND BUTTON
         * */
		Icon musicIcon = new ImageIcon("Soundicon.png");
		soundButton = new JButton (musicIcon);
		soundButton.setBounds(10,10,15,15);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		soundButton.addActionListener(this);
		mainPanel.add(soundButton);
		
		/**
         * HOW TO PLAY
         * */
		//how to play button
		helpButton = new JButton ("?");
		helpButton.setBounds(35,10,15,15);	
		helpButton.setBackground(Color.white);
		helpButton.setForeground(Color.black);
		mainPanel.add(helpButton);
		helpButton.addActionListener(this);
		
		/**
		 * GAME
		 * */
		JPanel gamePanelP1 = new JPanel();
		gamePanelP1.setBounds(20,20,309,600);
		gamePanelP1.setLayout(null);
		gamePanelP1.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(gamePanelP1);
		
		JPanel gamePanelP2 = new JPanel();
		gamePanelP2.setBounds(600,20,309,600);
		gamePanelP2.setLayout(null);
		gamePanelP2.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(gamePanelP2);
		
		/**
         * TIMER
         * the grid will be repaint each 100ms
         * */
        Timer t = new Timer(100, this);
        t.start(); 
		
		this.setVisible(true);
        
	
	}
    /**
     * PAINT method
     * */
    
    /*public void paint (Graphics g){
        data.dessine(g);
        data.T1.dessine(g);
    }
    */
    
    /********************************************************************************************************
     * ACTIONLISTENER
     * */

    public void actionPerformed (ActionEvent e){
		// no need of any "if" for the timer t
        repaint();
        
		if (e.getSource() == helpButton){
			data.pauseTheGame();
			helpPopUp HelpWindow = new helpPopUp ();
			HelpWindow.setVisible(true);
			System.out.println("you clicked on help");

		}
        
        if (e.getSource()== playPauseButton){
            data.pauseTheGame();
        }
	}
	
	/********************************************************************************************************
     * CHANGELISTENER
     * */
	
	public void stateChanged(ChangeEvent e) {
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

    
}
