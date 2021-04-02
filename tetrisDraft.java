
import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;

public class tetrisDraft extends JFrame  {
		  //grid-type attribute
    
		private grid data;

		private JTextArea scoreAff;
		private JButton playPauseButton;
	
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
		
		//score textArea
		scoreAff = new JTextArea ();
		scoreAff.setBounds(20,20,210,50);	
		scoreAff.setBackground(Color.red);
		bestScorePanel.add(scoreAff);
		
		/**
         * PLAY/PAUSE
         * */
     
		Icon playPauseIcon = new ImageIcon("playPauseIcon.png");
		playPauseButton = new JButton (playPauseIcon);
		playPauseButton.setBounds(20,455,210,50);	
		playPauseButton.setBackground(Color.red);
		playPauseButton.setForeground(Color.black);
		//playPauseButton.addActionListener(this);
		displayPanel.add(playPauseButton);
		
		this.setVisible(true);
	
	}

}
