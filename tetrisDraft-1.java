
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;

public class tetrisDraft extends JFrame implements ActionListener, ChangeListener  {
		 
		//grid-type attribute
    
		private grid data;
		
		//Widgets declarations
		
		private JTextArea scoreAff;
		private JTextArea bestScoreAff;
		private JButton playPauseButton;
		private JButton soundButton;
		private JButton helpButton;
		private helpPopUp help;
	
	    public tetrisDraft (){
		
		//Creation of principle window
		
		this.setTitle("Tetr'INSA");
		this.setSize(800,600);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0,0,800,600);
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.white);
		this.add(mainPanel);
		
		//Create display panel with the scores and the "future" tetris
		JPanel displayPanel = new JPanel();
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
		scorePanel.add(scoreAff);
		
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
		
		//best score textArea
		bestScoreAff = new JTextArea ();
		bestScoreAff.setBounds(20,20,210,50);	
		bestScoreAff.setBackground(Color.red);
		bestScorePanel.add(bestScoreAff);
		
		/**
         * PLAY/PAUSE
         * */
		playPauseButton = new JButton ();
		playPauseButton.setBounds(20,455,210,50);	
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
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(20,20,470,530);
		gamePanel.setLayout(null);
		gamePanel.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(gamePanel);
		
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
