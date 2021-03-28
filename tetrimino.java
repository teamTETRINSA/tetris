/**
 * it's a colored shape composed of different litlle squares
 * */
 
import java.awt.Graphics;
import java.awt.Color;

public class tetrimino extends shape{
    /**
     * constructor
     * */
    
    public tetrimino(int n){
        super();
        switch (n){
            case 1: // ....
            for (int i =0 ; i<this.tab[0].length ; i++){
                this.tab[1][i]=1;
            }
            break;
            
            case 2: // ::
            this.tab[1][1]=2;
            this.tab[1][2]=2;
            this.tab[2][1]=2;
            this.tab[2][2]=2;
            break;
            
            case 3: // .:.
            for (int i =0 ; i<this.tab[0].length ; i++){
                this.tab[2][i]=3;
            }
            this.tab[1][1]=3;
            break;
            
            case 4: // ':.
            this.tab[1][0]=4;
            this.tab[1][1]=4;
            this.tab[2][1]=4;
            this.tab[2][2]=4;
            break;
            
            case 5: // .:'
            this.tab[2][0]=5;
            this.tab[1][1]=5;
            this.tab[2][1]=5;
            this.tab[1][2]=5;
            break;
            
            case 6: // :..
            for (int i =0 ; i<this.tab[0].length-1 ; i++){
                this.tab[2][i]=6;
            }
            this.tab[1][0]=6;
            break;
            
            case 7: // ..:
            for (int i =0 ; i<this.tab[0].length-1 ; i++){
                this.tab[2][i]=7;
            }
            this.tab[1][2]=7;
            break;
        }
    }
    
    /**
     * toString method
     * no parameter
     **/
    
    public String toString(){
        StringBuilder temp = new StringBuilder("");
        temp.append("Shape "+this.tab[1][1]+" :");
        temp.append("\n");
        temp.append(super.toString());
        return temp.toString();
    }
    
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
    
    /**
     * MOVETETRIMINO
     * Translation of the tetrimino of a coordinate dX 
     * */
     
    public void moveTetrimino(int dx) {
			this.X += dx;
	}
    
    /**
     * DROPTETRIMINO
     * Drop the tetrimino of a coordinate dY 
     * */
     
    public void dropTetrimino(int dy) {
			this.Y += -dy;
	}
    
    /**
     * GETINITIALPOSITION
     * method to get the initial position of a tetrimino on the grid
     * we place the top left hand corner of the tetrimino tab
     * */
    
    
    public void getInitialPosition(grid g){
        int ct=tab.length/2; // coordinate of the horizontal center of tetrimino t
        int cg=g.area[0].length/2; //coordinate of the horizontal center of grid G
        X = cg-ct ;// the initial position X of tetrimino t
        
		
        // détermination de la position d'insertion de la forme au début de la grille de jeu
        System.out.println("milieu shape a = "+ct);
        System.out.println("milieu gamearea A = "+cg);
        System.out.println("position début ajout a dans A = "+ (cg-ct));
    }
    
    public void dessine(Graphics g){
        g.setColor(ColorTetrimino);
        for (int i=0; i < tab.length ; i++){
            for (int j=0; j< tab[0].length ; j++){
                g.fillRect (Y*15+i*15, X*15+j*15, 15, 15);
            }
        }
	}
    
    /*******************
     * TO COMPLETE WITH OTHER COLORS
     * */
    
    public int getIntegerForColor(){
        return 1;
    }
        

}
