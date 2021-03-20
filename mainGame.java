import java.util.LinkedList;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;

public class mainGame /*extends JPanel()**/ {
	
    /**
     * initializing parameters
     * */
     
    boolean restart=true;   // variable to know if we want to restart the game
    boolean start;          //varibale to know if we print another shape on the grid
    boolean fallen ;        // variable to know the tetrimino t1 fell
    int score = 0;          // score of the player
    int bestScore = 0;      // to save the best score
    /**boolean go = false; // says if the game is over or not      :      START varibale ?????      **/
    int lap = 0;            // count the total number of tetriminos placed in the area
    /**boolean possibleFall ;  // to says if a tetrimno can fall (if there is nothing under it) **/
    Timer T;                // tetriminos fall at precise (shorter and shorter) time intervals
    grid G;                 //the game area
    tetrimino t1;           //the tetrimino falling on the grid
    tetrimino t2;           // the next tetrimino coming ; it is printed on the side of the game
    ArrayList<tetrimino> list = new ArrayList<tetrimino> (ShapeBank()) ; // a list of the different tetriminos that will be used
    
    /********************************************************************************************************
     * MAIN
     * */
  
	public static void main(String[] args) {
        while (restart == true){
            restart = false;
            G = new grid(20,10);          //we re-build a grid before each new game
            // Paramètre position initiale x à calculer 
            System.out.println(G);        //a supprimer plus tard
            t1 = newTetrimino();          //the first tetrimino choosen
            /** AFFICHAGE DE T1 **/
            /* while we can play and add tetriminos
             * we continue introducing tetriminos at the top of the game
             * */
            while (start == true)
                start = false;
                lap += 1;
                score += 1;
                t1.getInitialPosition(G);
                t2=newTetrimino();        // will be printed a "the next coming tetrimino" when t1 will be printed on the grid
                /** PRINT T2 **/   
                if (check(G)==true)                    //initialisation : la chute n°1 est-elle possible?
                
                /* tant LA CHUTE EST POSSIBLE 
                 * check method : I don't know if we put it in this maintest or in the grid class...
                 * **/
                While (G.check()==true){    
                    /** PRINT ... **/
                }
                
                /* we exchange the even numbers of a tetrimino as odd ones
                 * it now belongs to the fallen tetriminos
                 * */
                g.tranformShape();
                /** PRINT G **/
                /*
                 * the total score is modified
                 * we add the lap integer to the score
                 * we also add an integer returned by the deleteLines method
                 * see its implementation for details
                 * */
                score += lap + deleteLines(score, G);
                /** PRINT score **/
                
                /*
                 * is the game finished?
                 * if yes the boolean start stays false and the loop does not restart
                 * if not the start button becomes true
                 * and the next tetrimino that will be used becomes the one printed on the side (t2)
                 * */
                if (!GameOver(G)){
                    start == true;
                    t1 = t2;
                }
            }
            // the bestScore is updated
            if (bestScore <= score){
                bestScore=score;
            }
            /** PRINT PopUp window 
             * for the end of the game
             * with the score 
             * and the bestScore
             * */
            score=0;
            /** PRINT "score=0" at the top of the window where the score appears **/
            
            // the PLAY button waits to be pushed
            while (!restart){
                /** WAIT **/
            }
        }
    }
    
    /********************************************************************************************************
     * METHODS
     * methods to analyse the game and (they do not modify the GUI)
     * */
    
    public ArrayList ShapeBank () {
        
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
        
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);
        
        /**
         * now we create a list with all these tetriminos
         * */
         
        ArrayList<tetrimino> list = new ArrayList<tetrimino>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        
		return list;
       
    }
    
    public tetrimino newTetrimino () {
		
		nb = (int)(Math.random()*8);
		shape1 = list.get(nb);
		return shape1;
	}
    
    /**
     * CHECK method
     * returns true if the tetrimino is still falling
     * param : grid
     * */
    
    public boolean check(grid g){
        return false;  // so that it compiles
    }
    
    public int deleteLines(int score, grid g){
        return 0;   // so that it compiles
    }
    
    /** 
     * GAMEOVER method
     * methods to know if the game is over
     * if the second line of the table (which corresponds to line 1) is filled, the game is over
     * returns boolean
     * */
    

    public boolean gameOver(grid g){
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
     * DELETELINES
     * method to delete all lines filled by tetriminos in a grid
     * void
     * */

    public int deleteLines(int score, grid g){
        // to get a list of the lines we need to delete
        LinkedList<Integer> linesfilledg = linesFilled (g);
        
        // we delete all the numbers contained in filled lines
        int count=0;
        for (int a : linesfilledA){
            count=+1;
            for (int i =0 ; i< A.area[0].length ; i++){
                A.area[a][i]=0;
            }
        }

        /** PRINT grid **/
        
        // we rearrange the grid
        /** PRINT score **/
        
        // finally we make fall all the filled boxes above thes lines in the terminal and on the GUI
        
        /* we return an integer corresponding to the bonus got
         * using the method addBonus? is it usefull or maybe we can directly do it here...
         * */
        return 1; // so that it compiles
    }
    
    /**
     * ADDBONUS
     * method to transform the number of lines deleted in one step as an increase of the score
     * function of the lap number and the number of lines deleted in one shot
     * */
    
    public int addBonus(int b){
        return 1; // so that it compiles
    }
	
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    public void rotateTetrimino () {
		t1.rotateTetrimino();
    }
    
    /**
     * MOVETETRIMINO
     * Translation of the tetrimino of a coordinate dX 
     * */
     
    public void moveTetrimino(int dx) {
        t1.moveTetrimino(dx);
	}
    
    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY 
     * */
     
    public void dropTetrimino(int dy) {
        t1.dropTetrimino(dy);
	}
    
	/**
     * GETCELL
     * Returns the content of one cell of the grid
     * */
     
    public int getCell(int x, int y) {
        return G.getCell(x,y);
	}
    
    /**
     * GETINITIALPOSITION
     * method to get the initial position of a tetrimino on the grid
     * we place the top left hand corner of the tetrimino tab
     * */
    
    
	
}
