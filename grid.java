/**
 * GRID CLASS
 * ...
 * * */

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.ArrayList;

public class grid{
	
    // not used no more
    //public int[][] area = new int[20][10];
    
    public Object[][] areaO = new Object[20][10]; //Object type allows to store int ad colors in the same array
    
    
    
    public boolean restart = false;   // variable to know if we want to restart the game
    
    public boolean start = true;          //variaale to know if we print another shape on the grid
    
    public boolean initialPhase = true;  // variable used in the tetrisGUI-2 classes to know if we are in the initial phase of the game (ther player is choosing
    
    public boolean pause = false ;
    
    public boolean fallen ;        // variable to know the tetrimino t1 fell
    
    public int score = 0;          // score of the player
    
    public int bestScore = 20;      // to save the best score

    public int formerBestScore = 0;
    
    /** public boolean go = false; // says if the game is over or not      :      START varibale ?????      **/
    
    public int lap = 0;            // count the total number of tetriminos placed in the area
    
    public int interval = 1000;    //the time interval at which a tetrimino is dropped   
    
    public int speedLevel=800;         // take the following values : 800 (level 1) - 600 (level 2) - 400 (level 3)
    
    public shape T1 ;           //the tetrimino falling on the grid
    
    public shape T2 ;           // the next tetrimino coming ; it is printed on the side of the game
    
    public boolean soundOn = true ;
    
    public ArrayList<shape> ShapeBank ; // bank of shapes to store tetriminos
    
    public Color ColorArea = new Color(210,210,210); // the color for the area background on the GUI
    
    /** CONSTRUCTOR **/
    
    public grid(){
        int nb = (int)(Math.random()*7);
        T1 = new tetrimino(nb);
        
        nb = (int)(Math.random()*7);
        T2 = new tetrimino(nb);
        getInitialPosition();
    }
    
    /**
     * TRANSFORMSHAPE
     * transform a shape that end it's fall (seen as even numbers)
     * to a fixed shape (seen as odd numbers ≠ 0)
     * */
    
    public void transformShape(){
        for (int i = 0; i<T1.tab.length; i++){
            for (int j = 0; j<T1.tab[0].length ; j++){
                if (T1.tab[i][j]!=0){
                    //areaO[T1.Y+i][T1.X+j]=T1.getIntegerForColor();
                    areaO[T1.Y+i][T1.X+j]=T1.ColorTetrimino;
                }
            }
        }
    }
    
    /**
     * MAKEFALLGRID
     * Make fall the part above an empty line (previously entirely filled)
     * */
    
    public void makeFallGrid(int l){
        for (int i=l ; i>0 ; i--){
            for (int k=0 ; k<areaO[0].length ; k++){
                areaO[l][k]=areaO[l-1][k];
            }
        }
        System.out.println("          > jump "); 
    }
    
    
    public void dessine(Graphics g){
        switch (areaO.length){
            case 20:
                for (int i=0; i < 10 ; i++){
                    for (int j=0; j< 20 ; j++){
                        if (areaO[j][i] != null){
							g.setColor((Color) areaO[j][i]);
                            g.fillRect (137+i*(28+2), 123+j*(28+2), 28, 28);
                        }else{
							g.setColor(ColorArea);
                            g.fillRect (137+i*(28+2), 123+j*(28+2), 28, 28);
						}
                    }
                }
            break;
            
            case 24:
                for (int i=0; i < 12 ; i++){
                    for (int j=0; j< 24 ; j++){
                        if (areaO[j][i] != null){
							g.setColor((Color) areaO[j][i]);
                            g.fillRect (137+i*(23+2), 123+j*(23+2), 23, 23);
                        }else{
							g.setColor(ColorArea);
                            g.fillRect (137+i*(23+2), 123+j*(23+2), 23, 23);
						}
                    }
                }
            break;
            
            case 28:
                for (int i=0; i < 14 ; i++){
                    for (int j=0; j< 28 ; j++){
                        if (areaO[j][i] != null){
							g.setColor((Color) areaO[j][i]);
                            g.fillRect (133+i*(20+2), 112+j*(20+2), 20, 20);
                        }else{
							g.setColor(ColorArea);
                            g.fillRect (133+i*(20+2), 112+j*(20+2), 20, 20);
						}
                    }
                }
            break;
        }
	}
	
    /**
     * to set the position of a tetrimino at the center of the area
     * as a function of the size of the area
     * */
    
