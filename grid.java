/**
 * GRID CLASS
 * ...
 * * */

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;

public class grid{
	
    public int[][] area = new int[20][10];
    
    //public Color[][] areaC = new Color[20][10];
    
    public Object[][] areaO = new Object[20][10];
    
    
    
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
    
    public int speedLevel=800;         // take the following values : 800 (level 1) - 600 (level 2) - 400 (level 3)
    
    public shape T1 ;           //the tetrimino falling on the grid
    
    public shape T2 ;           // the next tetrimino coming ; it is printed on the side of the game
    
    public boolean soundOn =true ;
    
    /** CONSTRUCTOR **/
    
    public grid(){
        int nb = (int)(Math.random()*7);
        T1 = new tetrimino(nb);
        nb = (int)(Math.random()*7);
        T2 = new tetrimino(nb);
    }
    
    /*
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
    * */
    
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
    
    /*
    public boolean odd (int i){
        boolean o = true;
        if (i%2 !=0){
            o = false;
        }
        return o;
    }
    * */
    
   
    /**
     * GETCELL
     * Returns the content of one cell of the grid
     * */
     
    //not used no more
    
    
    public Object getCell(int x, int y) {
        return areaO[x][y];
        
	}
	
    
    
    public void dessine(Graphics g){
        switch (areaO.length){
            case 20:
                //g.setColor(Color.black);
                for (int i=0; i < 10 ; i++){
                    for (int j=0; j< 20 ; j++){
                        if (areaO[j][i] != null){
							g.setColor((Color) areaO[j][i]);
                            g.fillRect (138+i*(28+2), 110+j*(28+2), 28, 28);
                        }else{
							g.setColor(Color.cyan);
                            g.fillRect (138+i*(28+2), 110+j*(28+2), 28, 28);
						}
                    }
                }
            break;
            
            case 24:
                //g.setColor(Color.black);
                for (int i=0; i < 12 ; i++){
                    for (int j=0; j< 24 ; j++){
                        if (areaO[j][i] != null){
							g.setColor((Color) areaO[j][i]);
                            g.fillRect (131+i*(23+2), 110+j*(23+2), 23, 23);
                        }else{
							g.setColor(Color.cyan);
                            g.fillRect (131+i*(23+2), 110+j*(23+2), 23, 23);
						}
                    }
                }
            break;
            
            case 28:
                //g.setColor(Color.black);
                for (int i=0; i < 14 ; i++){
                    for (int j=0; j< 28 ; j++){
                        if (areaO[j][i] != null){
							g.setColor((Color) areaO[j][i]);
                            g.fillRect (133+i*(20+2), 110+j*(20+2), 20, 20);
                        }else{
							g.setColor(Color.cyan);
                            g.fillRect (133+i*(20+2), 110+j*(20+2), 20, 20);
						}
                    }
                }
            break;
        }
	}
	
    
    public int getInitialPosition(tetrimino T){
        int ct=T.tab.length/2; // coordinate of the horizontal center of tetrimino t
        int cg=(areaO[0].length)/2; //coordinate of the horizontal center of grid G
        T.X = cg-ct ;// the initial position X of tetrimino t
        return T.X;
        
		
        // détermination de la position d'insertion de la forme au début de la grille de jeu
        /*
        System.out.println("milieu shape a = "+ct);
        System.out.println("milieu gamearea A = "+cg);
        System.out.println("position début ajout a dans A = "+ (cg-ct));
        * */
    }
    
    public void initialiseData(){
        // first we empty the area
        for (int i=0; i < areaO.length ; i++){
            for (int j=0; j< areaO[0].length ; j++){
                areaO[i][j]=null;
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
        T1.Y += dy;
        System.out.println("Y = "+T1.Y);
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
}
