import java.util.Random;
import java.awt.Color;

public class tetrimino {
    
    public int[][] tab ;
    protected Color CouleurTetrimino;
    private final Color COULEUR_PAR_DEFAUT = Color.black; // La couleur par défaut est défini comme une constante
    
    /**
     * constructor
     * no parameter
     **/
    
    public tetrimino () {
        
        Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
        //Initialisation couleur
		CouleurTetrimino = new Color(r, g, b);
		//Initialisation tab
		tab = new int [2][4];
	
	}
    
    public String toString (){ 
        StringBuilder temp = new StringBuilder("");
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab[1].length; j++){
                temp.append("").append(tab[i][j]).append("|");//je fais unne concatenation
            }
            temp.append("\n");// Retour a la ligne
        }
        return temp.toString();
    }
}
