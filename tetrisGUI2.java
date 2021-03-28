/*
 * tetrisGUI2.java
 * 
 * Copyright 2021 Bich-lien Phan <bich-lienphan@Bich-liens-MacBook.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */



import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.event.*;

public class tetrisGUI2 extends JFrame implements ActionListener, ChangeListener{
	//declare widgets out of constructor
	private JButton startButton;
	private JSlider sliderDifficulty;
	private JTextArea scoreAff1;
	private JTextArea scoreAff2;
	private JTextArea bestScoreAff;
	private JButton soundButton;
	private JButton helpButton;
	private helpPopUp help;
	
	//constructor
	public tetrisGUI2 (){
		
		//Creation of principle window
		
		this.setTitle("Tetr'INSA");
		this.setSize(929,670);
		this.setLocation(130,20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create main panel
		JPanel mainPane = new JPanel();
		mainPane.setBounds(0,0,929,650);
		mainPane.setLayout(null);
		mainPane.setBackground(Color.white);
		this.add(mainPane);
		
		//Create start button
		startButton = new JButton ("Start!");
		startButton.setBounds(370,230,200,55);	
		startButton.setBackground(Color.red);
		startButton.setForeground(Color.black);
		startButton.addActionListener(this);
		mainPane.add(startButton);
		
		sliderDifficulty = new JSlider(JSlider.HORIZONTAL,1,3,1);
        sliderDifficulty.setMajorTickSpacing(1);
		//sliderDifficulty.setMinorTickSpacing(1);
		sliderDifficulty.setValueIsAdjusting(true);
		sliderDifficulty.setSnapToTicks(true);
		sliderDifficulty.setPaintTicks(true);
		sliderDifficulty.setPaintLabels(true);
		sliderDifficulty.setBounds(353,327,232,60);
		
		mainPane.add(sliderDifficulty);
        sliderDifficulty.addChangeListener(this);
		
		
		//"Difficulty"
		JLabel difficulty = new JLabel();
		difficulty.setFont(new Font("Ariel", Font.PLAIN, 16));
		difficulty.setText("Difficulty:");
		difficulty.setBounds(440,289,340,50);
		mainPane.add(difficulty);
		
		/**
         * SCORES
         * */
		//"Scores"
		JLabel scoreTitle = new JLabel();
		scoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		scoreTitle.setText("Scores:");
		scoreTitle.setBounds(440,370,340,50);
		mainPane.add(scoreTitle);
		
		//"Player 1"
		JLabel score1 = new JLabel();
		score1.setFont(new Font("Ariel", Font.PLAIN, 16));
		score1.setText("Player 1:");
		score1.setBounds(380,400,340,50);
		mainPane.add(score1);
		
		//score textArea for player 1 
		scoreAff1 = new JTextArea ();
		scoreAff1.setBounds(390,455,330,50);	
		scoreAff1.setOpaque(false);
		mainPane.add(scoreAff1);
		
		//"Player 2"
		JLabel score2 = new JLabel();
		score2.setFont(new Font("Ariel", Font.PLAIN, 16));
		score2.setText("Player 2:");
		score2.setBounds(495,400,340,50);
		mainPane.add(score2);
		
		//score textArea for player 2
		scoreAff2 = new JTextArea ();
		scoreAff2.setBounds(495,455,330,50);	
		scoreAff2.setOpaque(false);
		mainPane.add(scoreAff2);
		
		/**
         * BEST SCORE
         * */
		//"Best Score"
		JLabel bestScoreTitle = new JLabel();
		bestScoreTitle.setFont(new Font("Ariel", Font.PLAIN, 16));
		bestScoreTitle.setText("Best Score:");
		bestScoreTitle.setBounds(440,500,340,50);
		mainPane.add(bestScoreTitle);
		
		//best score textArea
		bestScoreAff = new JTextArea ();
		bestScoreAff.setBounds(450,550,330,50);	
		bestScoreAff.setOpaque(false);
		mainPane.add(bestScoreAff);
		
		
		/**
         * ADD BACKGROUND
         * */
		ImageIcon icon = new ImageIcon("background2.jpg");
		JLabel label = new JLabel(icon);
		label.setBounds(0,0,929,650);
		mainPane.add(label);
		
		
		this.setVisible(true);
    }
    /********************************************************************************************************
     * ACTIONLISTENER
     * */

    public void actionPerformed (ActionEvent e){
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
            //mainGame.dropTetrimino(1);
        }else if (e.getKeyCode()==KeyEvent.VK_UP){
            //mainGame.rotateTetrimino();
        }else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            //mainGame.moveTetrimino(1);
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            //mainGame.moveTetrimino(-1);
        }
    }
}

		

		
