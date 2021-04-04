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
            this.tab[1][0]=1;
            this.tab[1][1]=1;
            this.tab[1][2]=1;
            this.tab[1][3]=1;
            break;
            
            
            case 2: // ::
            this.tab[1][1]=2;
            this.tab[1][2]=2;
            this.tab[2][1]=2;
            this.tab[2][2]=2;
            break;
            
            case 3: // .:.
            for (int i =0 ; i<this.tab[0].length-1 ; i++){
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
    
    /*
    public String toString(){
        StringBuilder temp = new StringBuilder("");
        temp.append("Shape "+this.tab[1][1]+" :");
        temp.append("\n");
        temp.append(super.toString());
        return temp.toString();
    }
    * */
    
    
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    /*
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
    * */
    
    
    
    /*
    public void dessine(Graphics g){
        super.paint(g);
        
        g.setColor(ColorTetrimino);
        for (int i=0; i < tab.length ; i++){
            for (int j=0; j< tab[0].length ; j++){
                if (tab[i][j]!=0){
                    g.fillRect (30+Y*15+j*15, 70+X*15+i*15, 15, 15);
                }
            }
        }
        
	}
    * */
    

}
