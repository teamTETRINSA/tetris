
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class tetrisGUI extends JFrame implements ActionListener{
	
	//declare widgets out of constructor
	private JButton startButton;
	private JButton difficulty1;
	private JButton difficulty2;
	private JButton difficulty3;
	private JTextArea scoreAff;
	private JTextArea bestScoreAff;
	private JButton soundButton;
	private JButton helpButton;
	private helpPopUp help;
	
	//constructor
	public tetrisGUI (){
		
		//Creation of principle window
		
		this.setTitle("Tetr'INSA");
		//Set size of the window
		this.setSize(800,600);
		//intital position
		this.setLocation(300,200);
		//operation on closing
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Create main panel
		JPanel mainPane = new JPanel();
		//Set position and size (x,y,dx,dy)
		mainPane.setBounds(0,0,800,600);
		//absolute positioning
		mainPane.setLayout(null);
		//Set colour
		mainPane.setBackground(Color.white);
		//add this pane to the frame
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
		//Set position and size (x,y,dx,dy)
		startButton.setBounds(523,245,232,60);	
		//set background colour
		startButton.setBackground(Color.red);
		//set text colour
		startButton.setForeground(Color.black);
		//Listen to utilisateur
		startButton.addActionListener(this);
		//add this to the main pane
		mainPane.add(startButton);
		
		
		//DIFFICULTY BUTTONS
		
		//difficulty button1
		difficulty1 = new JButton ("1");
		//Set position and size (x,y,dx,dy)
		difficulty1.setBounds(523,327,64,60);	
		//set background colour
		difficulty1.setBackground(Color.red);
		//set text colour
		difficulty1.setForeground(Color.black);
		//Listen to utilisateur
		difficulty1.addActionListener(this);
		//add this to the main pane
		mainPane.add(difficulty1);
		
		//difficulty button2
		difficulty2 = new JButton ("2");
		//Set position and size (x,y,dx,dy)
		difficulty2.setBounds(607,327,64,60);	
		//set background colour
		difficulty2.setBackground(Color.red);
		//set text colour
		difficulty2.setForeground(Color.black);
		//Listen to utilisateur
		difficulty2.addActionListener(this);
		//add this to the main pane
		mainPane.add(difficulty2);
		
		//difficulty button 3
		difficulty3 = new JButton ("3");
		//Set position and size (x,y,dx,dy)
		difficulty3.setBounds(691,327,64,60);	
		//set background colour
		difficulty3.setBackground(Color.red);
		//set text colour
		difficulty3.setForeground(Color.black);
		//Listen to utilisateur
		difficulty3.addActionListener(this);
		//add this to the main pane
		mainPane.add(difficulty3);
		
		//"Difficulty"
		JLabel difficulty = new JLabel();
		difficulty.setFont(new Font("Ariel", Font.PLAIN, 16));
		//add text
		difficulty.setText("Difficulty:");
		//Set position and size (x,y,dx,dy)
		difficulty.setBounds(595,289,340,50);
		//add this to the main pane
		mainPane.add(difficulty);
		
		
		//SCORE
		//"Score"
		JLabel scoreTitle = new JLabel();
		scoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		//add text
		scoreTitle.setText("Your Score:");
		//Set position and size (x,y,dx,dy)
		scoreTitle.setBounds(595,370,340,50);
		//add this to the main pane
		mainPane.add(scoreTitle);
		
		//score textArea
		scoreAff = new JTextArea ();
		//Set position and size (x,y,dx,dy)
		scoreAff.setBounds(595,425,330,50);	
		scoreAff.setOpaque(false);
		//add this to the main pane
		mainPane.add(scoreAff);
		
		//BEST SCORE
		//"Score"
		JLabel bestScoreTitle = new JLabel();
		bestScoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		//add text
		bestScoreTitle.setText("Best Score:");
		//Set position and size (x,y,dx,dy)
		bestScoreTitle.setBounds(595,450,340,50);
		//add this to the main pane
		mainPane.add(bestScoreTitle);
		
		//best score textArea
		bestScoreAff = new JTextArea ();
		//Set position and size (x,y,dx,dy)
		bestScoreAff.setBounds(595,500,330,50);	
		bestScoreAff.setOpaque(false);
		//add this to the main pane
		mainPane.add(bestScoreAff);
		
		
		//SOUND
		//sound button
		Icon musicIcon = new ImageIcon("Soundicon.png");
		soundButton = new JButton (musicIcon);
		//Set position and size (x,y,dx,dy)
		soundButton.setBounds(720,25,20,20);	
		//set background colour
		soundButton.setBackground(Color.red);
		//set text colour
		soundButton.setForeground(Color.black);
		//Listen to utilisateur
		soundButton.addActionListener(this);
		//add this to the main pane
		mainPane.add(soundButton);
		
		
		//HOW TO PLAY
		//how to play button
		helpButton = new JButton ("?");
		//Set position and size (x,y,dx,dy)
		helpButton.setBounds(745,25,20,20);	
		//set background colour
		helpButton.setBackground(Color.white);
		//set text colour
		helpButton.setForeground(Color.black);
		//add this to the main pane
		mainPane.add(helpButton);
		//Listen to utilisateur
		helpButton.addActionListener(this);
		
		//declare pop up
		help = new helpPopUp();
		
		
		//Add desert background
		ImageIcon icon = new ImageIcon("backgroundfinal.png");
		JLabel label = new JLabel(icon);
		label.setBounds(0,0,800,600);
		mainPane.add(label);
		
		this.setVisible(true);
	
	}
	public void actionPerformed (ActionEvent e){
		if (e.getSource()== helpButton){
			help.setVisible(true);
			System.out.println("you clicked on help");
		}
		
	}
}

