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
     
    //to re-check after the main modifications
        
    boolean restart=true;// variable to know if we want to restart the game
    boolean start;
    int mode = 2;   /* player mode 2  by default
                            * need now to add a reaction to the button mode player
                        * */
    boolean go = false; // says if the game is over or not
    int lap = 0; // cont the total number of tetriminos placed in the area
    int score1 = 0; // score of player 1 (on left)
    int score2 = 0; // score of player 2 (on right)
    ArrayList<tetrimino> TetriminosList;
    grid G1;
    grid G2;
	
	int nb = 0;
	tetrimino form1 = new tetrimino();
	tetrimino form2 = new tetrimino();
	ArrayList<tetrimino> list = new ArrayList<tetrimino> (ShapeBank()) ;
	
	    /**
        * creating the grids
        * */
        
    //grid G1 = new grid(20,10);
    //grid G2 = new grid(20,10);
    //System.out.println(G1);
    //System.out.println(G2);
  
	public static void main(String[] args) {
        
        /**
        * creating the GUI
        * */
        
        //GUI window = new GUI (list, 861,600,100,50, G1, G2);
        
    }
    
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
		form1 = list.get(nb);
		return form1;
	}
    
}
