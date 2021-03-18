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

public class GUI extends JFrame implements MouseListener, ActionListener{
    
    
    //private JButton start;
    //private JLabel backGround;
    private JPanel JP1;
    
    /**
     * initializing parameters
     * */
     
    //to re-check after the main modifications
        
    boolean restart=true;// variable to know if we want to restart the game
    boolean start;
    int mode = 2;   /* player mode 2  by default
                            * need now to add a reaction to the button mode player
                        * */
    boolean go = false; // says if the game is over or not
    int lap = 0; // cont the total number of tetriminos placed in the area
    int score1 = 0; // score of player 1 (on left)
    int score2 = 0; // score of player 2 (on right)
    ArrayList<tetrimino> TetriminosList;
    grid G1;
    grid G2;
    
    /**Constructor
     * for any player mode > it starts on mode 2
     * */
     
    public GUI (ArrayList<tetrimino> l, int a, int b, int c, int d, grid tab1, grid tab2){
        
        TetriminosList=l;
        G1=tab1;
        G2=tab2;
        
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(a,b);
        this.setLocation(c,d);
        this.setTitle("Tetr'INSA");
        
        
        
        /**
         * PANELS
         * 3 main panels, 
         *      1 for each grid
         *      1 for the menu bar
         * 2 more when the game finishes 
         *      to annouce who won and scores
         */
        
        JPanel JPG1 = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(10,10,10,10);
        JP1.setBackground(Color.green);
        this.add(JPG1);
        
        JPanel JPG2 = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(30,10,10,10);
        JP1.setBackground(Color.red);
        this.add(JPG2);
        
        JPanel JPMenu = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(50,10,10,10);
        JP1.setBackground(Color.yellow);
        this.add(JPMenu);
        
        JPanel JPWinner = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(10,30,10,10);
        JP1.setBackground(Color.yellow);
        //this.add(JPWinner);
        
        JPanel JPLoser = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(30,30,10,10);
        JP1.setBackground(Color.yellow);
        //this.add(JPLoser);
        
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
     * GRAPHICAL METHODS
     * all methods that will take into account graphical parameters
     * */
    
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
    
    /********************************************************************************************************
     * METHODS
     * methods to analyse the game and (they do not modify the GUI)
     * */
     

        /**
         * method to get the initial position of a tetrimino on the grid
         * we place the top left hand corner of the shape
         * this method could be use if we use different width for grids
         * int
         * */
        
        //only elements
        
        /* 
        int pa=a.tab.length/2; //milieu shape a
        int pA=A.area[1].length/2; //milieu gamearea A
        int d=pA-pa ;//position début ajout a dans A
        
		
        // détermination de la position d'insertion de la forme au début de la grille de jeu
        System.out.println("milieu shape a = "+pa);
        System.out.println("milieu gamearea A = "+pA);
        System.out.println("position début ajout a dans A = "+d);
        * */
    
        /**
         * method to print the 2 grids at the same time on the terminal
         * to delete?
         * */
    
    
        // only elements
    
        //does not work
        /*
        public void showGrid(grid A, grid B){
        System.out.println(A);
        System.out.println(B);
        }
        * */
        
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
