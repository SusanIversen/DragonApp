package nz.co.karodata.model;

import com.badlogic.gdx.Gdx;

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

    //FIELDS
    Person [] drummers = new Person[3];
    Person [] sweeps = new Person[3];
    Paddler [] paddlers = new Paddler[30];


    public Team (File file){
//        Team thisTeam = new Team();

        int id = 0;
        int ip = 0;
        int is = 0;


        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
  //          Set<Person> drummers = new HashSet<Person>();
  //          Set<Paddler> paddlers = new HashSet<Paddler>();
 //           Set<Person> sweeps = new HashSet<Person>();

            String line;
            while((line = in.readLine()) != null){
                String[] values = line.split(",");
                Gdx.app.log("ShowType", line);
                if (values[0].toString() != "Paddler"){
                    Gdx.app.log("ShowType", values[0]);
                }
                /*
                switch (values[0]){
                    case "paddler":
                        paddlers[ip] = new Paddler(line);
                        ip = ip + 1;
                        break;
                    case "drummer":
                        drummers[id] = new Person(line);
                        id = id + 1;
                        break;
                    case "sweep":
                        sweeps[is] = new Person(line);
                        is = is + 1;
                        break;
                    case "default":
                        break;
                }
                */
            }

        } catch (IOException e)  {
            e.printStackTrace();
        }

    }

}
