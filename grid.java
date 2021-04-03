/**
 * GRID CLASS
 * ...
 * * */

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class grid{
	
    public int[][] area ;
    
    public boolean restart = false;   // variable to know if we want to restart the game
    
    public boolean start = true;          //variaale to know if we print another shape on the grid
    
    public boolean initialPhase = true;  // variable used in the tetrisGUI-2 classes to know if we are in the initial phase of the game (ther player is choosing
    
    public boolean pause = false ;
    
    public boolean fallen ;        // variable to know the tetrimino t1 fell
    
    public int score = 0;          // score of the player
    
    public int bestScore = 0;      // to save the best score
    
    /** public boolean go = false; // says if the game is over or not      :      START varibale ?????      **/
    
    public int lap = 0;            // count the total number of tetriminos placed in the area
    
    public int interval = 1000;    //the time interval at which a tetrimino is dropped   
    
    public int speedLevel;         // take the following values : 800 (level 1) - 600 (level 2) - 400 (level 3)
    
    public tetrimino T1;           //the tetrimino falling on the grid
    
    public tetrimino T2;           // the next tetrimino coming ; it is printed on the side of the game
    
    /**
     * CONSTRUCTOR
     * */
    
    public grid(){
    }
    
    public String toString (){ 
        StringBuilder temp = new StringBuilder("");
        temp.append("Grid "+this.area.length+"x"+this.area[0].length+" :");
        temp.append("\n");
        for(int i = 0; i < area.length; i++){
            for(int j = 0; j < area[1].length; j++){
                temp.append("").append(area[i][j]).append("|");//je fais unne concatenation
            }
            temp.append("\n");// Retour a la ligne
        }
        return temp.toString();
    }
    
    /**
     * transform a shape that end it's fall (seen as even numbers)
     * to a fixed shape (seen as odd numbers ≠ 0)
     * */
    
    public void transformShape(){
        for (int i = 0; i<T1.tab.length; i++){
            for (int j = 0; j<T1.tab[0].length ; j++){
                area[T1.Y+i][T1.X+j]=T1.getIntegerForColor();
            }
        }
    }
    
    public void makeFallGrid(){
        for (int i=area.length-1 ; i>=0 ; i--){
            boolean lineEmpty = true;
            for (int j=0 ; j<area[0].length ; j++){
                if (area[i][j]!=0){
                    lineEmpty = false;
                    j=-1;
                }
            }
            if (lineEmpty==true){
                for (int k=0 ; k<area[0].length ; k++){
                    area[i][k]=area[i-1][k];
                }
            }
        }
    }
    
    public boolean odd (int i){
        boolean o = true;
        if (i%2 !=0){
            o = false;
        }
        return o;
    }
    
   
    /*
    public int getCell(int x, int y) {
        return (area[x][y]);
	}
    * */
    
    
    public void dessine(Graphics g){
        g.setColor(Color.black);
        for (int i=0; i < area.length ; i++){
            for (int j=0; j< area[0].length ; j++){
                if (area[i][j] != 0){
                    g.fillRect (30+i*15, 70+j*15, 15, 15);
                }
            }
        }
	}
    
    public void getInitialPosition(){
        int ct=T1.tab.length/2; // coordinate of the horizontal center of tetrimino t
        int cg=(area[0].length)/2; //coordinate of the horizontal center of grid G
        T1.X = cg-ct ;// the initial position X of tetrimino t
        
		
        // détermination de la position d'insertion de la forme au début de la grille de jeu
        System.out.println("milieu shape a = "+ct);
        System.out.println("milieu gamearea A = "+cg);
        System.out.println("position début ajout a dans A = "+ (cg-ct));
    }
    
    public void initialiseData(){
        // first we empty the area
        for (int i=0; i < area.length ; i++){
            for (int j=0; j< area[0].length ; j++){
                area[i][j]=0;
            }
        }
    }
    
    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY 
     * */
     
    public void dropTetrimino(int dy) {
        T1.Y += dy;
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
        }else{
            pause=false;
        }
    }
    
    public void selectAreaSize (int h, int w){
        area = new int[h][w];
    }
    
    public void selectSpeed (int s){
        speedLevel = s ;
    }
}
