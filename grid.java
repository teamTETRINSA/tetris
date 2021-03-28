/**
 * GRID CLASS
 * ...
 * * */

import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class grid{
	
    public int[][] area ;
    
    public grid(int h, int l){
        area = new int[h][l];
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
    
    public void transformShape(tetrimino T1){
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
    
   
    
    public int getCell(int x, int y) {
        return (area[x][y]);
	}
    
    
    public void dessine(Graphics g){
        g.setColor(Color.black);
        for (int i=0; i < area.length ; i++){
            for (int j=0; j< area[0].length ; j++){
                if (area[i][j] != 0){
                    g.fillRect (20+i*15, 20+j*15, 15, 15);
                }
            }
        }
	}
    
}
