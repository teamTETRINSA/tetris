/**
 * it's a colored shape composed of different litlle squares
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
		/*
        StringBuilder temp = new StringBuilder("");
        temp.append("Shape "+this.tab[1][1]+" :");
        temp.append("\n");
        temp.append(super.toString());
        return temp.toString();
        * */
    }
    
    
    
    /**
     * ROTATETRIMINO
     * Rotation to the right 
     * */
    
    
    public void rotateTetrimino () {
		super.rotateTetrimino();
		
		int [][] revTab = new int [this.tab.length][this.tab[0].length];
        
        System.out.println(this);
        
        //the lines are moved down
        for (int j = 0; j<revTab.length; j++){
			for (int i = 0; i<revTab[0].length; i++){
				revTab[i][j]=this.tab[this.tab.length-1-i][j];
			}
		}
		
		System.out.println(prt(revTab));
		
		//transpose of the matrix
		int [][] transTab = new int [this.tab.length][this.tab[0].length];
		for (int p = 0; p<transTab.length; p++){
			for (int q = 0; q<transTab[0].length; q++){
				transTab[p][q]=revTab[q][p];
			}
		}
		
		System.out.println(prt(transTab));
        
        tab=transTab;
		
        
    }
    
    
    /**
     * DESSINE
     * use by the paint(() method is our GUi to paint 
     * */
     
     //USELESS
    
    
    public void dessine(Graphics g, grid data, int n){
        super.dessine(g,data,n);
        
        /*if (n==1){ //painting the current falling tetrimino T1 
			switch (data.area.length){
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
		* */
	}
	
    
    

}
