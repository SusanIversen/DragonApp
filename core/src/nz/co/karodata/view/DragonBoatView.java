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
    private Label[][] labels = new Label[DragonBoat.NUM_SIDES][DragonBoat.NUM_ROWS];
    private Label drummer = new Label("Drummer", skin);
    private Label sweep = new Label("Sweep", skin);


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
            labels[0][i] = new Label("LHSide" + (i+1), skin);
            group.addActor(labels[0][i]);
            labels[0][i].setName("LHS" + (i+1));
            labels[0][i].setAlignment(Align.center);
            labels[0][i].setHeight(vSpacing);
            labels[0][i].setWidth(hSpacing);
            labels[0][i].setPosition(0, top - yOffset);

            //RHS
            labels[1][i] = new Label("RHSide" + (i+1), skin);
            group.addActor(labels[1][i]);
            labels[1][i].setName("RHS" + (i+1));
            labels[1][i].setAlignment(Align.center);
            labels[1][i].setHeight(vSpacing);
            labels[1][i].setWidth(hSpacing);
            labels[1][i].setPosition(hSpacing, top - yOffset);

        }

        //Sweep
        group.addActor(sweep);
        sweep.setName("Sweep");
        sweep.setAlignment(Align.center);
        sweep.setHeight(vSpacing);
        sweep.setWidth(hSpacing);
        sweep.setPosition(hSpacing *1/2, top - (13* vSpacing));

        stage.addActor(group);
    }

}
