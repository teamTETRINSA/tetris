/**
 * main window
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList; //to delete
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;//used to reduce the size of an image
import java.awt.Image;

public class GUI extends JFrame implements MouseListener, ActionListener{
    
    
    //private JButton start;
    //private JLabel backGround;
    private JPanel JP1;
    
    /**
    * initializing parameters
     * */
     
    //to re-check after the main mdoifications
        
    boolean restart=true;// variable to know if we want to restart the game
    boolean start;
    int mode = 2;   /* player mode 2  by default
                            * need now to add a reaction to the button mode player
                        * */
    boolean go = false; // says if the game is over or not
    int lap = 0; // cont the total number of tetriminos placed in the area
    int score1 = 0; // score of player 1 (on left)
    int score2 = 0; // score of player 2 (on right)
    
    /**Constructor
     * for any mode > it opens on mode 2
     * */
     
    public GUI (ArrayList<tetrimino> list, int a, int b, int c, int d, grid tab1, grid tab2){
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
        
        
        ImageIcon original = new ImageIcon("BG1.jpeg");
        Image newImage   =  original.getImage();
        int	h = newImage.getHeight(this);
        int	l = newImage.getWidth(this);
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
         * methods to know if the game is over
         * if the second line of the table (which corresponds to line 1) is filled, the game is over
         * boolean
         * */
    
         // for player mode n°1
        public boolean gameOver(grid a, int mode){
            boolean gameFinished = true;
            for (int i=0 ; i<a.area[1].length ; i++){
                if (a.area[1][i]==0){
                    gameFinished=false;
                    i=a.area[1].length;
                }
            }
            return gameFinished;
        }
    
        // for player mode n°2
        public boolean gameOver(grid a, grid b, int mode){
            boolean gameFinished = true;
            for (int i=0 ; i<a.area[1].length ; i++){
                if (a.area[1][i]==0 && b.area[1][i]==0){
                    gameFinished=false;
                    i=a.area[1].length;
                }
            }
            return gameFinished;
        }
    
        /**
        * method to trandform the number of lines deleted in one step as an increase of the score
        * */
    
        public int addBonus(int b){
            return 1; // so that the code compile
        }
    
        /**
        * method to know when a line is filled and has to be emptied
        * int score, grid g
        * LinkedList<int> 
        * */
    
        public LinkedList<Integer> linesFilled (grid g){ 
            LinkedList<Integer> filledLines = new LinkedList<Integer>(); 
            for (int i = 0 ; i<g.area.length ; i++){
                boolean filled = true ;
                for (int j = 0 ; i<g.area[0].length ; j++){
                    if (g.area[i][j]==0){
                        filled = false ;
                        j = g.area[0].length ;
                    }
                }
                if (filled = true){
                    filledLines.add(i);
                }
            }
            return filledLines;
        }
    
        /**
         * method to delete all lines filled by tetriminos in a grid
         * void
         * */
    
        public int deleteLines(int score, grid A){
            LinkedList<Integer> linesfilledA = linesFilled (A);
            
            //first we delete the numbers of the filled lines
            int count=0;
            for (int a : linesfilledA){
                count=+1;
                for (int i =0 ; i< A.area[0].length ; i++){
                    A.area[a][i]=0;
                }
            }
            return count;

            
            // then we show the grids in terminal and on the GUI with emptied lines
            
            // finally we make fall all the filled boxes above thes lines in the temrinal and on the GUI
            
        }
        
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
        
        
        /*******************************************************************************************
         * to analyse and re-use
         * */
        
        /*
         while(go==false){
                lap+=1;
                score1+=1;
                score2+=1;
                
                Random rand = new Random();
                int t = rand.nextInt(7);
                //System.out.println("T="+t);
                s2=list.get(t);
                
                
                //when tetriminos are placed in the game, we look at all filled lines to delete them
                
                
                int bonus1 = deleteLines(score1,G1); // call the linesFilled method to get a list of the lines that we need to delete
                                                    // delete the filled lines in both grids
                                                    //rearrange the grids making falling all filled boxes above the lines deleted
                                                    // add points for each o
                            
                int bonus2 = deleteLines(score2,G2);
                score1 = addBonus(bonus1);
                score2 = addBonus(bonus2);
                                
                
                //when tetriminos are placed in the game and filled lines deleted, we look if the game is finished
                
                
                go=gameOver(G1,G2,mode);    // as a function of the game mode
                                            //if the second line of the table (which corresponds to line 1) is filled, the game is over
                
                s1=s2; // the next shape already choosed before
                        // (so that we can show it near the game area in the GUI
                        // becomes the main shape of the next lap, 
            }
        */
            
        
        /**CHOOSING RANDOM A TETRIMINO  AND THE SECOND ONE TO PRINT IT ON THE INTERFACE
         * int y = (int) (Math.random()*8);
         * tetrimino s1 = list.get(y);
         * tetrimino s2;
         * */
         
        /** print a panel when the game is over
        * button start becomes avilable and visible
        * if the user push the start button, 
        * the boolean "start" becomes true and the game restart
        * */

}
