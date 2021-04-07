import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameOverGUI extends JFrame {

	private int score;
	
	private JPanel gameOverPanel;
	
	private JPanel panelBackground;
	
	private Clip gameOverSound;
	
	private JButton replay;

	public GameOverGUI (int scoregame) {
	
		score = scoregame;
		
		// Definition of the Frame //
        
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
		    
        Icon imgIcon = new ImageIcon(this.getClass().getResource("gameover.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(0,0,500,280); 
		//this.getContentPane().add(label);	
		panelBackground.add(label);
		
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
         
        File tetrisSoundFile = new File("gameOver.wav");		
        AudioInputStream audiotetrisSound = AudioSystem.getAudioInputStream(tetrisSoundFile);
        gameOverSound = AudioSystem.getClip();		
        gameOverSound.open(audiotetrisSound);		
         
		}catch(Exception e){ e.printStackTrace(); }
    
     
    }
	
}
