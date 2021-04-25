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
        
        
        grid data = new grid();
        boolean formerBestScoreUpdated = false; 
        
        

        //Calling the first "welcome" panel
        WelcomeGUI newWindow = new WelcomeGUI (data);
        
        while (data.restart==false){
		// While the button play is not pressed, do nothing
            System.out.println("### FALSE ###");
            timePause(1000);
        }

        while (data.restart == true){
            
		data.restart = false;

            //then create a new shapeBank with the X copmponent of tetriminos centered
            
            data.ShapeBank = bank(data);
            
            data.pause=false;

            data.initialiseData();   /* we empty the area before each new game
                                        * useful when we play more that one game after having lauch the java program
                                        * */

            //USELESS NOW
            /*
            //the first tetrimino choosen is initialized in a diferent way than the following
            int nb = (int)(Math.random()*7);
            //data.T1 = data.ShapeBank.get(nb);
            data.T1 = new tetrimino(nb);
            * */

            /* while we can play and add tetriminos
            * we continue introducing tetriminos at the top of the game
            */

            while (data.start == true){
                System.out.println("###### NEW LAP ######");
                
                timePause(1000);
                //System.out.println("SSSSSSSSS       "+data.speedLevel);
                
                
                // this function allows to decrease the time interval as a function of the number of laps done
                data.interval = data.speedLevel*(int)Math.exp((-data.lap)/100) + 100;
                
                data.start = false;
                data.lap += 1;
                System.out.println("######  lap : "+data.lap+"   ######");
                data.score += 1;
                System.out.println("######  score : "+data.score+"  ######");

                // we choose the next coming tetrimino randomly - it will be printed next the game area
                int nbs = (int)(Math.random()*7);
                //data.T2 = data.ShapeBank.get(nbs);
                data.T2 = new tetrimino(nbs);
                //centering the tetrimino on the grid
                data.getInitialPosition();
                
                //System.out.println("######  T2  >> "+nbs+" ######");

                // will be printed "the next coming tetrimino T2" when T1 will be printed on the grid

                while (data.dropIsPossible()){
                    if (data.pause == false){ //if the play/pause button is on "pause" mode, do nothing                    
                        timePause(data.interval);
                        data.dropTetrimino(1);
                    }
                }

                //sound when a tetrimno end falling

                tetrisGUI.soundEndFall.start();

                data.transformShape();

                /*
                 * the total score is modified
                 * we add the lap integer to the score
                 * we also add an integer returned by the deleteLines method
                 * see its implementation for details
                 * */
                int bonus = deleteLines(data);
                data.score +=  bonus;

                // data.bestScore attribute is updated
                // data.bestScore is updated

                if (data.score>data.bestScore){
                    if (formerBestScoreUpdated==false){
                        data.formerBestScore = data.bestScore;
                        formerBestScoreUpdated=true;
                    }
                    data.bestScore=data.score;
                    
                }

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
            System.out.println("######      BESTSCORE : "+data.bestScore+"    #######");
            System.out.println("##################################");
            System.out.println("##################################");
            System.out.println();
            
            tetrisGUI.soundGameOver.start();
            //timePause(2000);
            
            /**
             * PRINT GameOverGUI window
             * */

            GameOverGUI WindowGameover = new GameOverGUI (data.score,data);
			WindowGameover.setVisible(true);
            
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
        for (int i=0 ; i<data.areaO[1].length ; i++){
            if (data.areaO[1][i]!=null){
                gameFinished=true;
                i=data.areaO[1].length;
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
            tetrisGUI.soundDeletedLine.start();
            for (int i =0 ; i< data.areaO[0].length ; i++){
                data.areaO[l][i]=0;
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

        for (int i = 0 ; i<data.areaO.length ; i++){
            boolean filled = true ;
            for (int j = 0 ; j<data.areaO[0].length ; j++){
                if (data.areaO[i][j]==null){
                    filled = false ;
                    j = data.areaO[0].length ;
                }
            }
            if (filled == true){
                filledLines.add(i);
            }
        }
        return filledLines;
    }






    /**
     * MOVETETRIMINO
     * Translation of the tetrimino of a coordinate dX
     * can move a tetrimino to the right or to the left
     * */

    public static void moveTetrimino(grid data, int dx) {
        if (data.pause == false){ //if the play/pause button is on "pause" mode, do nothing 
            if ((dx>0 && data.moveToRightIsPossible()==true)||(dx<0 && data.moveToLeftIsPossible())){
                data.moveTetrimino(dx);
                // problem here to restart the sound when we push the button the second time
                tetrisGUI.soundKeyboardMove.start();
            }
        }
	}

    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY
     * */

    public static void dropTetrimino(grid data) {
		if (data.pause == false){ //if the play/pause button is on "pause" mode, do nothing 
			if (data.dropIsPossible()){
				data.dropTetrimino(1);
                // problem here to restart the sound when we push the button the second time
                tetrisGUI.soundKeyboardDrop.start();
			}
		}
	}

    /**
     * ROTATETRIMINO
     * Rotation to the right
     * */
	
	//USELESS NOW
	/*
    public static void rotateTetrimino (grid data) {
		data.T1.rotateTetrimino();
		System.out.println("you have rotated");
    }*/

   
    
    /**
     * SHAPECOUNTEMPTYLINES
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    //USELESS NOW
    // but if we delete the methods the game is not painted no more on the GUI

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
    //USELESS NOW
    // but if we delete the methods the game is not painted no more on the GUI
    public static boolean RightSideEmptyColumns(grid data, int n, int tet_1_or_2){
        boolean ok = true ;
        if (tet_1_or_2 == 2){
            for (int j=0 ; j< data.T2.tab.length ; j++){
                if (data.T2.tab[j][data.T2.tab[0].length-1]!=0){
                    ok=false;
                }
            }
        }else{
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
        }
        return ok;
    }
    
    
    /**
     * LEFTSIDEEMPTYCOLUMNS
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    
    public static boolean LeftSideEmptyColumns(grid data, int n, int tet_1_or_2){
        boolean ok = true ;
        if (tet_1_or_2 == 2){
            for (int j=0 ; j< data.T2.tab.length ; j++){
                if (data.T2.tab[j][0]!=0){
                    ok=false;
                }
            }
        }else{
            switch (n){
                case 1:
                    for (int j=0 ; j< data.T1.tab.length ; j++){
                        if (data.T1.tab[j][0]!=0){
                            ok=false;
                        }
                    }
                break;
                
                case 2:
                    for (int i = 0; i < 2 ; i++){
                        for (int j=0 ; j< data.T1.tab.length ; j++){
                            if (data.T1.tab[j][i]!=0){
                                ok=false;
                            }
                        }
                    }
                break;
            }
        }
        return ok;
    }
    
    
    /**
     * BANK
     * create a new bank where the tetriminos hav their X coordinate centered
     * */
    
    public static ArrayList<shape> bank(grid data){
        ArrayList<shape> BANK = new ArrayList<shape> ();
        
        //Creating tetriminos

        shape t1= new tetrimino(1);
        shape t2= new tetrimino(2);
        shape t3= new tetrimino(3);
        shape t4= new tetrimino(4);
        shape t5= new tetrimino(5);
        shape t6= new tetrimino(6);
        shape t7= new tetrimino(7);

        //now we create a list with all these tetriminos

        BANK.add(t1);
        BANK.add(t2);
        BANK.add(t3);
        BANK.add(t4);
        BANK.add(t5);
        BANK.add(t6);
        BANK.add(t7);
        
        //we change the starting X coordinate

        //WE DONT NEED THIS NO MORE
        /*
        for (int i = 0; i < BANK.size() ; i++){
            BANK.get(i).X = data.getInitialPosition((tetrimino)BANK.get(i));
        }
        * */
        
        return BANK;
    }

}
