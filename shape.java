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
    
    /** CONSTRUCTOR **/
    
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
        ColorTetrimino = new Color(r,g,b);
		
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
    
    public void dessine(Graphics g, grid data, int n){
		if (n==1){ //painting the current falling tetrimino T1 
			switch (data.areaO.length){
				case 20:         
					g.setColor(data.T1.ColorTetrimino);
					for (int i=0; i < data.T1.tab.length; i++){
						for (int j=0; j< data.T1.tab[0].length ; j++){
							if (data.T1.tab[i][j]!=0){
								g.fillRect (170+(data.T1.X*(24+2))+j*(24+2), 50+(data.T1.Y*(24+2))+i*(24+2), 24, 24);
							}
						}
					}
				break;
				
				case 24:
					g.setColor(data.T1.ColorTetrimino);
					for (int i=0; i < data.T1.tab.length ; i++){
						for (int j=0; j< data.T1.tab[0].length  ; j++){
							if (data.T1.tab[i][j]!=0){
								g.fillRect (170+(data.T1.X*(20+2))+j*(20+2), 50+(data.T1.Y*(20+2))+i*(20+2), 20, 20);
							}
						}
					}
				break;
				
				case 28:
					g.setColor(data.T1.ColorTetrimino);
					for (int i=0; i < data.T1.tab.length  ; i++){
						for (int j=0; j< data.T1.tab[0].length ; j++){
							if (data.T1.tab[i][j]!=0){
								g.fillRect (170+(data.T1.X*(17+2))+j*(17+2), 50+(data.T1.Y*(17+2))+i*(20+2), 17, 17);
							}
							
						}
					}
				break;
			}
		}else if (n==2){ //printing the next coming tetrimino
			for (int i=0; i < data.T2.tab.length; i++){
				for (int j=0; j< data.T2.tab[0].length ; j++){
					if (data.T2.tab[i][j]!=0){
						g.setColor(data.T1.ColorTetrimino);
						g.fillRect (565+j*(24+2), 143+i*(24+2), 24, 24);
					}
				}
			}
		}
	}
		
	
    
}
