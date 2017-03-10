package nz.co.karodata.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
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
    public Image [] [] images = new Image[6][20];
    public Label [] [] tlables = new Label[6][20];



    public TeamView(Stage stage, Team team, int x, int y){
        this.team = team;
        this.group = new Group();

        group.setPosition(x,y);

        float vSpacing = 50;
        float hSpacing = WIDTH/4;
        float top = HEIGHT;

        int d =0;
        int lx = 0;
        int lr = 0;
        int rl = 0;
        int rx = 0;
        int s = 0;

        //Drummers
        for(int i = 0; i < team.numDrummers; i++){
            images[0][d] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
            tlables[0][d] = new Label(team.drummers[i].name, skin);
            if (team.drummers[i].gender.toUpperCase().equals("M")) {
                changeImageColor(images[1][lx],Color.BLUE);
            } else {
                changeImageColor(images[1][lx],Color.PINK);
            }
            group.addActor(images[0][d]);
            group.addActor(tlables[0][d]);
            images[0][lx].setName(team.paddlers[i].name);
            images[0][lx].setPosition(0,top + 20 - (lx*vSpacing));
            tlables[0][lx].setName(team.paddlers[i].name);
            tlables[0][lx].setPosition(0, top - (lx*vSpacing));
            team.paddlers[i].teamCol = 0;
            team.paddlers[i].teamRow = lx;
            lx = lx + 1;
        }
        //group.addActor(drummer);
        //drummer.setName("Drummer");
        //drummer.setAlignment(Align.center);
        //drummer.setHeight(vSpacing);
        //drummer.setWidth(hSpacing);
        //drummer.setPosition(hSpacing *1/2, top - (2 * vSpacing));

        //Paddlers
        for(int i = 0; i < team.numPaddlers; i++){
            if (team.paddlers[i].paddlingSide.toUpperCase().equals("LX")){
                images[1][lx] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                tlables[1][lx] = new Label(team.paddlers[i].name, skin);
                if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                    changeImageColor(images[1][lx],Color.BLUE);
                    //changeLabelColor(tlables[1][lx],Color.BLUE);
                } else {
                    changeImageColor(images[1][lx],Color.PINK);
                    //changeLabelColor(tlables[1][lx],Color.PINK);
                }
                group.addActor(images[1][lx]);
                group.addActor(tlables[1][lx]);
                images[1][lx].setName(team.paddlers[i].name);
                images[1][lx].setPosition(0,top + 20 - (lx*vSpacing));
                tlables[1][lx].setName(team.paddlers[i].name);
                tlables[1][lx].setPosition(0, top - (lx*vSpacing));
                team.paddlers[i].teamCol = 1;
                team.paddlers[i].teamRow = lx;
                lx = lx + 1;
            } else {
                if (team.paddlers[i].paddlingSide.toUpperCase().equals("LR")){
                    images[2][lr] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                    tlables[2][lr] = new Label(team.paddlers[i].name, skin);
                    if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                        changeImageColor(images[2][lr],Color.BLUE);
                        //changeLabelColor(tlables[2][lr],Color.BLUE);
                    } else {
                        changeImageColor(images[2][lr],Color.PINK);
                        //changeLabelColor(tlables[2][lr],Color.PINK);
                    }
                    tlables[2][lr] = new Label(team.paddlers[i].name, skin);
                    group.addActor(images[2][lr]);
                    group.addActor(tlables[2][lr]);
                    images[2][lr].setName(team.paddlers[i].name);
                    images[2][lr].setPosition(hSpacing,top + 20 - (lr*vSpacing));
                    tlables[2][lr].setName(team.paddlers[i].name);
                    tlables[2][lr].setPosition(hSpacing, top - (lr*vSpacing));
                    team.paddlers[i].teamCol = 2;
                    team.paddlers[i].teamRow = lr;
                    lr = lr + 1;
                } else {
                    if (team.paddlers[i].paddlingSide.toUpperCase().equals("RX")){
                        images[3][rx] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                        tlables[3][rx] = new Label(team.paddlers[i].name, skin);
                        if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                            changeImageColor(images[3][rx],Color.ROYAL);
                            //changeLabelColor(tlables[3][rx],Color.BLUE);
                        } else {
                            changeImageColor(images[3][rx],Color.PINK);
                            //changeLabelColor(tlables[3][rx],Color.PINK);
                        }
                        tlables[3][rx] = new Label(team.paddlers[i].name, skin);
                        group.addActor(images[3][rx]);
                        group.addActor(tlables[3][rx]);
                        images[3][rx].setName(team.paddlers[i].name);
                        images[3][rx].setPosition(hSpacing * 2,top + 20 - (rx*vSpacing));
                        tlables[3][rx].setName(team.paddlers[i].name);
                        tlables[3][rx].setPosition(hSpacing * 2, top - (rx*vSpacing));
                        team.paddlers[i].teamCol = 3;
                        team.paddlers[i].teamRow = rx;
                        rx = rx + 1;
                    } else {
                        images[4][rl] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                        tlables[4][rl] = new Label(team.paddlers[i].name, skin);
                        if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                            changeImageColor(images[4][rl],Color.ROYAL);
                            //changeLabelColor(tlables[4][rl],Color.BLUE);
                        } else {
                            changeImageColor(images[4][rl],Color.PINK);
                            //changeLabelColor(tlables[4][rl],Color.PINK);
                        }
                        tlables[4][rl] = new Label(team.paddlers[i].name, skin);
                        group.addActor(images[4][rl]);
                        group.addActor(tlables[4][rl]);
                        images[4][rl].setName(team.paddlers[i].name);
                        images[4][rl].setPosition(hSpacing * 3,top + 20 - (rl*vSpacing));
                        tlables[4][rl].setName(team.paddlers[i].name);
                        tlables[4][rl].setPosition(hSpacing * 3, top - (rl*vSpacing));
                        team.paddlers[i].teamCol = 4;
                        team.paddlers[i].teamRow = rl;
                        rl = rl + 1;
                    }
                }
            }
        }

        stage.addActor(group);
    }


    public boolean changeImageColor(Image image, Color color){
        ColorAction colorAction = new ColorAction();
        colorAction.setEndColor(color);
        colorAction.setDuration(0.1f);
        image.addAction(colorAction);
        return true;
    }

    public boolean changeActorColor(Actor actor, Color color){
        ColorAction colorAction = new ColorAction();
        colorAction.setEndColor(color);
        colorAction.setDuration(0.1f);
        actor.addAction(colorAction);
        return true;
    }

    public boolean changeLabelColor(Label label, Color color){
        ColorAction colorAction = new ColorAction();
        colorAction.setEndColor(color);
        colorAction.setDuration(0.1f);
        label.addAction(colorAction);
        return true;
    }

    public static boolean testActorTeam(Actor actor, TeamView teamView) {
        if (actor != null) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 20; j++) {
                    if (teamView.images[i][j] == actor){
                        return true;
                    }
                    if (teamView.tlables[i][j] == actor){
                        return true;
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }


}
