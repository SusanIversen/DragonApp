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
        Label drummerHeader = new Label("Drummers",skin) ;
        group.addActor(drummerHeader);
        drummerHeader.setPosition(0, top + 100);
        changeLabelColor(drummerHeader,Color.GREEN);

        for(int i = 0; i < team.numDrummers; i++){
            tlables[0][d] = new Label(team.drummers[i].name, skin);
            group.addActor(tlables[0][d]);
            tlables[0][d].setName(team.drummers[i].name);
            tlables[0][d].setPosition(hSpacing * (i+1), top + 100);
            tlables[0][d].setHeight(20);
            tlables[0][d].setWidth(100);
            tlables[0][d].setAlignment(Align.bottomLeft);

            team.drummers[i].teamCol = 0;
            team.drummers[i].teamRow = d;
            d = d + 1;
        }


        //Paddlers
        Label paddlerHeader = new Label("Paddlers",skin) ;
        group.addActor(paddlerHeader);
        paddlerHeader.setPosition(0, top + 60);
        changeLabelColor(paddlerHeader,Color.GREEN);
        Label paddlerHeaderLX = new Label("LX",skin) ;
        group.addActor(paddlerHeaderLX);
        paddlerHeaderLX.setPosition(0, top + 40);
        changeLabelColor(paddlerHeaderLX,Color.GREEN);
        Label paddlerHeaderLR = new Label("LR",skin) ;
        group.addActor(paddlerHeaderLR);
        paddlerHeaderLR.setPosition(hSpacing, top + 40);
        changeLabelColor(paddlerHeaderLR,Color.GREEN);
        Label paddlerHeaderRL = new Label("RL",skin) ;
        group.addActor(paddlerHeaderRL);
        paddlerHeaderRL.setPosition(hSpacing*2, top + 40);
        changeLabelColor(paddlerHeaderRL,Color.GREEN);
        Label paddlerHeaderRX = new Label("RX",skin) ;
        group.addActor(paddlerHeaderRX);
        paddlerHeaderRX.setPosition(hSpacing*3, top + 40);
        changeLabelColor(paddlerHeaderRX,Color.GREEN);


        for(int i = 0; i < team.numPaddlers; i++){
            if (team.paddlers[i].paddlingSide.toUpperCase().equals("LX")){
                images[1][lx] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                tlables[1][lx] = new Label(team.paddlers[i].name, skin);
                if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                    changeImageColor(images[1][lx],Color.ROYAL);
                } else {
                    changeImageColor(images[1][lx],Color.PINK);
                }
                group.addActor(images[1][lx]);
                group.addActor(tlables[1][lx]);
                images[1][lx].setName(team.paddlers[i].name);
                images[1][lx].setPosition(0,top + 20 - (lx*vSpacing));
                tlables[1][lx].setName(team.paddlers[i].name);
                tlables[1][lx].setPosition(0, top - (lx*vSpacing));
                tlables[1][lx].setHeight(20);
                tlables[1][lx].setWidth(100);
                team.paddlers[i].teamCol = 1;
                team.paddlers[i].teamRow = lx;
                lx = lx + 1;
            } else {
                if (team.paddlers[i].paddlingSide.toUpperCase().equals("LR")){
                    images[2][lr] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                    tlables[2][lr] = new Label(team.paddlers[i].name, skin);
                    if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                        changeImageColor(images[2][lr],Color.ROYAL);
                    } else {
                        changeImageColor(images[2][lr],Color.PINK);
                    }
                    tlables[2][lr] = new Label(team.paddlers[i].name, skin);
                    group.addActor(images[2][lr]);
                    group.addActor(tlables[2][lr]);
                    images[2][lr].setName(team.paddlers[i].name);
                    images[2][lr].setPosition(hSpacing,top + 20 - (lr*vSpacing));
                    tlables[2][lr].setName(team.paddlers[i].name);
                    tlables[2][lr].setPosition(hSpacing, top - (lr*vSpacing));
                    tlables[2][lr].setHeight(20);
                    tlables[2][lr].setWidth(100);
                    team.paddlers[i].teamCol = 2;
                    team.paddlers[i].teamRow = lr;
                    lr = lr + 1;
                } else {
                    if (team.paddlers[i].paddlingSide.toUpperCase().equals("RX")){
                        images[3][rx] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                        tlables[3][rx] = new Label(team.paddlers[i].name, skin);
                        if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                            changeImageColor(images[3][rx],Color.ROYAL);
                        } else {
                            changeImageColor(images[3][rx],Color.PINK);
                        }
                        tlables[3][rx] = new Label(team.paddlers[i].name, skin);
                        group.addActor(images[3][rx]);
                        group.addActor(tlables[3][rx]);
                        images[3][rx].setName(team.paddlers[i].name);
                        images[3][rx].setPosition(hSpacing * 2,top + 20 - (rx*vSpacing));
                        tlables[3][rx].setName(team.paddlers[i].name);
                        tlables[3][rx].setPosition(hSpacing * 2, top - (rx*vSpacing));
                        tlables[3][rx].setHeight(20);
                        tlables[3][rx].setWidth(100);
                        team.paddlers[i].teamCol = 3;
                        team.paddlers[i].teamRow = rx;
                        rx = rx + 1;
                    } else {
                        images[4][rl] = new Image(new Texture(Gdx.files.internal("core/assets/person.jpg")));
                        tlables[4][rl] = new Label(team.paddlers[i].name, skin);
                        if (team.paddlers[i].gender.toUpperCase().equals("M")) {
                            changeImageColor(images[4][rl],Color.ROYAL);
                        } else {
                            changeImageColor(images[4][rl],Color.PINK);
                        }
                        tlables[4][rl] = new Label(team.paddlers[i].name, skin);
                        group.addActor(images[4][rl]);
                        group.addActor(tlables[4][rl]);
                        images[4][rl].setName(team.paddlers[i].name);
                        images[4][rl].setPosition(hSpacing * 3,top + 20 - (rl*vSpacing));
                        tlables[4][rl].setName(team.paddlers[i].name);
                        tlables[4][rl].setPosition(hSpacing * 3, top - (rl*vSpacing));

                        tlables[4][rl].setHeight(20);
                        tlables[4][rl].setWidth(100);
                        team.paddlers[i].teamCol = 4;
                        team.paddlers[i].teamRow = rl;
                        rl = rl + 1;
                    }
                }
            }
        }
        //Sweeps
        Label sweepHeader = new Label("Sweeps",skin) ;
        group.addActor(sweepHeader);
        sweepHeader.setPosition(0, top - 500);
        changeLabelColor(sweepHeader,Color.GREEN);

        for(int i = 0; i < team.numSweeps; i++){
            tlables[5][s] = new Label(team.sweeps[i].name, skin);
            group.addActor(tlables[5][s]);
            tlables[5][s].setName(team.sweeps[i].name);
            tlables[5][s].setPosition(hSpacing * (i+1), top - 500);
            team.sweeps[i].teamCol = 5;
            team.sweeps[i].teamRow = s;
            s = s + 1;
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
