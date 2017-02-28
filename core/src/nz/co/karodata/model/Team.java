package nz.co.karodata.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Susan on 23/02/2017.
 */
public class Team {
    public Person [] drummers;
    public Person [] sweeps;
    public Paddler [] paddlers;

    public Team(){

    }

    public static Team loadTeam (File file){
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            Set<Person> drummers = new HashSet<Person>();
            Set<Paddler> paddlers = new HashSet<Paddler>();
            Set<Person> sweeps = new HashSet<Person>();

            String line;
            while((line = in.readLine()) != null){
                String[] values = line.split("\t");
                if (values[0].equals("Paddler")){
                    Paddler p = new Paddler(line);
                    paddlers.add(p);
                } else if (values[0].equals("Drummer")) {
                    Person p = new Person(line);
                    drummers.add(p);
                } else if (values[0].equals("Sweep")) {
                    Person p = new Person(line);
                    sweeps.add(p);
                }
            }
            return new Team();

        } catch (IOException e)  {
            e.printStackTrace();
            return null;
        }

    }

}
