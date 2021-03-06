/**
 * main window
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics2D;//used to reduce the size of an image

public class GUI extends JFrame implements MouseListener, ActionListener{
    
    
    //private JButton start;
    //private JLabel backGround;
    private JPanel JP1;
    
    
    public GUI (int a, int b, int c, int d){
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(a,b);
        this.setLocation(c,d);
        this.setTitle("Tetr'INSA");

        //names of parameters
        //this.l=maListeCourbe;
        
        
        
        /**
         * PANELS
         */
        
        JPanel JP1 = new JPanel();
        JP1.setLayout(null);
        //JP1.setBounds(10,10,10,10);
        JP1.setBackground(Color.green);
        JP1.addMouseListener(this);
        this.add(JP1);
        
        /**
         * LABELS
         */
        
        /*
        ImageIcon original = new ImageIcon("BG1.jpeg");
        Image newImage   =  original.getImage();
        int	h = newImage.getHeight(this);
        int	l = newImage.getWidth(this);
        newImage = newImage.getScaledInstance(l,600,861);
        

        
        ImageIcon bg1 = new ImageIcon(newImage);
        
        
        JLabel backGround = new JLabel(bg1);
        backGround.setBounds(0,0,a,b);
        JP1.add(backGround);
        * */
        
        
        ///////////
        
        
        
        ImageIcon bg1 = new ImageIcon("BG1.jpeg");
        bg1=fillScreen(bg1);
        JLabel backGround = new JLabel(bg1);
        backGround.setBounds(0,0,a,b);
        JP1.add(backGround);
        
        
        /**
         * BUTTONS
         */
        
        /**
         * TEXT FIELDS
         */
        
        /**
         * TEXT AREA
         */
        
        this.setVisible(true);
    }
    
    public void actionPerformed (ActionEvent e) {
	}
    
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
    
    public ImageIcon fillScreen(ImageIcon i){
        
        Image I   =  i.getImage();
        int	h = I.getHeight(this);
        int	l = I.getWidth(this);
        I = I.getScaledInstance(861,600,0);
        
        
        ImageIcon resizedImage = new ImageIcon(I);
        return resizedImage;
    }
}
