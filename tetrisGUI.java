
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;

public class tetrisGUI extends JFrame implements ActionListener, ChangeListener {
    
    //grid-type attribute
    
    private grid data;
	
	//declare widgets out of constructor
	private JButton startButton;
	private JButton difficulty1;
	private JButton difficulty2;
	private JButton difficulty3;
	private JSlider sliderDifficulty;
	private JTextArea scoreAff;
	private JTextArea bestScoreAff;
	private JButton soundButton;
    private JButton playPauseButton;
	private JButton helpButton;
	private helpPopUp help;
	
	//constructor
	public tetrisGUI (grid g){
		
        data = g;
        
		//Creation of principle window
		
		this.setTitle("Tetr'INSA");
		this.setSize(800,600);
		this.setLocation(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Create main panel
		JPanel mainPane = new JPanel();
		mainPane.setBounds(0,0,800,600);
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		this.add(mainPane);
		
		/*
		//Create panel for next tetrimino
		JPanel nextPane = new JPanel();
		//Set position and size (x,y,dx,dy)
		nextPane.setBounds(531,65,200,175);
		//absolute positioning
		nextPane.setLayout(null);
		//set color
		nextPane.setBackground(Color.red);
		mainPane.add(nextPane);
		*/ 
		
		//Create start button
		startButton = new JButton ("Start!");
		startButton.setBounds(523,245,232,60);	
		startButton.setBackground(Color.red);
		startButton.setForeground(Color.black);
		startButton.addActionListener(this);
		mainPane.add(startButton);
		
		
		/**
         * DIFFICULTY BUTTONS
         * */
		
		/*
		//difficulty button1
		difficulty1 = new JButton ("1");
		difficulty1.setBounds(523,327,64,60);	
		difficulty1.setBackground(Color.red);
		difficulty1.setForeground(Color.black);
		difficulty1.addActionListener(this);
		mainPane.add(difficulty1);
		
		//difficulty button2
		difficulty2 = new JButton ("2");
		difficulty2.setBounds(607,327,64,60);	
		difficulty2.setBackground(Color.red);
		difficulty2.setForeground(Color.black);
		difficulty2.addActionListener(this);
		mainPane.add(difficulty2);
		
		//difficulty button 3
		difficulty3 = new JButton ("3");
		difficulty3.setBounds(691,327,64,60);	
		difficulty3.setBackground(Color.red);
		difficulty3.setForeground(Color.black);
		difficulty3.addActionListener(this);
		mainPane.add(difficulty3);
		*/
		
        // A INSERER DANS LA WELCOME GUI
        /*
		//Difficulty
		JLabel difficulty = new JLabel();
		difficulty.setFont(new Font("Ariel", Font.PLAIN, 16));
		difficulty.setText("Difficulty:");
		difficulty.setBounds(595,289,340,50);
		mainPane.add(difficulty);
        * */
		
		/**
         * DIFFICULTY SLIDER
         * */
        // A INSERER DANS LA WELCOME GUI
        /* 
        sliderDifficulty = new JSlider(JSlider.HORIZONTAL,1,3,1);
        sliderDifficulty.setMajorTickSpacing(1);
		//sliderDifficulty.setMinorTickSpacing(1);
		sliderDifficulty.setValueIsAdjusting(true);
		sliderDifficulty.setSnapToTicks(true);
		sliderDifficulty.setPaintTicks(true);
		sliderDifficulty.setPaintLabels(true);
		sliderDifficulty.setBounds(523,327,232,60);
		
		mainPane.add(sliderDifficulty);
        sliderDifficulty.addChangeListener(this);
        * */
		
		/**
         * SCORE
         * */
		//"Score"
		JLabel scoreTitle = new JLabel();
		scoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		scoreTitle.setText("Your Score:");
		scoreTitle.setBounds(595,370,340,50);
		mainPane.add(scoreTitle);
		
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(595,425,330,50);	
		scoreAff.setOpaque(false);
		mainPane.add(scoreAff);
		
		/**
         * BEST SCORE
         * */
		//"Score"
		JLabel bestScoreTitle = new JLabel();
		bestScoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		bestScoreTitle.setText("Best Score:");
		bestScoreTitle.setBounds(595,450,340,50);
		mainPane.add(bestScoreTitle);
		
		//best score textArea
		bestScoreAff = new JTextArea ();
		bestScoreAff.setBounds(595,500,330,50);	
		bestScoreAff.setOpaque(false);
		mainPane.add(bestScoreAff);
		
		
		/**
         * SOUND BUTTON
         * */
		Icon musicIcon = new ImageIcon("Soundicon.png");
		soundButton = new JButton (musicIcon);
		soundButton.setBounds(720,25,20,20);	
		soundButton.setBackground(Color.red);
		soundButton.setForeground(Color.black);
		soundButton.addActionListener(this);
		mainPane.add(soundButton);
		
		/**
         * PLAY/PAUSE
         * */
         
		Icon playPauseIcon = new ImageIcon("playPauseIcon.png");
		playPauseButton = new JButton (playPauseIcon);
		playPauseButton.setBounds(680,25,20,20);	
		playPauseButton.setBackground(Color.red);
		playPauseButton.setForeground(Color.black);
		playPauseButton.addActionListener(this);
		mainPane.add(playPauseButton);
        
		/**
         * HOW TO PLAY
         * */
        
		//how to play button
		helpButton = new JButton ("?");
		helpButton.setBounds(745,25,20,20);	
		helpButton.setBackground(Color.white);
		helpButton.setForeground(Color.black);
		mainPane.add(helpButton);
		helpButton.addActionListener(this);
		
		//declare pop up
		help = new helpPopUp();
		
		
		/**
         * ADD BACKGROUND
         * */
		ImageIcon icon = new ImageIcon("backgroundfinal.png");
		JLabel label = new JLabel(icon);
		label.setBounds(0,0,800,600);
		mainPane.add(label);
        
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
    
    public void paint (Graphics g){
        data.dessine(g);
        data.T1.dessine(g);
    }
    
    /********************************************************************************************************
     * ACTIONLISTENER
     * */

    public void actionPerformed (ActionEvent e){
        
        // no need of any "if" for the timer t
        repaint();
        
		if (e.getSource() == helpButton){
			help.setVisible(true);
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

