/**
 * the listTetrimino class allows to create a LinkedList of diffrent tetriminos given as parameters
 * */

public class listTetrimino{
    LinkedList<tetrimino> list ;
    
    /**
     * Constructor
     * uses different tetriminos as parameters
     * */
     
    public listeTetrimino(tetrimino t1, tetrimino t2, tetrimino t3, tetrimino t4, tetrimino t5, tetrimino t6, tetrimino t7){
        list = new LinkedList<tetrimino>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
    }
}
