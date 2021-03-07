/**
 * different tests
 * main
 * create tetriminos
 * create a list of tetriminos
 * */

import java.util.LinkedList;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.Random;

public class mainTest{
  
	public static void main(String[] args){
        
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
        
        boolean start=true;
        
        while (start){
            start=false;
        
            /**
             * creating the grid
             * */
            
            int mode = 2;   /* player mode 2  by default
                             * need now to add a reaction to the button mode player
                             * */
            
            boolean go = false ; // says if the game is over or not
            int lap = 0; // count the total number of tetriminos placed in the area
            int score1 = 0;
            int score2 =0;
            
            //initializing tetriminos
            int y = (int) (Math.random()*8);
            tetrimino s1 = list.get(y);
            tetrimino s2;
             
            grid G1 = new grid(20,10);
            grid G2 = new grid(20,10);
            
            System.out.println(G1);
            System.out.println(G2);
            
            
            GUI window = new GUI (861,600,100,50);
            //GUI window = new GUI (listeCourbe,1000,600,100,50);
            
            /** main loop
             * allow to repeat the fall of tetriminos until the game is over
             * */
            
            while(go==false){
                lap+=1;
                score1+=1;
                score2+=1;
                
                Random rand = new Random();
                int t = rand.nextInt(7);
                //System.out.println("T="+t);
                s2=list.get(t);
                
                
                //when tetriminos are placed in the game, we look at all filled lines to delete them
                
                /*
                int bonus1 = deleteLines(score1,G1); /* call the linesFilled method to get a list of the lines that we need to delete
                                * delete the filled lines in both grids
                                * rearrange the grids making falling all filled boxes above the lines deleted
                                * add points for each o
                                * */
                int bonus2 = deleteLines(score2,G2);
                score1 = addBonus(bonus1);
                score2 = addBonus(bonus2);
                                
                
                //when tetriminos are placed in the game and filled lines deleted, we look if the game is finished
                
                /*
                go=gameOver(G1,G2,mode);    /* as a function of the game mode
                                             * if the second line of the table (which corresponds to line 1) is filled, the game is over
                                             * */
                
                s1=s2; /* the next shape already choosed before
                        * (so that we can show it near the game area in the GUI
                        * becomes the main shape of the next lap, 
                        * */
            }
            
            /** print a panel when the game is over
             * button start becomes avilable and visible
             * if the user push the start button, 
             * the boolean "start" becomes true and the game restart
             * */
            
            
        }
    }
    
    /**
     * method to know if the game is over
     * if the second line of the table (which corresponds to line 1) is filled, the game is over
     * boolean
     * */
    
    
    public boolean gameOver(grid a, grid b, int mode){
        boolean gameFinished = true;
        
        switch (mode){
            case 1 :
            for (int i=0 ; i<a.area[1].length ; i++){
                if (a.area[1][i]==0){
                    gameFinished=false;
                    i=a.area[1].length;
                }
            }
            break;
            case 2 :
            for (int i=0 ; i<a.area[1].length ; i++){
                if (a.area[1][i]==0 && b.area[1][i]==0){
                    gameFinished=false;
                    i=a.area[1].length;
                }
            }
            break;
        }
        return gameFinished;
    }
    
    /**
     * method to delete all lines filled by tetriminos
     * 
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
     * method to trandform the number of lines deleted in one step as an increase of the score
     * */
    
    public int addBonus(int b){
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
     * */
    
    
        // only elements
    
        //does not work
        /*
        public void showGrid(grid A, grid B){
        System.out.println(A);
        System.out.println(B);
        }
        * */
        
}
