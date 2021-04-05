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

        
        // Creation of the ShapeBank of tetriminos
        ArrayList<shape> ShapeBank = new ArrayList<shape> ();

        /**
         * Creating tetriminos
         * */

        shape t1= new tetrimino(1);
        shape t2= new tetrimino(2);
        shape t3= new tetrimino(3);
        shape t4= new tetrimino(4);
        shape t5= new tetrimino(5);
        shape t6= new tetrimino(6);
        shape t7= new tetrimino(7);

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
        
        grid data = new grid();

        //Calling the first "welcome" panel
        WelcomeGUI newWindow = new WelcomeGUI (data, ShapeBank);
        
        while (data.restart==false){
            // do nothing
            System.out.println("### FALSE ###");
            timePause(1000);
        }

        while (data.restart == true){
            
            System.out.println("### NEW LAP ###");

            data.initialiseData();   /* we empty the area before each new game
                                        * useful when we play more that one game after having lauch the java program
                                        * data.restart = false;
                                        * */

            //the first tetrimino choosen is initialized in a diferent way than the following
            int nb = (int)(Math.random()*7);
            System.out.println("AAAAA    "+nb);
            data.T1 = ShapeBank.get(nb);

            /* while we can play and add tetriminos
            * we continue introducing tetriminos at the top of the game
            */

            while (data.start == true){
                System.out.println("DDDDDDDDD       "+data.lap);
                timePause(1000);
                System.out.println("SSSSSSSSS       "+data.speedLevel);
                
                
                // this function allows to decrease the time interval as a functioon of the number of laps done
                data.interval = data.speedLevel*(int)Math.exp((-data.lap)/100) + 200;
                
                data.start = false;
                data.lap += 1;
                data.score += 1;

                data.getInitialPosition();  /* usefull to print the tetrimino
                                                * at the center of the grid
                                                * as a function of its size
                                                * */

                // we choose the next coming tetrimino random - it will be printed next the game area
                int nbs = (int)(Math.random()*7);
                System.out.println("FFFFFFF    "+nb);
                data.T2 = ShapeBank.get(nbs);

                // will be printed "the next coming tetrimino T2" when T1 will be printed on the grid

                while (dropIsPossible(data)){
                    //while the play/pause button is on "pause" mode, do nothing
                    while (data.pause==true){
                    }
                    timePause(data.interval);
                    data.dropTetrimino(1);
                }

                data.transformShape();

                /*
                 * the total score is modified
                 * we add the lap integer to the score
                 * we also add an integer returned by the deleteLines method
                 * see its implementation for details
                 * */

                data.score += data.lap + deleteLines(data);

                /*
                 * is the game finished?
                 * if yes the boolean start stays false and the loop does not restart
                 * if not the start button becomes true
                 * and the next tetrimino that will be used becomes the one printed on the side (t2)
                 * */

                if (! gameOver(data)){
                    data.start = true;
                    t1 = t2;
                }

            }

            // the bestScore is updated

            /** PRINT PopUp window
             * at the end of the game
             * with the score
             * and the bestScore
             * */

            if (data.score>data.bestScore){
                //static problem
                //tetrisDraft.messageDialogNBS();
                data.bestScore=data.score;
            }else{
                //static problem
                //tetrisDraft.messageDialogGO();
            }
            data.score=0;

            /** PRINT "score=0" at the top of the window where the score appears **/

            // the PLAY button waits to be pushed

            while (!data.restart){
                //do nothing
            }

        }
    }

    /********************************************************************************************************
     * METHODS
     * */

    /**
     * TIMEPAUSE
     * used to make drop the tetriminos at regular intervals
     * */
    /*
    public static void timePause () {
		try {
		Thread.sleep(1000);
		}catch(InterruptedException e){}
	}
    * */

    public static void timePause (int ms) {
		try {
		Thread.sleep(ms);
		}catch(InterruptedException e){}
	}

    /**
     * GAMEOVER method
     * methods to know if the game is over
     * if the second line of the table (which corresponds to line 1) is filled, the game is over
     * returns boolean
     * */


    public static boolean gameOver(grid data) {
        boolean gameFinished = true;
        for (int i=0 ; i<data.area[1].length ; i++){
            if (data.area[1][i]==0){
                gameFinished=false;
                i=data.area[1].length;
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

    public static LinkedList<Integer> linesFilled(grid data){

        LinkedList<Integer> filledLines = new LinkedList<Integer>();

        for (int i = 0 ; i<data.area.length ; i++){
            boolean filled = true ;
            for (int j = 0 ; i<data.area[0].length ; j++){
                if (data.area[i][j]==0){
                    filled = false ;
                    j = data.area[0].length ;
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

    public static int deleteLines(grid data){
        // to get a list of the lines we need to delete
        LinkedList<Integer> linesfilled = linesFilled(data);

        // we delete all the numbers contained in filled lines
        int count=0;
        for (int a : linesfilled){
            count=+1;
            for (int i =0 ; i< data.area[0].length ; i++){
                data.area[a][i]=0;
            }
        }

        /* we rearrange the grid : we make fall all the filled boxes
         * above the lines in the terminal and on the GUI
         */

        data.makeFallGrid();

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

    /*
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
    * */


    /**
     * MOVETETRIMINO
     * Translation of the tetrimino of a coordinate dX
     * can move a tetrimino to the right or to the left
     * */

    public static void moveTetrimino(grid data, int dx) {
        if ((dx>0 && moveToRightIsPossible(data)==true)||(dx<0 && moveToLeftIsPossible(data))){
            data.moveTetrimino(dx);
        }
	}

    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY
     * */

    public static void dropTetrimino(grid data, int dy) {
        if (dropIsPossible(data)==true){
            data.dropTetrimino(dy);
        }
	}

    /**
     * ROTATETRIMINO
     * Rotation to the right
     * */

    public static void rotateTetrimino (grid data) {
		data.T1.rotateTetrimino();
    }

    /**
     * DROPSIPOSSIBLE
     * Returns true if the boxes (of the grid) under all colored boxes of a tetrimino are empty
     * */

    public static boolean dropIsPossible(grid data){
        boolean possible = true ;
        for (int i = 0; i< data.T1.tab.length ; i++){
            for (int j=0 ; j< data.T1.tab[0].length ; j++){
                if (data.T1.tab[i/*+1*/][j]==0){
                    if (data.area[data.T1.Y+i+1][data.T1.X] != 0){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
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

    public static boolean moveToRightIsPossible(grid data){
        boolean possible = true ;
        for (int i = 0; i< data.T1.tab.length ; i++){
            for (int j=0 ; j< data.T1.tab[0].length ; j++){
                if (data.T1.tab[i][j+1]==0){
                    if (data.area[data.T1.Y][data.T1.X+j+1] != 0){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
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

    public static boolean moveToLeftIsPossible(grid data){
        boolean possible = true ;
        for (int i = 0; i< data.T1.tab.length ; i++){
            for (int j=0 ; j< data.T1.tab[0].length ; j++){
                if (data.T1.tab[i][j-1]==0){
                    if (data.area[data.T1.Y][data.T1.X+j-1] != 0){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
                    }
                }
            }
        }
        return possible;
    }

}
