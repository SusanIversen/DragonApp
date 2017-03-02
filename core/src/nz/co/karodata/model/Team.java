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
        int id = 0;
        int ip = 0;
        int is = 0;


        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line;
            while((line = in.readLine()) != null){
                String[] values = line.split(",");
                if (values[0].toLowerCase().equals("paddler")){
                    Gdx.app.log("ShowType", values[0]);
                    paddlers[ip] = new Paddler(values[1], Integer.parseInt(values[2]), values[3], Integer.parseInt(values[4]),values[5], values[6]);
                    ip = ip + 1;
                } else {
                    if (values[0].toLowerCase().equals("drummer")){
                        drummers[id] = new Person(values[1], Integer.parseInt(values[2]), values[3]);
                        id = id + 1;
                    } else {
                        if (values[0].toLowerCase().equals("sweep")){
                            sweeps[is] = new Person(values[1], Integer.parseInt(values[2]), values[3]);
                            is = is + 1;
                        }
                    }
                }
            }

        } catch (IOException e)  {
            e.printStackTrace();
        }

    }

}
