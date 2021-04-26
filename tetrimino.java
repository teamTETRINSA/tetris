/**
 * TETRIMINO
 * it's a colored shape composed of different litlle squares
 * inheritor class of shape
 * 
 * a tetrimino is this little piece composed of squares that fall in a Tetris game
 * */
 
import java.awt.Graphics;
import java.awt.Color;

public class tetrimino extends shape{
	
	/** CONSTRUCTOR **/
    
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
    
    
    public String toString(){
		return super.toString();
    }
    
    
    
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    
    public void rotateTetrimino () {
		super.rotateTetrimino();
    }
    

	
    
    

}
