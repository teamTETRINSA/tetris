/* This class is a simple GUI Frame called when the player has lost the game.
	It is composed of an animated image background and a button "Play Again" redirecting to the Welcome Window.
	
	2 possibilities for the background :
		- a standard animated image when the game is over
		- if the player surpassed his best score, it displays a special background with an animated text
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.util.logging.*;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameOverGUI extends JFrame {

	// Declaration of the attributes and widgets //
	
	private int score;
	
	private grid data;
	
	private JPanel gameOverPanel;
	
	private JPanel panelBackground;

   	private Clip gameOverSound1;
	
	private JButton replay;
	
	// Coordinates for String animation // 
	
	private int coordX = 0;
    	private int coordY = 60;
    	private int coordZ = 150;
    	private int coordT = 180;
    
   	 private String string1 = "! New Record !";
   	 private String string2 = "Best Score";

	public GameOverGUI (int scoregame, grid g) { // 
	
		score = scoregame;
		data = g;
		
		// Definition of the Frame -> Size, location ... //
        
        this.setTitle("! Game Over !");
        this.setSize(500,280);
	this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);	
        this.setLayout(null);
        this.setShape(new RoundRectangle2D.Double(0, 0, 500, 280, 30, 30)); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
		// Panel containing the image // 
		
        panelBackground = new JPanel();
        panelBackground.setBounds(0,0,500,280);
        panelBackground.setLayout(null);
        
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    
		// JButton //
		
		Border roundedBorder = new LineBorder(Color.WHITE, 2, true);
		
		replay = new JButton();
		replay.setText("Play Again");
		replay.setBounds(170,220,160,40);
		replay.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
		replay.setForeground(Color.WHITE);
		replay.setBackground(new Color(0,0,0,200));
		replay.setBorder(roundedBorder);
		replay.setOpaque(true);
		replay.setIcon(new ImageIcon("replay.png"));
		panelBackground.add(replay);
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// Animated Image //
		
		if (score < data.bestScore) {
		
        	Icon imgIcon = new ImageIcon(this.getClass().getResource("gameover.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(0,0,500,280); 
		//this.getContentPane().add(label);	
		panelBackground.add(label);
		
		} else if (score >= data.bestScore) {
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("gameoverbis.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(0,0,500,280); 
		//this.getContentPane().add(label);	
		panelBackground.add(label);
			
		}
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// General Panel //
		
		gameOverPanel = new JPanel();
        gameOverPanel.setBounds(0,0,500,280);
        gameOverPanel.setLayout(null);
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.add(panelBackground);
		
		this.add(gameOverPanel);
		
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
		
		// Audio SoundTrack //
     
        try {
         
        File gameOver1File = new File("gameOver.wav");		
        AudioInputStream audiogameOver1 = AudioSystem.getAudioInputStream(gameOver1File);
        gameOverSound1 = AudioSystem.getClip();		
        gameOverSound1.open(audiogameOver1);

        if (data.soundOn == true) {
            timePause(2000);
			gameOverSound1.start();
		}
        
         
		}catch(Exception e){ e.printStackTrace(); }
    
     
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
		
		// Time pause //

    public static void timePause (int ms) {
		try {
		Thread.sleep(ms);
		}catch(InterruptedException e){}
	}
    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
		
		// String animation //
	
     public void paint(Graphics g) {
		 
		if (score >= data.bestScore) {
		
			super.paint(g);
        
			Graphics2D G = (Graphics2D) g;
			G.setFont(new Font("Times Roman", Font.BOLD, 35));     
        
			G.setColor(Color.blue);
			G.drawString(string1, coordX, coordY);
			G.setColor(Color.red);
			G.drawString(string1, coordX+2, coordY+2);
			G.setColor(Color.white);
			G.drawString(string1, coordX+4, coordY+4);
			
			G.setColor(Color.white);
			G.drawString(string2, coordZ, coordT);
			G.setColor(Color.black);
			G.drawString(string2, coordZ+2, coordT+2);

			try {
			
				Thread.sleep(200);
				coordX += 40;
            
				if(coordX > getWidth()) {
					coordX = 0;
				}
        
			repaint();
                
			} 
        
			catch (InterruptedException ex) {
				//JOptionPane.showMessageDialog(this, ex);
			}
    
		} else if (score < data.bestScore) {
			
			super.paint(g);
			
		}
        
    }
    
    public void actionPerformed (ActionEvent e){
        
        if (e.getSource() == replay){
            //tetrisGUI.setVisible(false);
            //tetrisGUI.dispose();
            //mainGame.setTetrisGuiFalse();
            this.dispose();
        }
    }
    
    
	
}
