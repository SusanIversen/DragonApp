package nz.co.karodata.model;

/**
 * Created by Susan on 23/02/2017.
 */
public class DragonBoat {
    public static final int NUM_ROWS = 10;
    public static final int NUM_SIDES = 2;
    public static final int LEFT_ROW = 0;
    public static final int RIGHT_ROW = 1;

    public Paddler[][] paddlers = new Paddler[NUM_SIDES][NUM_ROWS];

    public DragonBoat() {
    //Empty Constructor
    }

    public Paddler addPaddler(int column, int row, Paddler paddler){
        if(row >= 0 && row < NUM_ROWS && column >= 0 && column < NUM_SIDES){
            Paddler tmp = paddlers[column][row];
            paddlers[column][row] = paddler;
            return tmp;
        }else{
            return null;
        }
    }


}
