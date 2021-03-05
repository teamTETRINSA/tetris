// ANCIEN MODELE
/**
 * to create shapes
 * by @josephblanchard
 * */

public class shapeXX{
    public int[][] tab ;
    
    /**
     * constructor
     * no parameter
     **/
    
    public shape(int c, int d){
        //boolean v=false;             // v says if a shape is valid or not ; method not used for now
        tab = new int[c][c];
        switch(d){
            case 1: //L1
            for (int i=0 ; i<c ; i++){
                tab[i][0]=1;
            }
            for (int i=0 ; i<c ; i++){
                tab[c-1][i]=1;
            }
            break;
            
            case 2: //L2
            for (int i=0 ; i<c ; i++){
                tab[i][c-1]=1;
            }
            for (int i=0 ; i<c ; i++){
                tab[c-1][i]=1;
            }
            break;
            
            //to continue
        }
        
        // creating random shapes
        /*
        while (v=false){
            for (int i=0 ; i<c ; i++){
                for (int j=0 ; j<c ; j++){
                    tab[i][j]=(int) (Math.random()*2);
                }
            }
            v=test(tab);
            //v=true;
        }
        * */
        
    }
    
    /**
     * return true if a shape is valid, false if not
     * */
    
    /*
    public boolean test (int[][] s){
        boolean b = true;

        for (int i=0 ; i<s.length ; i++){
            for (int j=0 ; j<s[1].length ; j++){
                if (s[i][j]==1){
                    if (s[i-1][j]==0 && s[i+1][j]==0 && s[i][j-1]==0 && s[i][j+1]==0){
                        i=3;
                        j=3;
                        b= false;
                    }
                }
            }
        }
        if (b=true){
            int count=0;
            for (int i=0 ; i<s.length ; i++){
                for (int j=0 ; j<s[1].length ; j++){
                    if (s[i][j]==0){
                        count+=1;
                    }
                }
            }
            if (count==9){
                b=false;
            }
        }
        return b;  
    }
    * */
    
    /**
     * toString method
     * */
    
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
