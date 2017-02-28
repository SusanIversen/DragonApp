package nz.co.karodata.model;

/**
 * Created by Susan on 23/02/2017.
 */
public class Person {
    public String name;
    public int weight;
    public String gender;

    public Person(String details){
        String[] values = details.split("\t");
        name = values[1];
        gender = values[2];
        weight = Integer.parseInt(values[3]);
    }
}

