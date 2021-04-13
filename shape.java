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
        */
        return res;
    }
    
    /** 
     * STRING
     * for 4x4 int[][]
     * */
     
    public String prt (int[][] t){ 
        //System.out.println("**PRT**");
        String res ="";
        for(int i = 0; i < t.length; i++){
            for(int j = 0; j < t[1].length; j++){
                res+=tab[i][j]+" |"; // concatenation
            }
            res+="\n";// line break
        }
        
        return res;
        
    }
    
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    public void rotateTetrimino () {
		
		int [][] revTab = new int [this.tab.length][this.tab[0].length];
        System.out.println("original");
        System.out.println(this);
        
        //the lines are moved down
        
        for (int j = 0; j<revTab.length; j++){
			for (int i = 0; i<revTab[0].length; i++){
				revTab[i][j]=tab[tab.length-1-i][j];
			}
		}
		System.out.println("revTab");
		System.out.println(prt(revTab));
        
		
		//transpose of the matrix
		int [][] transTab = new int [this.tab.length][this.tab[0].length];
		for (int p = 0; p<transTab.length; p++){
			for (int q = 0; q<transTab[0].length; q++){
				transTab[p][q]=revTab[q][p];
			}
		}
		System.out.println("transTab");
		System.out.println(prt(transTab));
        
        tab=transTab;
        
    }
    
    
    
    /*******************
     * TO COMPLETE WITH OTHER COLORS
     * */
    // USELESS
    
    /*
    public int getIntegerForColor(){
        return 1;
    }
    * */
    
    /*******************
     * DESSINE
     * called by the GUI to print tetriminos T1 and T2
     * */
    
    public void dessine(Graphics g, grid data, int n){
		if (n==1){ //painting the current falling tetrimino T1 
			switch (data.areaO.length){
				case 20:         
					g.setColor(ColorTetrimino);
					for (int i=0; i < tab.length; i++){
						for (int j=0; j< tab[0].length ; j++){
							if (tab[i][j]!=0){
								g.fillRect (137+(X*(28+2))+j*(28+2), 123+(Y*(28+2))+i*(28+2), 28, 28);
							}
						}
					}
				break;
				
				case 24:
					g.setColor(ColorTetrimino);
					for (int i=0; i < tab.length ; i++){
						for (int j=0; j< tab[0].length  ; j++){
							if (tab[i][j]!=0){
								g.fillRect (137+(X*(23+2))+j*(23+2), 123+(Y*(23+2))+i*(23+2), 23, 23);
							}
						}
					}
				break;
				
				case 28:
					g.setColor(ColorTetrimino);
					for (int i=0; i < tab.length  ; i++){
						for (int j=0; j< tab[0].length ; j++){
							if (tab[i][j]!=0){
								g.fillRect (133+(X*(20+2))+j*(20+2), 112+(Y*(20+2))+i*(20+2), 20, 20);
							}
							
						}
					}
				break;
			}
		}else if (n==2){ //printing the next coming tetrimino
            int k = 0;
            if ((mainGame.RightSideEmptyColumns(data, 1, 2) == true) && mainGame.LeftSideEmptyColumns(data, 1, 2) == false){
                k=28;
            }
			for (int i=0; i < data.T2.tab.length; i++){
				for (int j=0; j< data.T2.tab[0].length ; j++){
					if (data.T2.tab[i][j]!=0){
						g.setColor(data.T2.ColorTetrimino);
						g.fillRect (600+k+j*(56+2), 483+i*(56+2), 56, 56);
					}/*else{
						g.setColor(Color.yellow);
						g.fillRect (600+k+j*(56+2), 483+i*(56+2), 56, 56);
					}*/
				}
			}
		}else if (n==3){
			for (int k = 0 ; k < data.ShapeBank.size() ; k++){
				for (int i=0; i < data.ShapeBank.get(k).tab.length; i++){
					for (int j=0; j< data.ShapeBank.get(k).tab[0].length ; j++){
						if (data.ShapeBank.get(k).tab[i][j]!=0){
							g.setColor(data.ShapeBank.get(k).ColorTetrimino);
							if (k>1){
								g.fillRect (20+j*(25+2), 70+k*100+i*(25+2), 25, 25);
							}else{
								g.fillRect (7+j*(25+2), 70+k*100+i*(25+2), 25, 25);
							}
						}
					}
				}
			}
		}
			
	}
		
	
    
}
