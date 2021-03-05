public class mainTest{
  
	public static void main(String[] args){
        shape a = new shape(5,1);
        gamearea A = new gamearea();
        System.out.println(a);
        System.out.println(A);
        
        int pa=a.tab.length/2; //milieu shape a
        int pA=A.area[1].length/2; //milieu gamearea A
        int d=pA-pa ;//position début ajout a dans A
        
        System.out.println("milieu shape a = "+pa);
        System.out.println("milieu gamearea A = "+pA);
        System.out.println("position début ajout a dans A = "+d);
        
        //printShape(a);
        
        /*
        for (int i=0 ; i<3 ; i++){
            for (int j=0 ; j<3 ; j++){
                System.out.print(a.tab[i][j]+"  ");
            }
            System.out.println();
        }
        * */
    }
    
    /**
     * print a shape as a table
     * */
    
    /*
    public void printShape (shape s){
        for (int i=0 ; i<3 ; i++){
            for (int j=0 ; j<3 ; j++){
                System.out.print(a.tab[i][j]+"  ");
            }
            System.out.println();
        }
    }
    * */
    
}
