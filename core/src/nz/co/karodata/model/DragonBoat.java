package nz.co.karodata.model;

/**
 * Created by Susan on 23/02/2017.
 */
public class DragonBoat {
    public static final int NUM_ROWS = 10;
    public static final int NUM_SIDES = 2;
    public static final int LEFT_ROW = 0;
    public static final int RIGHT_ROW = 1;

    public Person drummer;
    public Paddler[][] paddlers = new Paddler[NUM_SIDES][NUM_ROWS];
    public Person sweep;

    public DragonBoat() {
    //Empty Constructor
    }

    public Person addDrummer(Person person){
            drummer = person;
            return drummer;
    }

    public Person removeDrummer(Person person){
        drummer = null;
        return null;
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

    public Paddler removePaddler(int column, int row){
        if(row >= 0 && row < NUM_ROWS && column >= 0 && column < NUM_SIDES){
            paddlers[column][row] = null;
            return null;
        }else{
            return null;
        }
    }

    public Person addSweep(Person person){
        sweep = person;
        return sweep;
    }

    public Person removeSweep(Person person){
        sweep = null;
        return null;
    }

    public long leftWeight(DragonBoat dragonBoat){
        long w = 0;
        for(int i = 0; i < DragonBoat.NUM_ROWS; i++) {
            try {w = w + dragonBoat.paddlers[0][i].weight;} catch (Exception e) { }
        }
        return w;
    }

    public long rightWeight(DragonBoat dragonBoat){
        long w = 0;
        for(int i = 0; i < DragonBoat.NUM_ROWS; i++){
            try { w = w + dragonBoat.paddlers[1][i].weight;} catch (Exception e) { }
        }
        return w;
    }

    public long balance(DragonBoat dragonBoat){
        long l = 0;
        long r = 0;
        long b = 0;

        l = leftWeight(dragonBoat) ;
        r = rightWeight(dragonBoat);
        b = l - r;
        return b;
    }

    public long frontWeight(DragonBoat dragonBoat){
        long w = 0;
        try {w = w + dragonBoat.drummer.weight;} catch (Exception e) { }

        for(int i = 0; i < DragonBoat.NUM_ROWS/2; i++){
            try { w = w + dragonBoat.paddlers[0][i].weight;} catch (Exception e) { }
            try { w = w + dragonBoat.paddlers[1][i].weight;} catch (Exception e) { }
        }
        return w;
    }

    public long backWeight(DragonBoat dragonBoat){
        long w = 0;
        try {w = w + dragonBoat.sweep.weight;} catch (Exception e) { }

        for(int i = DragonBoat.NUM_ROWS/2; i < DragonBoat.NUM_ROWS; i++){
            try { w = w + dragonBoat.paddlers[0][i].weight;} catch (Exception e) { }
            try { w = w + dragonBoat.paddlers[1][i].weight;} catch (Exception e) { }
        }
        return w;
    }
    public long balance2(DragonBoat dragonBoat){
        long f = 0;
        long bk = 0;
        long b = 0;

        f = frontWeight(dragonBoat) ;
        bk = backWeight(dragonBoat);
        b = f - bk;
        return b;
    }

}
