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
    
    /*public void transformShape(){
        for (int i=0 ; i<area.length ; i++){
            for (int j=0 ; j<area[0].length ; j++){
                if (odd(area[i][j])){ // the method odd (int i) returns true if the integer i is odd
                    switch (area[i][j]){
                        case 1 :
                        area[i][j] = 2;
                        break;
                        
                        case 3 :
                        area[i][j] = 4;
                        break;
                        
                        case 5 :
                        area[i][j] = 6;
                        break;
                        
                        case 7 :
                        area[i][j] = 8;
                        break;
                        
                        case 9 :
                        area[i][j] = 10;
                        break;
                        
                        case 11 :
                        area[i][j] = 12;
                        break;
                        
                        case 13 :
                        area[i][j] = 14;
                        break;
                    }
                }
            }
        }
    }*/
    
    public boolean odd (int i){
        boolean o = true;
        if (i%2 !=0){
            o = false;
        }
        return o;
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
