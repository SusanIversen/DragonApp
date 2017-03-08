package nz.co.karodata.model;

import nz.co.karodata.values.Preference;
import nz.co.karodata.values.Side;

/**
 * Created by Susan on 23/02/2017.
 */
public class Paddler extends Person {

    public int strength;
    public String paddlingSide;
    public String rowPreferences;
    public Boolean available;
    public Boolean used;

    public Paddler (String inName, Integer inWeight, String inGender, Integer inStrength, String inSide, String inPref1 ){
        super(inName,inWeight,inGender);
        this.strength = inStrength;
        this.paddlingSide = inSide;
        this.rowPreferences = inPref1;
        this.available = true;
        this.used = false;

    }
}

