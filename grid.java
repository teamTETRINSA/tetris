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

}
