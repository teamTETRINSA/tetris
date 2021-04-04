import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class shape {
    
    public int[][] tab ;
    public int X = 0;
    public int Y = 0;
    protected Color ColorTetrimino;
    
    private final Color COULEUR_PAR_DEFAUT = Color.black; // La couleur par défaut est défini comme une constante
    
    /**
     * constructor
     * no parameter
     **/
    
    public shape () {
        
        Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
        
        /*
        //creating an ArrayList of colors
        ArrayList<Color> AvailableColors = new ArrayList<Color> (Color.green, Color.blue, Color.red);
        
        //Initialisation couleur
        int nb = (int)(Math.random()*4);
		CouleurTetrimino = AvailableColors.get(nb);
        * */
        ColorTetrimino = Color.blue;
		
		//Initialisation tab
		tab = new int [4][4];
	
	}
    
    /**
     * toString method
     * no parameter
     **/
    
    /*
    public String toString (){ 
        String res ="";
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab[1].length; j++){
                res+=tab[i][j]+" |"; // concatenation
            }
            res+="\n";// line break
        }
        /*
        StringBuilder temp = new StringBuilder(""); //StringBuilder allows us to use a toString method able to print something on different lines, usefull to print matrices
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab[1].length; j++){
                temp.append("").append(tab[i][j]).append("|"); // concatenation
            }
            temp.append("\n");// line break
        }
        return temp.toString();
        *
        return res;
    }**/
    
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    public void rotateTetrimino () {
		
		int [][] revTab = new int [this.tab.length][this.tab[0].length];
        
        //the lines are moved down
        for (int j = 0; j<revTab.length; j++){
			for (int i = 0; i<revTab[0].length; i++){
				revTab[i][j]=this.tab[this.tab.length-1-i][j];
			}
		}
		
		//transpose of the matrix
		int [][] transTab = new int [this.tab.length][this.tab[0].length];
		for (int p = 0; p<transTab.length; p++){
			for (int q = 0; q<transTab[0].length; q++){
				transTab[p][q]=revTab[q][p];
			}
		}
        
        this.tab=transTab;
    }
    
    /*******************
     * TO COMPLETE WITH OTHER COLORS
     * */
    
    public int getIntegerForColor(){
        return 1;
    }
    
    public void dessine(Graphics g){
        for (int i=0; i < tab.length ; i++){
            for (int j=0; j< tab[0].length ; j++){
                if (tab[i][j]!=0){
                    g.setColor(ColorTetrimino);
                    g.fillRect (30+Y*15+j*15, 80+X*15+i*15, 15, 15);
                }//else{
                    //g.setColor(Color.red);
                    //g.fillRect (30+Y*15+j*15, 80+X*15+i*15, 15, 15);
                //}   
            }
        }
	}
    
}
