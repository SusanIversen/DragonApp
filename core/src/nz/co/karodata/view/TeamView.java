package nz.co.karodata.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import nz.co.karodata.model.DragonBoat;
import nz.co.karodata.model.Team;

/**
 * Created by Susan on 25 Feb 17.
 */
public class TeamView {
    //CONSTANTS
    private static final int WIDTH = 400;
    private static final int HEIGHT = 550;
    private static final Skin skin = new Skin(Gdx.files.internal("core/assets/uiskin.json"));

    //TeamMemberView[] LO, LR, RL, RO
    //Store a reference to a Team object (Like in the DragonBoatView)
    //Go through all the paddlers in the team and check which side they prefer
    //Then add them to the corresponding array

    //MODEL REFERENCE
    private Team team;

    //VIEW FIELDS
    private Group group;
    //Images and Labels for Drummers, LO, LR, RL, RO, Sweeps
    private Image [] [] images = new Image[6][20];
    private Label [] [] labels = new Label[6][20];



    public TeamView(Stage stage, Team team, int x, int y){
        this.team = team;
        this.group = new Group();

        group.setPosition(x,y);

        float vSpacing = 40;
        float hSpacing = WIDTH/4;
        float top = HEIGHT;

        //Drummer
        //group.addActor(drummer);
        //drummer.setName("Drummer");
        //drummer.setAlignment(Align.center);
        //drummer.setHeight(vSpacing);
        //drummer.setWidth(hSpacing);
        //drummer.setPosition(hSpacing *1/2, top - (2 * vSpacing));

        //Paddlers
        int d =0;
        int lx = 0;
        int lr = 0;
        int rl = 0;
        int rx = 0;
        int s = 0;

        Gdx.app.log("Team Size",Integer.toString(team.numPaddlers) );
        for(int i = 0; i < team.numPaddlers; i++){
            if (team.paddlers[i].paddlingSide.toUpperCase().equals("LX")){
                if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                    images[1][lx] = new Image(new Texture(Gdx.files.internal("core/assets/man.jpg")));
                } else {
                    images[1][lx] = new Image(new Texture(Gdx.files.internal("core/assets/woman.jpg")));
                }
                labels[1][lx] = new Label(team.paddlers[i].name, skin);
                group.addActor(images[1][lx]);
                group.addActor(labels[1][lx]);
                images[1][lx].setName(team.paddlers[i].name);
                images[1][lx].setPosition(0,top + 20 - (lx*vSpacing));
                labels[1][lx].setName(team.paddlers[i].name);
                labels[1][lx].setPosition(0, top - (lx*vSpacing));
                lx = lx + 1;
            } else {
                if (team.paddlers[i].paddlingSide.toUpperCase().equals("LR")){
                    if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                        images[2][lr] = new Image(new Texture(Gdx.files.internal("core/assets/man.jpg")));
                    } else {
                        images[2][lr] = new Image(new Texture(Gdx.files.internal("core/assets/woman.jpg")));
                    }
                    labels[2][lr] = new Label(team.paddlers[i].name, skin);
                    group.addActor(images[2][lr]);
                    group.addActor(labels[2][lr]);
                    images[2][lr].setName(team.paddlers[i].name);
                    images[2][lr].setPosition(hSpacing,top + 20 - (lr*vSpacing));
                    labels[2][lr].setName(team.paddlers[i].name);
                    labels[2][lr].setPosition(hSpacing, top - (lr*vSpacing));
                    lr = lr + 1;
                } else {
                    if (team.paddlers[i].paddlingSide.toUpperCase().equals("RX")){
                        if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                            images[3][rx] = new Image(new Texture(Gdx.files.internal("core/assets/man.jpg")));
                        } else {
                            images[3][rx] = new Image(new Texture(Gdx.files.internal("core/assets/woman.jpg")));
                        }
                        labels[3][rx] = new Label(team.paddlers[i].name, skin);
                        group.addActor(images[3][rx]);
                        group.addActor(labels[3][rx]);
                        images[3][rx].setName(team.paddlers[i].name);
                        images[3][rx].setPosition(hSpacing * 2,top + 20 - (rx*vSpacing));
                        labels[3][rx].setName(team.paddlers[i].name);
                        labels[3][rx].setPosition(hSpacing * 2, top - (rx*vSpacing));
                        rx = rx + 1;
                    } else {
                        if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                            images[4][rl] = new Image(new Texture(Gdx.files.internal("core/assets/man.jpg")));
                        } else {
                            images[4][rl] = new Image(new Texture(Gdx.files.internal("core/assets/woman.jpg")));
                        }
                        labels[4][rl] = new Label(team.paddlers[i].name, skin);
                        group.addActor(images[4][rl]);
                        group.addActor(labels[4][rl]);
                        images[4][rl].setName(team.paddlers[i].name);
                        images[4][rl].setPosition(hSpacing * 3,top + 20 - (rl*vSpacing));
                        labels[4][rl].setName(team.paddlers[i].name);
                        labels[4][rl].setPosition(hSpacing * 3, top - (rl*vSpacing));
                        rl = rl + 1;
                    }
                }
            }
        }

        stage.addActor(group);
    }



}
