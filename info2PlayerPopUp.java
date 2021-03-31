import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.event.*;

// Sound support
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class info2PlayerPopUp extends JFrame implements ActionListener, ChangeListener {	



	public info2PlayerPopUp () {



	}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	
	public void stateChanged(ChangeEvent e) {
		/*
		if (e.getSource() == backGroundSpinner) {
			
			backGroundNumber = (Integer) backGroundSpinner.getValue();
			// System.out.println(backGroundNumber); - - - > Test to verify 
			
			if (backGroundNumber == 1) {
				
				//backGround = "imageTetrisWelcome.jpg";
				//Background.setIcon(imageIconTetrisWelcome1);
				Background = new JLabel(imageIconTetrisWelcome1);
				Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
				panelImage.add(Background);
			}
			
			if (backGroundNumber == 2) {
				
				//backGround = "imageTetrisWelcome2.jpg";
				//Background.setIcon(imageIconTetrisWelcome2);
				Background = new JLabel(imageIconTetrisWelcome2);
				Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
				Background.repaint();
				Background.revalidate();
				panelImage.add(Background);
				panelImage.repaint();
				
			}
			
			if (backGroundNumber == 3) {
				
				//backGround = "imageTetrisWelcome4.jpg";
				//Background.setIcon(imageIconTetrisWelcome3);
				Background = new JLabel(imageIconTetrisWelcome3);
				Background.setBounds(0,0,panelImage.getWidth(),panelImage.getHeight());
				Background.setIcon(imageIconTetrisWelcome3);
				Background.repaint();
			}
			
		}
	*/
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //	
    
    public void actionPerformed (ActionEvent e) {
     
	}

}
