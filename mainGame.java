import java.util.LinkedList;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;
import java.awt.Dialog;
import javax.swing.JOptionPane;

public class mainGame extends JOptionPane {
  
	public static void main(String[] args) {
        
    /**
     * Initializing parameters
     * */
     
    boolean restart=true;   // variable to know if we want to restart the game
    
    boolean start = true;          //variaale to know if we print another shape on the grid
    
    boolean initialPhase = true;  // variable used in the tetrisGUI-2 classes to know if we are in the initial phase of the game (ther player is choosing
    
    boolean pause = false ;
    
    boolean fallen ;        // variable to know the tetrimino t1 fell
    
    int score = 0;          // score of the player
    
    int bestScore = 0;      // to save the best score
    
    /**boolean go = false; // says if the game is over or not      :      START varibale ?????      **/
    
    int lap = 0;            // count the total number of tetriminos placed in the area
    
    int interval =1000;          //the time interval at which a tetrimino is dropped    
    
    grid G;                 //the game area
    
    tetrimino T1;           //the tetrimino falling on the grid
    
    tetrimino T2;           // the next tetrimino coming ; it is printed on the side of the game
    
    // ArrayList<tetrimino> list = new ArrayList<tetrimino> (ShapeBank()) ; // a list of the different tetriminos that will be used -> ALready used + created in Main
        
        // Creation of the ShapeBank of tetriminos
	
	ArrayList<tetrimino> ShapeBank = new ArrayList<tetrimino> ();
       
    /**
     * Creating tetriminos
     * */
        
    tetrimino t1= new tetrimino(1);
    tetrimino t2= new tetrimino(2);
    tetrimino t3= new tetrimino(3);
    tetrimino t4= new tetrimino(4);
    tetrimino t5= new tetrimino(5);
    tetrimino t6= new tetrimino(6);
    tetrimino t7= new tetrimino(7);
        
    /**
     * for printing the different tetriminos created on the temrinal
     * */
    /*
    System.out.println(t1);
    System.out.println(t2);
    System.out.println(t3);
    System.out.println(t4);
    System.out.println(t5);
    System.out.println(t6);
    System.out.println(t7);
    */
        
    /**
     * now we create a list with all these tetriminos
     * */
         
    ShapeBank.add(t1);
    ShapeBank.add(t2);
    ShapeBank.add(t3);
    ShapeBank.add(t4);
	ShapeBank.add(t5);
    ShapeBank.add(t6);
    ShapeBank.add(t7);
        
    while (restart == true){
        
        restart = false;
        G = new grid(20,10);                //we re-build a grid before each new game
        
        System.out.println(G);              //a supprimer plus tard

        int nb = (int)(Math.random()*8);	//the first tetrimino choosen
		T1 = ShapeBank.get(nb);   
           
        /** AFFICHAGE DE T1 **/
        /* while we can play and add tetriminos
        * we continue introducing tetriminos at the top of the game
        */
            
		while (start == true)
            
            interval =  45*((int) Math.sin(1/(lap+8)))+200; // this function allows to decrease the time ineterval as a functioon of the number of laps done
            start = false;
            lap += 1;
            score += 1;
            
            T1.getInitialPosition(G);
            
			int nbs = (int)(Math.random()*8);
			T2 = ShapeBank.get(nbs);
            
			// will be printed "the next coming tetrimino T2" when T1 will be printed on the grid
            
            while (dropIsPossible(T1, G)){
                //while the play/pause button is on "pause" mode, do nothing
                while (pause==true){
                }
                timePause(interval);
                dropTetrimino(T1,G,1);
            }
            
            G.transformShape(T1);
            
            /*
             * the total score is modified
             * we add the lap integer to the score
             * we also add an integer returned by the deleteLines method
             * see its implementation for details
             * */
                
            score += lap + deleteLines(score, G);
                
            /*
             * is the game finished?
             * if yes the boolean start stays false and the loop does not restart
             * if not the start button becomes true
             * and the next tetrimino that will be used becomes the one printed on the side (t2)
             * */
            
            if (! gameOver(G)){
                start = true;
                t1 = t2;
            }
               
        }
        
        // the bestScore is updated
            
        /** PRINT PopUp window 
         * at the end of the game
         * with the score 
         * and the bestScore
         * */
        
        if (score>bestScore){
            //JOptionPane.showMessageDialog(frame, "New Best Score !"+"/n"+score);
            //JOptionPane.showMessageDialog( null, "New Best Score !" );
            bestScore=score;
        }else{
            //JOptionPane.showMessageDialog(frame, "Game Over !"+"/n"+score+"/n"+"High Score : "+bestScore);
            //JOptionPane.showMessageDialog( null, "GameOver !" );
        }
        score=0;
            
        /** PRINT "score=0" at the top of the window where the score appears **/
            
        // the PLAY button waits to be pushed
        
        while (!restart){
            //do nothing
        }
        
    }
    

	
    /********************************************************************************************************
     * METHODS
     * */
     
    /**
     * TIMEPAUSE
     * used to make drop the tetriminos at regular intervals
     * */
    
    public static void timePause (int ms) {
		try {
		Thread.sleep(ms);
		}catch(InterruptedException e){}
	}
    
    /**
     * CHECK method
     * returns true if the tetrimino is still falling
     * param : grid
     * */
    
    public static boolean check(grid g){
        return false;  // so that it compiles
    }
    
