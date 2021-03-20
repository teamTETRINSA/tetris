/**
 * main window
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList; //to delete
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D; //used to reduce the size of an image
import java.awt.Image;

public class GUI extends JFrame implements MouseListener, ActionListener, KeyListener{
    
    
    //private JButton start;
    //private JLabel backGround;
    private JPanel JP1;
    // ...
    
    /**Constructor
     * for any player mode > it starts on mode 2
     * */
     
    public GUI (ArrayList<tetrimino> l, int a, int b, int c, int d, grid tab1){
        
        //TetriminosList=l;
        //G1=tab1;
        
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(a,b);
        this.setLocation(c,d);
        this.setTitle("Tetr'INSA");
        addKeyListener(this);
        
        
        
        /**
         * 3 PANELS
         * 2 main panels, 
         *      1 for the grid
         *      1 for the menu bar
         * 1 more
         *      when the game is over
         */
        
        JPanel JP1 = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(10,10,10,10);
        JP1.setBackground(Color.green);
        this.add(JP1);
        
        JPanel JPMenu = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(50,10,10,10);
        JP1.setBackground(Color.yellow);
        this.add(JPMenu);
        
        // a pop up window appears when the game is finished : maybe we'll not use a JPanel...
        /*
        JPanel JPWinner = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(10,30,10,10);
        JP1.setBackground(Color.yellow);
        //this.add(JPWinner);
        * */
        
        /**
         * LABELS
         * background image
         * description of buttons
         */
        
    
        ImageIcon original = new ImageIcon("BG1.jpeg");
        Image newImage   =  original.getImage();
        //int	h = newImage.getHeight(this);
        //int	j = newImage.getWidth(this);
        //newImage = newImage.getScaledInstance(l,600,861);
        newImage = newImage.getScaledInstance(861,600,0);
        

        
        ImageIcon bg1 = new ImageIcon(newImage);
        
        
        JLabel backGround = new JLabel(bg1);
        backGround.setBounds(0,0,a,b);
        JP1.add(backGround);
        
        
        
        ///////////
        
        
        /*
        ImageIcon bg1 = new ImageIcon("BG1.jpeg");
        bg1=fillScreen(bg1);
        JLabel backGround = new JLabel(bg1);
        backGround.setBounds(0,0,a,b);
        JP1.add(backGround);
        * */
        
        
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
    
    /********************************************************************************************************
     * ACTIONLISTENER
     * */
    
    public void actionPerformed (ActionEvent e) {
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
            mainGame.dropTetrimino(1);
        }else if (e.getKeyCode()==KeyEvent.VK_UP){
            mainGame.rotateTetrimino();
        }else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            mainGame.moveTetrimino(1);
        }else if (e.getKeyCode()==KeyEvent.VK_LEFT){
            mainGame.moveTetrimino(-1);
        }
    }

      
        
        /**
         * method to resize an image for the background
         * cannot be used for now
         * */
    
        /*
        public ImageIcon fillScreen(ImageIcon i){
            
            Image I   =  i.getImage();
            int	h = I.getHeight(this);
            int	l = I.getWidth(this);
            I = I.getScaledInstance(861,600,0);
            
            
            ImageIcon resizedImage = new ImageIcon(I);
            return resizedImage;
        }
        * */
        
}
