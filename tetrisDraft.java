
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;

public class tetrisDraft extends JFrame implements ActionListener, ChangeListener {
    
    //grid-type attribute
    
    private grid data;	
	//declare widgets out of constructor
	private JTextArea scoreAff;
	private JTextArea bestScoreAff;
	private JButton soundButton;
    private JButton playPauseButton;
	private JButton helpButton;
    private JPanel gamePanel;
    private JPanel displayPanel;
    private JPanel nextPanel;
	private helpPopUp help;
    
    private Timer T;
	
    public tetrisDraft (grid g){
            
        data = g;
		
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
		
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(20,20,210,50);	
		scoreAff.setBackground(Color.red);
		bestScorePanel.add(scoreAff);
        
        /**
         * SOUND BUTTON
         * */
		soundButton = new JButton ("􀊣)");
        
		soundButton.setBounds(20,20,40,20);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		soundButton.addActionListener(this);
		mainPanel.add(soundButton);
        
		/**
         * HELP BUTTON
         * */
        
		helpButton = new JButton ("?");
		helpButton.setBounds(70,20,20,20);	
		helpButton.setBackground(Color.white);
		helpButton.setForeground(Color.black);
		mainPanel.add(helpButton);
		helpButton.addActionListener(this);
		
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
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(20,20,470,530);
		gamePanel.setLayout(null);
		gamePanel.setBackground(new Color(224, 224, 224, 50));
		mainPanel.add(gamePanel);
        
        /**
         * TIMER
         * the grid will be repaint each 100ms
         * */
        
        T = new Timer(100, this);
        T.start(); 
		
        /**
         * Making all visible
         * */
		
		this.setVisible(true);
	}
    
    
    
    /********************************************************************************************************
     * ACTIONLISTENER
     * */

    public void actionPerformed (ActionEvent e){
        
        // no need of any "if" for the timer t
        repaint();
        
        /*
		if (e.getSource() == helpButton){
            data.pauseTheGame();
			help.setVisible(true);
			System.out.println("you clicked on help");
		}
        * */
        
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

}
