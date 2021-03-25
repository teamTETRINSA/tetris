import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class shape {
    
    public int[][] tab;
    public int X = 0;
    public int Y = 0;
    protected Color CouleurTetrimino;
    
    private final Color COULEUR_PAR_DEFAUT = Color.black; // la couleur par défaut est définie comme une constante
    
    /**
     * constructor
     * no parameter
     **/
    
    public shape () {
        
        Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
        
        // initialization of the coordinates 
        // this.X = x;
        // this.Y = y;
        
        // initialization of the color
		CouleurTetrimino = new Color(r, g, b);
		
		// initialization tab
		tab = new int [4][4];
	
	}
    
    /**
     * toString method
     * no parameter
     **/
    
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
        * */
        return res;
    }
    
    public void dessine(Graphics g){
        g.setColor(CouleurTetrimino);
        for (int i=0; i < tab.length ; i++){
            for (int j=0; j< tab[0].length ; j++){
                g.fillRect (i*15, j*15, 15, 15);
            }
        }
	}
}
