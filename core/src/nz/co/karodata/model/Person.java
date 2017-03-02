package nz.co.karodata.model;

/**
 * Created by Susan on 23/02/2017.
 */
public class Person {
    private Person person;
    public String name;
    public int weight;
    public String gender;

    public Person(Person person){
        this.person = person;
    }

    public Person(String inName, Integer inWeight, String inGender){
        this.name = inName;
        this.weight = inWeight;
        this.gender = inGender;

    }
}

