/**
 * info2PlayerPopUp
 * 
 * this window opens when we choose ethe 2 plyers mode
 * We made the choice not to develop this game mode but to 
 * work on improved functionnalities on the 1 Player Mode
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class info2PlayerPopUp extends JFrame implements ActionListener {	



    private grid data;

	private JPanel panelImage;
	
	private JLabel loadingLabel;
	
	private JLabel workLabel;
	
	private JPanel twoPlayersPanel;
	
	private JButton soundButton;
	
	private JButton helpButton;
    
    private JButton exitButton;
    

/** CONSTRUCTOR **/

	public info2PlayerPopUp (/*grid g*/) {

        // not to take into account the object grid resolves a bug that doesn't show this pop-up
        
        /*data = g;*/

		// Definition of the Frame // 

		this.setTitle("Two Players Mode");
        this.setSize(600,600);
		this.setLocationRelativeTo(null);
        this.setLayout(null);        
        this.setResizable(false);
        this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, 600, 600, 50, 50));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
        
		// Panel containing the background image // 
		
        panelImage = new JPanel();
        panelImage.setBounds(0,0,600,600);
        panelImage.setLayout(null);
        panelImage.setBackground(Color.white);
        
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

		// Loading Label  //
		
		loadingLabel = new JLabel();
		loadingLabel.setBackground(Color.white);
		loadingLabel.setForeground(Color.black);
		loadingLabel.setFont(new Font("Times Roman", Font.BOLD, 18));
		loadingLabel.setText("Loading . . .");
		loadingLabel.setIcon(new ImageIcon("difficulty.png"));
		loadingLabel.setHorizontalAlignment(JLabel.CENTER);
		loadingLabel.setBounds((panelImage.getWidth()-400)/2,20,400,30);
		panelImage.add(loadingLabel); 
		
		workLabel = new JLabel();
		workLabel.setBackground(Color.white);
		workLabel.setForeground(Color.black);
		workLabel.setFont(new Font("Times Roman", Font.BOLD, 16));
		workLabel.setText("Work in progress - Available Soon");
		workLabel.setIcon(new ImageIcon("work.png"));
		workLabel.setHorizontalAlignment(JLabel.CENTER);
		workLabel.setBounds((panelImage.getWidth()-400)/2,550,400,30);
		panelImage.add(workLabel); 
		
		soundButton = new JButton (new ImageIcon("sound.png"));
		soundButton.setBounds(20,20,20,20);	
		soundButton.setOpaque(false);
		//soundButton.setBackground(Color.red);
		//soundButton.setForeground(Color.black);
		panelImage.add(soundButton);
		soundButton.addActionListener(this);
		
		helpButton = new JButton (new ImageIcon("help.png"));
		helpButton.setBounds(60,20,20,20);	
		//helpButton.setText("?");
		helpButton.setFont(new java.awt.Font("Times Roman", Font.BOLD, 14));
		helpButton.setForeground(Color.WHITE);
		helpButton.setBackground(Color.BLACK);
		panelImage.add(helpButton);
		helpButton.addActionListener(this);
        
        exitButton = new JButton(new ImageIcon("back.png"));
		//exitButton.setText("<- BACK");
		exitButton.setBounds(540,10,40,40);
		exitButton.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
		exitButton.setForeground(Color.BLACK);
		exitButton.setBackground(Color.WHITE);
		panelImage.add(exitButton);
		exitButton.addActionListener(this);

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

		// Animated Image //
		
		Icon imgIcon = new ImageIcon(this.getClass().getResource("loading1.gif"));
		JLabel label = new JLabel(imgIcon);
		label.setBounds(50,50,250,250); 
		//this.getContentPane().add(label);	
		panelImage.add(label);	
		
		Icon imgIcon2 = new ImageIcon(this.getClass().getResource("loading2.gif"));
		JLabel label2 = new JLabel(imgIcon2);
		label2.setBounds(300,0,250,250); 
		panelImage.add(label2);	
		
		Icon imgIcon3 = new ImageIcon(this.getClass().getResource("loading3.gif"));
		JLabel label3 = new JLabel(imgIcon3);
		label3.setBounds(0,120,600,600); 
		panelImage.add(label3);	

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
		
		// General Panel //
		
		twoPlayersPanel = new JPanel();
        twoPlayersPanel.setBounds(0,0,600,600);
        twoPlayersPanel.setLayout(null);
        twoPlayersPanel.setBackground(Color.BLACK);
        twoPlayersPanel.add(panelImage);
		
		this.add(twoPlayersPanel);

	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
	
		// ActionListener //
    
    public void actionPerformed (ActionEvent e) {
        
        if (e.getSource() == soundButton) {
			if (data.soundOn == true) {
				WelcomeGUI.tetrisSoundtrack.stop();
				data.soundOn = false;
			
			}else if (data.soundOn == false) {
				WelcomeGUI.tetrisSoundtrack.start();
				data.soundOn = true;
				
			}
		}
        
        if (e.getSource() == helpButton){	
            helpPopUp HelpWindow = new helpPopUp (data);
			HelpWindow.setVisible(true);
			helpPopUp.helpSoundtrack.start();
            WelcomeGUI.tetrisSoundtrack.stop();
			helpPopUp.helpSoundtrack.start();
			helpPopUp.closedWindow = true;
        }
        
        if (e.getSource() == exitButton){
            this.dispose();
        }
        
    }

}