    public void getInitialPosition(){
        int ct=T1.tab.length/2; // coordinate of the horizontal center of tetrimino t
        int cg=(areaO[0].length)/2; //coordinate of the horizontal center of grid G
        T1.X = cg-ct;
        T2.X = cg-ct;// the initial position X of tetrimino t
    }
    
    /**
     * we empty the area before restarting a new game
     * */
    
    public void initialiseData(){
        for (int i=0; i < areaO.length ; i++){
            for (int j=0; j< areaO[0].length ; j++){
                this.areaO[i][j]=null;
            }
        }
    }
    
    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY 
     * -
     * used from the main
     * this method will not be directly called from the GUI keyListneer methods as 
     * we need first to check the possibility to drop the current 
     * falling tetrimino in the mainGame class and then use this method
     * */
     
    public void dropTetrimino(int dy) {
        if (dropIsPossible()==true){
			T1.Y += dy;
			System.out.println("Y = "+T1.Y);
		}
	}
    
    /**
     * DROPSIPOSSIBLE
     * Returns true if the boxes (of the grid) under all colored boxes of a tetrimino are empty
     * */
    
    public boolean dropIsPossible(){
        boolean possible = true ;
        if (T1.Y < areaO.length -4){
            for (int i = 0; i< T1.tab.length ; i++){
                for (int j=0 ; j< T1.tab[0].length ; j++){
                    if ((T1.tab[i][j] != 0) && (areaO[T1.Y+i+1][T1.X+j] != null)){
                        possible = false ;
                        i = T1.tab.length;
                        j = T1.tab[0].length;
                    }
                }
            }
        } else if ((T1.Y == areaO.length -4) && (ShapeCountEmptyLines(1)==true)){
            for (int i = 0; i< T1.tab.length ; i++){
                for (int j=0 ; j< T1.tab[0].length ; j++){
                    if ((T1.tab[i][j] != 0) && (areaO[T1.Y+i+1][T1.X+j] != null)){
                        possible = false ;
                        i = T1.tab.length;
                        j = T1.tab[0].length;
                    }
                }
            }
        } else if ((T1.Y == areaO.length -3) && (ShapeCountEmptyLines(2)==true)){
            for (int i = 0; i< T1.tab.length ; i++){
                for (int j=0 ; j< T1.tab[0].length ; j++){
                    if ((T1.tab[i][j] != 0) && (areaO[T1.Y+i+1][T1.X+j] != null)){
                        possible = false ;
                        i = T1.tab.length;
                        j = T1.tab[0].length;
                    }
                }
            }
        } else {
            possible = false ;
        }
        return possible;
    }
    
    /**
     * SHAPECOUNTEMPTYLINES
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    
    public boolean ShapeCountEmptyLines(int n){
        boolean ok = true ;
        switch (n){
            case 1:
                for (int j=0 ; j< T1.tab[0].length ; j++){
                    if (T1.tab[T1.tab.length-1][j]!=0){
                        ok=false;
                    }
                }
            break;
            
            case 2:
                for (int i = T1.tab.length-1; i>1 ; i--){
                    for (int j=0 ; j< T1.tab[0].length ; j++){
                        if (T1.tab[i][j]!=0){
                            ok=false;
                        }
                    }
                }
            break;
        }
        return ok;
    }
    
    /**
     * MOVETETRIMINO
     * Translation of the tetrimino of a coordinate dX 
     * */
     
    public void moveTetrimino(int dx) {
			T1.X += dx;
	}
    
    /**
     * PAUSETHEGAME
     * */
    
    public void pauseTheGame (){
        if (pause==false){
            pause=true;
            System.out.println("********************************* PAUSE THE GAME >>> PLAY");
        }else{
            pause=false;
            System.out.println("********************************* PAUSE THE GAME >>> PAUSE");
        }
        
    }
    
    public void selectAreaSize (int h, int w){
        areaO = new Object[h][w];
    }
    
    public void selectSpeed (int s){
        speedLevel = s ;
    }
    
    /**
     * MOVETORIGHTISPOSSIBLE
     * Returns true if the boxes (of the grid) on the right side of all colored boxes of a tetrimino are empty
     * */

