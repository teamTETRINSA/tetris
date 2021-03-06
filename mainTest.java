/**
 * different tests
 * main
 * create tetriminos
 * create a list of tetriminos
 * */

import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class mainTest{
  
	public static void main(String[] args){
        
        
        
        /**
         * Creating tetriminos
         * */
        
        tetriminoFilled t1= new tetriminoFilled(1);
        tetriminoFilled t2= new tetriminoFilled(2);
        tetriminoFilled t3= new tetriminoFilled(3);
        tetriminoFilled t4= new tetriminoFilled(4);
        tetriminoFilled t5= new tetriminoFilled(5);
        tetriminoFilled t6= new tetriminoFilled(6);
        tetriminoFilled t7= new tetriminoFilled(7);
        
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
        
        LinkedList<tetriminoFilled> list = new LinkedList<tetriminoFilled>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        
        /**
         * creating the grid
         * */
         
        grid G1 = new grid(20,10);
        grid G2 = new grid (20,10);
        //showGrid(G1,G2);
        System.out.println(G1);
        System.out.println(G2);
        
        GUI window = new GUI (1000,600,100,50);
        //GUI window = new GUI (listeCourbe,1000,600,100,50);
    }
    
    /**
     * for printing the 2 frids at the same time
     * */
    
    //does not work
    /*
    public void showGrid(grid A, grid B){
        System.out.println(A);
        System.out.println(B);
    }
    * */
    
        /**
         * dtermining the initial position of a tetrimino on the grid
         * */
        
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
         * 
         * */
         
        
        
        
        
        //printShape(a);
        
        /*
        for (int i=0 ; i<3 ; i++){
            for (int j=0 ; j<3 ; j++){
                System.out.print(a.tab[i][j]+"  ");
            }
            System.out.println();
        }
        * */
    
}
