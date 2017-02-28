package nz.co.karodata.model;

import nz.co.karodata.values.Preference;
import nz.co.karodata.values.Side;

/**
 * Created by Susan on 23/02/2017.
 */
public class Paddler extends Person {
    public int strength;
    public Side paddlingSide;
    public Preference[] rowPreferences;

    public Paddler (String details){
        super(details);
        String[] values = details.split("\t");
        String pref = values[4];

        if(pref.equalsIgnoreCase("LX")){
            paddlingSide = Side.LEFT;
        }else if(pref.equalsIgnoreCase("RX")){
            paddlingSide = Side.RIGHT;
        }else if(pref.equalsIgnoreCase("LR")){
            paddlingSide = Side.PREFERRED_LEFT;
        }else if(pref.equalsIgnoreCase("RL")){
            paddlingSide = Side.PREFERRED_RIGHT;
        }
    }
}

