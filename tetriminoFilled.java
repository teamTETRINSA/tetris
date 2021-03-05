public class tetriminoFilled extends tetrimino{
    /**
     * constructor
     * */
    
    public tetriminoFilled(int n){
        super();
        switch (n){
            case 1: // ....
            for (int i =0 ; i<this.tab[0].length ; i++){
                this.tab[0][i]=1;
            }
            break;
            
            case 2: // ::
            this.tab[0][0]=2;
            this.tab[0][1]=2;
            this.tab[1][0]=2;
            this.tab[1][1]=2;
            break;
            
            case 3: // .:.
            for (int i =0 ; i<this.tab[0].length ; i++){
                this.tab[1][i]=3;
            }
            this.tab[1][1]=3;
            break;
            
            case 4: // ':.
            this.tab[0][0]=4;
            this.tab[0][1]=4;
            this.tab[1][1]=4;
            this.tab[1][2]=4;
            break;
            
            case 5: // .:'
            this.tab[1][0]=5;
            this.tab[0][1]=5;
            this.tab[1][1]=5;
            this.tab[0][2]=5;
            break;
            
            case 6: // :..
            for (int i =0 ; i<this.tab[0].length-1 ; i++){
                this.tab[1][i]=6;
            }
            this.tab[0][0]=6;
            break;
            
            case 7: // ..:
            for (int i =0 ; i<this.tab[0].length-1 ; i++){
                this.tab[1][i]=7;
            }
            this.tab[0][2]=7;
            break;
        }
    }
    
    /**
     * toString method
     * no parameter
     **/
    
    public String toString(){
        StringBuilder temp = new StringBuilder("");
        temp.append(super.toString());
        return temp.toString();
    }

}
