package nz.co.karodata.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import nz.co.karodata.model.DragonBoat;

/**
 * Created by Susan on 25 Feb 17.
 */
public class DragonBoatView {

    //CONSTANTS
    private static final int WIDTH = 500;
    private static final int HEIGHT = 662;
    private static final Skin skin = new Skin(Gdx.files.internal("core/assets/uiskin.json"));

    //MODEL REFERENCE
    private DragonBoat boat;

    //VIEW FIELDS
    private Label[][] labels = new Label[DragonBoat.NUM_SIDES][DragonBoat.NUM_ROWS];
    private Group group;
    //TODO add labels for sweep and drummer

    public DragonBoatView(Stage stage, DragonBoat boat, int x, int y){
        this.boat = boat;
        this.group = new Group();

        group.setPosition(x,y);

        Image imageBoat = new Image(new Texture(Gdx.files.internal("core/assets/dragon_boat.png")));
        group.addActor(imageBoat);

        float vSpacing = HEIGHT/(DragonBoat.NUM_ROWS + 3);
        float hSpacing = WIDTH/(DragonBoat.NUM_SIDES + 1);
        float top = HEIGHT;

        for(int i = 0; i < DragonBoat.NUM_ROWS; i++){
            float yOffset = ((i+2) * vSpacing) + 15;
            float xOffset = 30;

            labels[0][i] = new Label("LHSide" + (i+1), skin);
            group.addActor(labels[0][i]);
            labels[0][i].setPosition(xOffset, top - yOffset);

            labels[1][i] = new Label("RHSide" + (i+1), skin);
            group.addActor(labels[1][i]);
            labels[1][i].setPosition(xOffset + hSpacing, top - yOffset);
        }

        stage.addActor(group);
    }

}