    /*public int deleteLines(int score, grid g){
        return 0;   // so that it compiles
    }*/
    
    /** 
     * GAMEOVER method
     * methods to know if the game is over
     * if the second line of the table (which corresponds to line 1) is filled, the game is over
     * returns boolean
     * */
    

    public static boolean gameOver(grid a) {
        boolean gameFinished = true;
        for (int i=0 ; i<a.area[1].length ; i++){
            if (a.area[1][i]==0){
                gameFinished=false;
                i=a.area[1].length;
            }
        }
        return gameFinished;
    }
    
    
    /**
    * LINES method
    * method to know when a line is filled and has to be emptied
    * int score, grid g
    * LinkedList<int> 
    * */
    
    public static LinkedList<Integer> linesFilled (grid g){ 
        
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
     * DELETELINES
     * method to delete all lines filled by tetriminos in a grid
     * void
     * */

    public static int deleteLines(int score, grid g){
        // to get a list of the lines we need to delete
        LinkedList<Integer> linesfilled = linesFilled (g);
        
        // we delete all the numbers contained in filled lines
        int count=0;
        for (int a : linesfilled){
            count=+1;
            for (int i =0 ; i< g.area[0].length ; i++){
                g.area[a][i]=0;
            }
        }
        
        /* we rearrange the grid : we make fall all the filled boxes 
         * above the lines in the terminal and on the GUI
         */
        
        g.makeFallGrid();
        
        /* we return an integer corresponding to the bonus got
         * using the method addBonus? is it usefull or maybe we can directly do it here...
         * */
         
        int bonus = (int) Math.pow(10,count);
        return bonus; 
    }
    
    /**
     * GET THE SCORE
     * the score increases when one line is completed/deleted
     * the more lines you complete at the same time, the higher the score
     * */
     
    public static int getScore(grid g1){
		int score=0;
		int nbLines=0; // number of lines completed (so deleted) at the same time
		for(int i=0 ; i<g1.area.length ; i++){
			boolean filled1 = true;
			for(int j=0 ; i<g1.area[0].length ; j++){
				if(filled1 = true){ // if one line is completed
					score = score + nbLines*100; // scoring system for level 1, we can adapt it
				} 
			}
		}
		return score;
	}
    
    /**
     * ADDBONUS
     * method to transform the number of lines deleted in one step as an increase of the score
     * function of the lap number and the number of lines deleted in one shot
     * */
    
    public static int addBonus(int b){
        return 1; // so that it compiles
    }
    
    /**
     * GETCELL
     * Returns the content of one cell of the grid
     * */
     
    public static int getCell(grid g, int x, int y) {
        return g.getCell(x,y);
	}
    
    /**
     * MOVETETRIMINO
     * Translation of the tetrimino of a coordinate dX
     * can move a tetrimino to the right or to the left 
     * */
     
    public static void moveTetrimino(tetrimino t, grid g, int dx) {
        if ((dx>0 && moveToRightIsPossible(t,g)==true)||(dx<0 && moveToLeftIsPossible(t,g))){
            t.moveTetrimino(dx);
        }
	}
    
    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY 
     * */
     
    public static void dropTetrimino(tetrimino t, grid g, int dy) {
        if (dropIsPossible(t,g)==true){
            t.dropTetrimino(dy);
        }
	}
	
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    public static void rotateTetrimino (tetrimino t) {
		t.rotateTetrimino();
    }
    
    /**
     * DROPSIPOSSIBLE
     * Returns true if the boxes (of the grid) under all colored boxes of a tetrimino are empty
     * */
    
    public static boolean dropIsPossible(tetrimino t, grid g){
        boolean possible = true ;
        for (int i = 0; i< t.tab.length ; i++){
            for (int j=0 ; j< t.tab[0].length ; j++){
                if (t.tab[i+1][j]==0){
                    if (g.area[t.Y+i+1][t.X] != 0){
                        possible = false ;
                        i = t.tab.length;
                        j = t.tab[0].length;
                    }
                }
            }
        }
        return possible;
    }
    
    /**
     * MOVETORIGHTISPOSSIBLE
     * Returns true if the boxes (of the grid) on the right side of all colored boxes of a tetrimino are empty
     * */
    
    public static boolean moveToRightIsPossible(tetrimino t, grid g){
        boolean possible = true ;
        for (int i = 0; i< t.tab.length ; i++){
            for (int j=0 ; j< t.tab[0].length ; j++){
                if (t.tab[i][j+1]==0){
                    if (g.area[t.Y][t.X+j+1] != 0){
                        possible = false ;
                        i = t.tab.length;
                        j = t.tab[0].length;
                    }
                }
            }
        }
        return possible;
    }
    
     /**
     * MOVETOLEFTISPOSSIBLE
     * Returns true if the boxes (of the grid) on the right side of all colored boxes of a tetrimino are empty
     * */
    
    public static boolean moveToLeftIsPossible(tetrimino t, grid g){
        boolean possible = true ;
        for (int i = 0; i< t.tab.length ; i++){
            for (int j=0 ; j< t.tab[0].length ; j++){
                if (t.tab[i][j-1]==0){
                    if (g.area[t.Y][t.X+j-1] != 0){
                        possible = false ;
                        i = t.tab.length;
                        j = t.tab[0].length;
                    }
                }
            }
        }
        return possible;
    }
    
    public static boolean pauseTheGame (boolean pause){
        if (pause==false){
            return true ;
        }else{
            return false;
        }
    }
}
