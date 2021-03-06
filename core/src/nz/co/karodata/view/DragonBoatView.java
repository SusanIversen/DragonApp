package nz.co.karodata.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import nz.co.karodata.model.DragonBoat;

/**
 * Created by Susan on 25 Feb 17.
 */
public class DragonBoatView {

    //CONSTANTS
    private static final int WIDTH = 306;
    private static final int HEIGHT = 662;
    private static final Skin skin = new Skin(Gdx.files.internal("core/assets/uiskin.json"));

    //MODEL REFERENCE
    private DragonBoat boat;

    //VIEW FIELDS
    private Group group;
    public Label[][] blables = new Label[DragonBoat.NUM_SIDES][DragonBoat.NUM_ROWS];
    public Label drummer = new Label("Drummer", skin);
    public Label sweep = new Label("Sweep", skin);
    public Label leftweight = new Label("0.0",skin);
    public Label rightweight = new Label("0.0",skin);
    public Label balanceweight = new Label("0.0",skin);
    public Label frontweight = new Label("0.0",skin);
    public Label backweight = new Label("0.0",skin);
    public Label balance2weight = new Label("0.0",skin);

    public DragonBoatView(Stage stage, DragonBoat boat, int x, int y){
        this.boat = boat;
        this.group = new Group();

        group.setPosition(x,y);

        Image imageBoat = new Image(new Texture(Gdx.files.internal("core/assets/dragon_boat.png")));
        group.addActor(imageBoat);

        float vSpacing = HEIGHT/(DragonBoat.NUM_ROWS + 3);
        float hSpacing = WIDTH/(DragonBoat.NUM_SIDES);
        float top = HEIGHT;

        //Drummer
        group.addActor(drummer);
        drummer.setName("Drummer");
        drummer.setAlignment(Align.center);
        drummer.setHeight(vSpacing);
        drummer.setWidth(hSpacing);
        drummer.setPosition(hSpacing *1/2, top - (2 * vSpacing));

        //Paddlers
        for(int i = 0; i < DragonBoat.NUM_ROWS; i++){
            float yOffset = ((i+3) * vSpacing);

            //LHS
            blables[0][i] = new Label("LHSide" + (i+1), skin);
            group.addActor(blables[0][i]);
            blables[0][i].setName("LHS" + (i+1));
            blables[0][i].setAlignment(Align.center);
            blables[0][i].setHeight(vSpacing);
            blables[0][i].setWidth(hSpacing);
            blables[0][i].setPosition(0, top - yOffset);

            //RHS
            blables[1][i] = new Label("RHSide" + (i+1), skin);
            group.addActor(blables[1][i]);
            blables[1][i].setName("RHS" + (i+1));
            blables[1][i].setAlignment(Align.center);
            blables[1][i].setHeight(vSpacing);
            blables[1][i].setWidth(hSpacing);
            blables[1][i].setPosition(hSpacing, top - yOffset);

        }

        //Sweep
        group.addActor(sweep);
        sweep.setName("Sweep");
        sweep.setAlignment(Align.center);
        sweep.setHeight(vSpacing);
        sweep.setWidth(hSpacing);
        sweep.setPosition(hSpacing *1/2, top - (13* vSpacing));

        group.addActor(leftweight);
        leftweight.setName("leftweight");
        leftweight.setAlignment(Align.left);
        leftweight.setPosition(70, top - 20);

        group.addActor(rightweight);
        rightweight.setName("rightweight");
        rightweight.setAlignment(Align.right);
        rightweight.setPosition(70 + hSpacing, top - 20);

        group.addActor(balanceweight);
        balanceweight.setName("balanceweight");
        balanceweight.setAlignment(Align.center);
        balanceweight.setPosition(70 + hSpacing *1/2, top - 20);

        group.addActor(frontweight);
        frontweight.setName("frontweight");
        frontweight.setAlignment(Align.left);
        frontweight.setPosition(-70, top - (vSpacing * 4));

        group.addActor(balance2weight);
        balance2weight.setName("backweight)");
        balance2weight.setAlignment(Align.right);
        balance2weight.setPosition(-70 ,top - (vSpacing * 7));

        group.addActor(backweight);
        backweight.setName("balance2weight");
        backweight.setAlignment(Align.center);
        backweight.setPosition(-70, top - (vSpacing * 10));


        stage.addActor(group);
    }

    public static boolean testActorBoat(Actor actor, DragonBoatView dragonBoatView) {
        if (actor != null) {
            for (int i = 0; i < DragonBoat.NUM_SIDES; i++) {
                for (int j = 0; j < DragonBoat.NUM_ROWS; j++) {
                    if (dragonBoatView.blables[i][j] == actor) {
                        return true;
                    }
                }
            }
            if (dragonBoatView.sweep == actor){
                return true;
            }
            if (dragonBoatView.drummer == actor){
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

}