    public boolean moveToRightIsPossible(){
        boolean possible = true ;
        if (T1.X < areaO[0].length -4){
            for (int i = 0; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i+1] != null)){
                        possible = false ;
                    }
                }
            }
        }else if ((T1.X == areaO[0].length -4) && (RightSideEmptyColumns(1,1)==true)){
            for (int i = 0; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i+1] != null)){
                        possible = false ;
                    }
                }
            }
        }else if ((T1.X == areaO[0].length -3) && (RightSideEmptyColumns(2,1)==true)){
            for (int i = 0; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i+1] != null)){
                        possible = false ;
                    }
                }
            }
        }else if(T1.X == -2){
            for (int i = 1; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i+1] != null)){
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
    
    public boolean moveToLeftIsPossible(){
        boolean possible = true ;
        if (T1.X > 0){
            for (int i = 0; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i-1] != null)){
                        possible = false ;
                    }
                }
            }
        
        }else if ((T1.X == 0) && (LeftSideEmptyColumns(1,1)==true)){    /* and if the first column of the tetrimino's tab is empty,
                                                                                 * we can analyse the x-1 column of the grid's area when a 
                                                                                 * box of the tetrimino's tab is filled bu a number different than 0
                                                                                 * */
            for (int i = 0; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i-1] != null)){
                        possible = false ;
                    }
                }
            }
        }else if((T1.X == -1) && (LeftSideEmptyColumns(2,1)==true)){    /* and if the 2 first columns of the tetrimino's tab are empty,
                                                                                 * we can analyse the x-1 column of the grid's area when a 
                                                                                 * box of the tetrimino's tab is filled by a number different than 0
                                                                                 * */
            for (int i = 0; i< T1.tab[0].length ; i++){
                for (int j=0 ; j< T1.tab.length ; j++){
                    if ((T1.tab[j][i]!=0) && (areaO[T1.Y+j][T1.X+i+1] != null)){
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
     * RIGHTSIDEEMPTYCOLUMNS
     * return true if the n last lines of the tab of a shape are empty
     * works if n = 1 or 2
     * */
    
    public boolean RightSideEmptyColumns(int n, int tet_1_or_2){
        boolean ok = true ;
        if (tet_1_or_2 == 2){
            for (int j=0 ; j< T2.tab.length ; j++){
                if (T2.tab[j][T2.tab[0].length-1]!=0){
                    ok=false;
                }
            }
        }else{
            switch (n){
                case 1:
                    for (int j=0 ; j< T1.tab.length ; j++){
                        if (T1.tab[j][T1.tab[0].length-1]!=0){
                            ok=false;
                        }
                    }
                break;
                
                case 2:
                    for (int i = T1.tab[0].length-2; i < T1.tab[0].length ; i++){
                        for (int j=0 ; j< T1.tab.length ; j++){
                            if (T1.tab[j][i]!=0){
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
    
    public boolean LeftSideEmptyColumns(int n, int tet_1_or_2){
        boolean ok = true ;
        if (tet_1_or_2 == 2){ // which tetrimino are we working on
            for (int j=0 ; j< T2.tab.length ; j++){
                if (T2.tab[j][0]!=0){
                    ok=false;
                }
            }
        }else{
            switch (n){ // how many columns do we want to check
                case 1:
                    for (int j=0 ; j< T1.tab.length ; j++){
                        if (T1.tab[j][0]!=0){
                            ok=false;
                        }
                    }
                break;
                
                case 2:
                    for (int i = 0; i < 2 ; i++){
                        for (int j=0 ; j< T1.tab.length ; j++){
                            if (T1.tab[j][i]!=0){
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
     * CHECKPOTENTIALERRORATBORDER
     * after the rotation of a tetrimino, we take care that 
     * the roation method doesn't put colored boxes out of the area
     * */
    
    public void checkPotentialErrorAtBorder(){
        if ((T1.X == areaO[0].length -2) && (RightSideEmptyColumns(2,1)==false)){ // if the tetrimino has 2 columns outside the area on the right side, we check if they are still empty after the rotation
            T1.X+=-1;
            if ((T1.X == areaO[0].length -3) && (RightSideEmptyColumns(1,1)==false)){ //here we have to re-check the condition for 1 colum
                T1.X+=-1;
            }
        }else if ((T1.X == areaO[0].length -3) && (RightSideEmptyColumns(1,1)==false)){ // if the tetrimino has 1 columns outside the area on the right side, same verification...
            T1.X+=-1;
        }else if((T1.X == -1) && (LeftSideEmptyColumns(1,1)==false)){ // if the tetrimino has 1 columns outside the area on the left side, same verification...
            T1.X+=1;
        }else if((T1.X == -2) && (LeftSideEmptyColumns(2,1)==false)){ // if the tetrimino has 2 columns outside the area on the left side, same verification...
            T1.X+=1;
            if((T1.X == -1) && (LeftSideEmptyColumns(1,1)==false)){ //here we have to re-check the condition for 1 colum
                T1.X+=1;
            }
        }
    }
}
