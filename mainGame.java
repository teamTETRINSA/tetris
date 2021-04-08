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
            
            

            data.initialiseData();   /* we empty the area before each new game
                                        * useful when we play more that one game after having lauch the java program
                                        * data.restart = false;
                                        * */

            //the first tetrimino choosen is initialized in a diferent way than the following
            int nb = (int)(Math.random()*7);
            System.out.println("new T1 >>>"+nb);
            data.T1 = ShapeBank.get(nb);

            /* while we can play and add tetriminos
            * we continue introducing tetriminos at the top of the game
            */

            while (data.start == true){
                System.out.println("###### NEW LAP ######");
                
                timePause(1000);
                //System.out.println("SSSSSSSSS       "+data.speedLevel);
                
                
                // this function allows to decrease the time interval as a functioon of the number of laps done
                data.interval = data.speedLevel*(int)Math.exp((-data.lap)/100) + 200;
                
                data.start = false;
                data.lap += 1;
                System.out.println("######  lap :"+data.lap+"   ######");
                data.score += 1;
                System.out.println("######  score : "+data.score+"  ######");

                data.getInitialPosition();  /* usefull to print the tetrimino
                                                * at the center of the grid
                                                * as a function of its size
                                                * */

                // we choose the next coming tetrimino random - it will be printed next the game area
                int nbs = (int)(Math.random()*7);
                data.T2 = ShapeBank.get(nbs);
                System.out.println("######  T2  >> "+nbs+" ######");

                // will be printed "the next coming tetrimino T2" when T1 will be printed on the grid

                while (dropIsPossible(data)){
                    if (data.pause == false){ //if the play/pause button is on "pause" mode, do nothing                    
                        timePause(data.interval);
                        data.dropTetrimino(1);
                        //System.out.println("drop ");
                    }
                }

                data.transformShape();

                /*
                 * the total score is modified
                 * we add the lap integer to the score
                 * we also add an integer returned by the deleteLines method
                 * see its implementation for details
                 * */
                int bonus = deleteLines(data) + data.lap;
                data.score +=  bonus;

                /*
                 * is the game finished?
                 * if yes the boolean start stays false and the loop does not restart
                 * if not the start button becomes true
                 * and the next tetrimino that will be used becomes the one printed on the side (t2)
                 * */

                if (gameOver(data)==false){
                    data.start = true;
                    data.T1 = data.T2;
                    System.out.println("######  T1 = T2 ######");
                    System.out.println();
                }
            }
            System.out.println("##################################");
            System.out.println("##################################");
            System.out.println("######      GAME OVER      #######");
            System.out.println("######      SCORE : "+data.score+"    #######");
            
            
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
            
            System.out.println("######      BESTSCORE : "+data.bestScore+"    #######");
            System.out.println("##################################");
            System.out.println("##################################");
            System.out.println();
            
            data.score=0;
            System.out.println("Score = 0");


            // the PLAY button waits to be pushed

            while (data.restart==false){
                //do nothing
                System.out.println("wait ... ");
                timePause(1000);
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
        boolean gameFinished = false;
        for (int i=0 ; i<data.area[1].length ; i++){
            if (data.area[1][i]!=0){
                gameFinished=true;
                i=data.area[1].length;
            }
        }
        return gameFinished;
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
        for (int l : linesfilled){
            count=+1;
            for (int i =0 ; i< data.area[0].length ; i++){
                data.area[l][i]=0;
            }
            data.makeFallGrid(l); // we rearrange the grid : we make fall all the filled boxes above the lines
        }
        System.out.println("     count = "+count);

        

        

        /* we return an integer corresponding to the bonus got
         * using the method addBonus? is it usefull or maybe we can directly do it here...
         * */
        if (count > 0){
            int bonus = (int) Math.pow(10,count);
            return bonus;
        }else{
            return 0;
        }
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
            for (int j = 0 ; j<data.area[0].length ; j++){
                if (data.area[i][j]==0){
                    filled = false ;
                    j = data.area[0].length ;
                }
            }
            if (filled == true){
                filledLines.add(i);
            }
        }
        return filledLines;
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
    
    /*
    public static boolean dropIsPossible(grid data){
        boolean possible = true ;
        for (int i = 0; i< data.T1.tab.length ; i++){
            for (int j=0 ; j< data.T1.tab[0].length ; j++){
                if (data.T1.tab[i][j]!=0){
                    if ((data.T1.Y == data.area.length -3-1) && (ShapeCountEmptyLines(data,1)==true)){
                        if (data.area[data.T1.Y+i+1][data.T1.X+j] != 0){
                            possible = false;
                            i = data.T1.tab.length;
                            j = data.T1.tab[0].length;
                        }
                    }else if((data.T1.Y == data.area.length -2-1) && (ShapeCountEmptyLines(data,2)==true)){
                        if (data.area[data.T1.Y+i+1][data.T1.X+j] != 0){
                            possible = false;
                            i = data.T1.tab.length;
                            j = data.T1.tab[0].length;
                        }
                    }else if ((data.T1.Y < data.area.length -3-1) && (data.area[data.T1.Y+i+1][data.T1.X+j] != 0)){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
                    }else{
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
                    }
                }
            }
        }
        return possible;
    }
    * */
    
    public static boolean dropIsPossible(grid data){
        boolean possible = true ;
        if (data.T1.Y < data.area.length -4){
            for (int i = 0; i< data.T1.tab.length ; i++){
                for (int j=0 ; j< data.T1.tab[0].length ; j++){
                    if ((data.T1.tab[i][j] != 0) && (data.area[data.T1.Y+i+1][data.T1.X+j] != 0)){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
                    }
                }
            }
        } else if ((data.T1.Y == data.area.length -4) && (ShapeCountEmptyLines(data,1)==true)){
            for (int i = 0; i< data.T1.tab.length ; i++){
                for (int j=0 ; j< data.T1.tab[0].length ; j++){
                    if ((data.T1.tab[i][j] != 0) && (data.area[data.T1.Y+i+1][data.T1.X+j] != 0)){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
                    }
                }
            }
        } else if ((data.T1.Y == data.area.length -3) && (ShapeCountEmptyLines(data,2)==true)){
            for (int i = 0; i< data.T1.tab.length ; i++){
                for (int j=0 ; j< data.T1.tab[0].length ; j++){
                    if ((data.T1.tab[i][j] != 0) && (data.area[data.T1.Y+i+1][data.T1.X+j] != 0)){
                        possible = false ;
                        i = data.T1.tab.length;
                        j = data.T1.tab[0].length;
                    }
                }
            }
        } else {
            possible = false ;
        }
        return possible;
    }

    /**
     * MOVETORIGHTISPOSSIBLE
     * Returns true if the boxes (of the grid) on the right side of all colored boxes of a tetrimino are empty
     * */

    public static boolean moveToRightIsPossible(grid data){
        boolean possible = true ;
        if (data.T1.X < data.area[0].length -4){
            for (int i = 0; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i+1] != 0)){
                        possible = false ;
                    }
                }
            }
        }else if ((data.T1.X == data.area[0].length -4) && (RightSideEmptyColumns(data,1)==true)){
            for (int i = 0; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i+1] != 0)){
                        possible = false ;
                    }
                }
            }
        }else if ((data.T1.X == data.area[0].length -3) && (RightSideEmptyColumns(data,2)==true)){
            for (int i = 0; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i+1] != 0)){
                        possible = false ;
                    }
                }
            }
        }else if(data.T1.X == -2){
            for (int i = 1; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i+1] != 0)){
                        possible = false ;
                    }
                }
            }
        }else{
            possible = false;
        }
        return possible;
    }

     /**
     * MOVETOLEFTISPOSSIBLE
     * Returns true if the boxes (of the grid) on the right side of all colored boxes of a tetrimino are empty
     * */
    
    public static boolean moveToLeftIsPossible(grid data){
        boolean possible = true ;
        //OK
        if (data.T1.X > 0){
            for (int i = 0; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i-1] != 0)){
                        possible = false ;
                    }
                }
            }
        
        }else if ((data.T1.X == 0) && (LeftSideEmptyColumns(data,1)==true)){    /* and if the first column of the tetrimino's tab is empty,
                                                                                 * we can analyse the x-1 column of the grid's area when a 
                                                                                 * box of the tetrimino's tab is filled bu a number different than 0
                                                                                 * */
            for (int i = 0; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i-1] != 0)){
                        possible = false ;
                    }
                }
            }
        }else if((data.T1.X == -1) && (LeftSideEmptyColumns(data,2)==true)){    /* and if the 2 first columns of the tetrimino's tab are empty,
                                                                                 * we can analyse the x-1 column of the grid's area when a 
                                                                                 * box of the tetrimino's tab is filled by a number different than 0
                                                                                 * */
            for (int i = 0; i< data.T1.tab[0].length ; i++){
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if ((data.T1.tab[j][i]!=0) && (data.area[data.T1.Y+j][data.T1.X+i+1] != 0)){
                        possible = false ;
                    }
                }
            }
        }else{
            possible = false;
        }
        return possible;
    }
    
    /*
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
    * */
    
    /**
     * SHAPECOUNTEMPTYLINES
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    
    public static boolean ShapeCountEmptyLines(grid data, int n){
        boolean ok = true ;
        switch (n){
            case 1:
                for (int j=0 ; j< data.T1.tab[0].length ; j++){
                    if (data.T1.tab[data.T1.tab.length-1][j]!=0){
                        ok=false;
                    }
                }
            break;
            
            case 2:
                for (int i = data.T1.tab.length-1; i>1 ; i--){
                    for (int j=0 ; j< data.T1.tab[0].length ; j++){
                        if (data.T1.tab[i][j]!=0){
                            ok=false;
                        }
                    }
                }
            break;
        }
        return ok;
    }
    
    /**
     * RIGHTSIDEEMPTYCOLUMNS
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    
    public static boolean RightSideEmptyColumns(grid data, int n){
        boolean ok = true ;
        switch (n){
            case 1:
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if (data.T1.tab[j][data.T1.tab[0].length-1]!=0){
                        ok=false;
                    }
                }
            break;
            
            case 2:
                for (int i = data.T1.tab[0].length-2; i < data.T1.tab[0].length ; i++){
                    for (int j=0 ; j< data.T1.tab.length ; j++){
                        if (data.T1.tab[j][i]!=0){
                            ok=false;
                        }
                    }
                }
            break;
        }
        return ok;
    }
    
    /**
     * LEFTSIDEEMPTYCOLUMNS
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    
    public static boolean LeftSideEmptyColumns(grid data, int n){
        boolean ok = true ;
        switch (n){
            case 1:
                for (int j=0 ; j< data.T1.tab.length ; j++){
                    if (data.T1.tab[j][0]!=0){
                        ok=false;
                    }
                }
            break;
            /*
            case 2:
                for (int i = data.T1.tab[0].length-2; i < data.T1.tab[0].length ; i++){
                    for (int j=0 ; j< data.T1.tab.length ; j++){
                        if (data.T1.tab[j][i]!=0){
                            ok=false;
                        }
                    }
                }
            break;
            * */
        }
        return ok;
    }
    
    
    

}
