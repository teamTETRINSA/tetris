public class grid{
    public int[][] area ;
    public grid(){
        area = new int[23][13];
    }
    public String toString (){ 
        StringBuilder temp = new StringBuilder("");
        for(int i = 0; i < area.length; i++){
            for(int j = 0; j < area[1].length; j++){
                temp.append("").append(area[i][j]).append("|");//je fais unne concatenation
            }
            temp.append("\n");// Retour a la ligne
        }
        return temp.toString();
    }

}
