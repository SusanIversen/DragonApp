package nz.co.karodata;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Color;

import nz.co.karodata.view.TeamMember;

public class DragonBoat extends ApplicationAdapter implements InputProcessor{
	private Stage stageBoat;
	private Skin skin;

	private Integer topPerson;
    private Integer gapPerson;
	private Integer columnLeftRight;
    private Integer columnLeftOnly;
    private Integer columnRightLeft;
    private Integer columnRightOnly;

    private Integer boatMiddle;
    private Integer boatLeft;
    private Integer boatRight;
    private Integer boatTop;
    private Integer boatGap;

    private Actor hitActorDown;
    private Actor hitActorUp;

    public Label labelDrummer;
    public Label labelLHSRow1;
    public Label labelRHSRow1;
    public Label labelLHSRow2;
    public Label labelRHSRow2;
    public Label labelLHSRow3;
    public Label labelRHSRow3;
    public Label labelLHSRow4;
    public Label labelRHSRow4;
    public Label labelLHSRow5;
    public Label labelRHSRow5;
    public Label labelLHSRow6;
    public Label labelRHSRow6;
    public Label labelLHSRow7;
    public Label labelRHSRow7;
    public Label labelLHSRow8;
    public Label labelRHSRow8;
    public Label labelLHSRow9;
    public Label labelRHSRow9;
    public Label labelLHSRow10;
    public Label labelRHSRow10;
    public Label labelSweep;


	@Override
	public void create () {
	    skin = new Skin(Gdx.files.internal("uiskin.json"));
		stageBoat = new Stage(new ScreenViewport());

		//First get the boat UI all set up
		boatMiddle = 220;
		boatLeft = 130;
		boatRight = 300;
		boatTop = 575;
		boatGap = 50;

		Group groupBoat = new Group();
		Image imageBoat = new Image(new Texture(Gdx.files.internal("dragon_boat.png")));
		groupBoat.addActor(imageBoat);
		//Drummer
		labelDrummer = new Label("Drummer",skin);
		groupBoat.addActor(labelDrummer);
		labelDrummer.setName("Drummer");
		//LHS
		labelLHSRow1 = new Label("LHSRow1", skin);
		groupBoat.addActor(labelLHSRow1);
		labelLHSRow1.setName("LHSRow1");
		labelLHSRow2 = new Label("LHSRow2", skin);
		groupBoat.addActor(labelLHSRow2);
        labelLHSRow2.setName("LHSRow2");
		labelLHSRow3 = new Label("LHSRow3", skin);
		groupBoat.addActor(labelLHSRow3);
        labelLHSRow3.setName("LHSRow3");
		labelLHSRow4 = new Label("LHSRow4", skin);
		groupBoat.addActor(labelLHSRow4);
        labelLHSRow4.setName("LHSRow4");
		labelLHSRow5 = new Label("LHSRow5", skin);
		groupBoat.addActor(labelLHSRow5);
        labelLHSRow5.setName("LHSRow5");
		labelLHSRow6 = new Label("LHSRow6", skin);
		groupBoat.addActor(labelLHSRow6);
        labelLHSRow6.setName("LHSRow6");
		labelLHSRow7 = new Label("LHSRow7", skin);
		groupBoat.addActor(labelLHSRow7);
        labelLHSRow7.setName("LHSRow7");
		labelLHSRow8 = new Label("LHSRow8", skin);
		groupBoat.addActor(labelLHSRow8);
        labelLHSRow8.setName("LHSRow8");
		labelLHSRow9 = new Label("LHSRow9", skin);
		groupBoat.addActor(labelLHSRow9);
        labelLHSRow9.setName("LHSRow9");
		labelLHSRow10 = new Label("LHSRow10", skin);
		groupBoat.addActor(labelLHSRow10);
        labelLHSRow10.setName("LHSRow10");
		//RHS
		groupBoat.addActor(labelDrummer);
		labelRHSRow1 = new Label("RHSRow1", skin);
		groupBoat.addActor(labelRHSRow1);
        labelRHSRow1.setName("RHSRow1");
		labelRHSRow2 = new Label("RHSRow2", skin);
		groupBoat.addActor(labelRHSRow2);
        labelRHSRow2.setName("RHSRow2");
		labelRHSRow3 = new Label("RHSRow3", skin);
		groupBoat.addActor(labelRHSRow3);
        labelRHSRow3.setName("RHSRow3");
		labelRHSRow4 = new Label("RHSRow4", skin);
		groupBoat.addActor(labelRHSRow4);
        labelRHSRow4.setName("RHSRow4");
		labelRHSRow5 = new Label("RHSRow5", skin);
		groupBoat.addActor(labelRHSRow5);
        labelRHSRow5.setName("RHSRow5");
		labelRHSRow6 = new Label("RHSRow6", skin);
		groupBoat.addActor(labelRHSRow6);
        labelRHSRow6.setName("RHSRow6");
		labelRHSRow7 = new Label("RHSRow7", skin);
		groupBoat.addActor(labelRHSRow7);
        labelRHSRow7.setName("RHSRow7");
		labelRHSRow8 = new Label("RHSRow8", skin);
		groupBoat.addActor(labelRHSRow8);
        labelRHSRow8.setName("RHSRow8");
		labelRHSRow9 = new Label("RHSRow9", skin);
		groupBoat.addActor(labelRHSRow9);
        labelRHSRow9.setName("RHSRow9");
		labelRHSRow10 = new Label("RHSRow10", skin);
		groupBoat.addActor(labelRHSRow10);
        labelRHSRow10.setName("RHSRow10");
        //Sweep
        labelSweep = new Label("Sweep",skin);
        groupBoat.addActor(labelSweep);

		stageBoat.addActor(groupBoat);
		labelDrummer.setPosition(boatMiddle,boatTop);
		labelLHSRow1.setPosition(boatLeft, boatTop - boatGap);
		labelRHSRow1.setPosition(boatRight, boatTop - boatGap);
		labelLHSRow2.setPosition(boatLeft, boatTop - (boatGap * 2));
		labelRHSRow2.setPosition(boatRight, boatTop - (boatGap * 2));
		labelLHSRow3.setPosition(boatLeft, boatTop - (boatGap * 3));
		labelRHSRow3.setPosition(boatRight, boatTop - (boatGap * 3));
		labelLHSRow4.setPosition(boatLeft, boatTop - (boatGap * 4));
		labelRHSRow4.setPosition(boatRight, boatTop - (boatGap * 4));
		labelLHSRow5.setPosition(boatLeft, boatTop - (boatGap * 5));
		labelRHSRow5.setPosition(boatRight, boatTop - (boatGap * 5));
		labelLHSRow6.setPosition(boatLeft, boatTop - (boatGap * 6));
		labelRHSRow6.setPosition(boatRight, boatTop - (boatGap * 6));
		labelLHSRow7.setPosition(boatLeft, boatTop - (boatGap * 7));
		labelRHSRow7.setPosition(boatRight, boatTop - (boatGap * 7));
		labelLHSRow8.setPosition(boatLeft, boatTop - (boatGap * 8));
		labelRHSRow8.setPosition(boatRight, boatTop - (boatGap * 8));
		labelLHSRow9.setPosition(boatLeft, boatTop - (boatGap * 9));
		labelRHSRow9.setPosition(boatRight, boatTop - (boatGap * 9));
		labelLHSRow10.setPosition(boatLeft, boatTop - (boatGap * 10));
		labelRHSRow10.setPosition(boatRight, boatTop - (boatGap * 10));
		labelSweep.setPosition(boatMiddle,boatTop - (boatGap * 11));


		//Second get the paddlers UI set up
        topPerson = 500;
        gapPerson = 100;
        columnLeftOnly = 600;
        columnLeftRight = 675;
        columnRightLeft = 750;
        columnRightOnly = 825;

        TeamMember groupPerson1 = new TeamMember();
        Image imagePerson1 = new Image(new Texture(Gdx.files.internal("person.png")));
		Label labelPerson1 = new Label("LO1", skin);
		groupPerson1.addActor(imagePerson1);
		imagePerson1.setName("I_LO1");
		groupPerson1.addActor(labelPerson1);
		labelPerson1.setName("L_LO1");

		stageBoat.addActor(groupPerson1);
		imagePerson1.setPosition(columnLeftOnly,topPerson);
		labelPerson1.setPosition(columnLeftOnly + 50,topPerson + 20);

        TeamMember groupPerson2 = new TeamMember();
        Image imagePerson2 = new Image(new Texture(Gdx.files.internal("person.png")));
        Label labelPerson2 = new Label("LR", skin);
        groupPerson2.addActor(imagePerson2);
        imagePerson2.setName("I_LR");
        groupPerson2.addActor(labelPerson2);
        labelPerson2.setName("L_LR");

        stageBoat.addActor(groupPerson2);
        imagePerson2.setPosition(columnLeftRight,topPerson);
        labelPerson2.setPosition(columnLeftRight + 50,topPerson + 20);

        TeamMember groupPerson3 = new TeamMember();
        Image imagePerson3 = new Image(new Texture(Gdx.files.internal("person.png")));
        Label labelPerson3 = new Label("RL", skin);
        groupPerson3.addActor(imagePerson3);
        imagePerson3.setName("I_RL");
        groupPerson3.addActor(labelPerson3);
        labelPerson3.setName("L_RL");

        stageBoat.addActor(groupPerson3);
        imagePerson3.setPosition(columnRightLeft,topPerson);
        labelPerson3.setPosition(columnRightLeft + 50,topPerson + 20);

        TeamMember groupPerson4 = new TeamMember();
        Image imagePerson4 = new Image(new Texture(Gdx.files.internal("person.png")));
        Label labelPerson4 = new Label("RO", skin);
        groupPerson4.addActor(imagePerson4);
        imagePerson4.setName("I_RO");
        groupPerson4.addActor(labelPerson4);
        labelPerson4.setName("L_RO");

        stageBoat.addActor(groupPerson4);
        imagePerson4.setPosition(columnRightOnly,topPerson);
        labelPerson4.setPosition(columnRightOnly + 50,topPerson + 20);

        TeamMember groupPerson5 = new TeamMember();
        Image imagePerson5 = new Image(new Texture(Gdx.files.internal("person.png")));
        Label labelPerson5 = new Label("LO2", skin);
        groupPerson5.addActor(imagePerson5);
        imagePerson5.setName("I_LO2");
        groupPerson5.addActor(labelPerson5);
        labelPerson5.setName("L_LO2");

        stageBoat.addActor(groupPerson5);
        imagePerson5.setPosition(columnLeftOnly,topPerson - gapPerson);
        labelPerson5.setPosition(columnLeftOnly + 50,topPerson - gapPerson + 20);

        InputMultiplexer im = new InputMultiplexer(this, stageBoat);
        Gdx.input.setInputProcessor(im);

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stageBoat.act(Gdx.graphics.getDeltaTime());
        stageBoat.draw();
	}
	
	@Override
	public void dispose () {
		stageBoat.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 coordDown = stageBoat.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
		hitActorDown = stageBoat.hit(coordDown.x,coordDown.y,true);

		if(hitActorDown != null) {
		    Gdx.app.log("HIT", hitActorDown.getName());

		    return true;
        }
        else {
            return false;
        }
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 coordUp = stageBoat.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        hitActorUp = stageBoat.hit(coordUp.x, coordUp.y, false);

        if (hitActorUp == hitActorDown) {

        }

        if (hitActorUp != null && hitActorDown != null) {
            if (hitActorUp.getName() != null) {
                switch (hitActorUp.getName()) {
                    case "Drummer":
                        labelDrummer.setText(hitActorDown.getName().substring(2));
                        labelDrummer.invalidate();
                        break;
                    case "LHSRow1":
                        labelLHSRow1.setText(hitActorDown.getName().substring(2));
                        labelLHSRow1.invalidate();
                        break;
                    case "LHSRow2":
                        labelLHSRow2.setText(hitActorDown.getName().substring(2));
                        labelLHSRow2.invalidate();
                        break;
                    case "LHSRow3":
                        labelLHSRow3.setText(hitActorDown.getName().substring(2));
                        labelLHSRow3.invalidate();
                        break;
                    case "LHSRow4":
                        labelLHSRow4.setText(hitActorDown.getName().substring(2));
                        labelLHSRow4.invalidate();
                        break;
                    case "LHSRow5":
                        labelLHSRow5.setText(hitActorDown.getName().substring(2));
                        labelLHSRow5.invalidate();
                        break;
                    case "LHSRow6":
                        labelLHSRow6.setText(hitActorDown.getName().substring(2));
                        labelLHSRow6.invalidate();
                        break;
                    case "LHSRow7":
                        labelLHSRow7.setText(hitActorDown.getName().substring(2));
                        labelLHSRow7.invalidate();
                        break;
                    case "LHSRow8":
                        labelLHSRow8.setText(hitActorDown.getName().substring(2));
                        labelLHSRow8.invalidate();
                        break;
                    case "LHSRow9":
                        labelLHSRow9.setText(hitActorDown.getName().substring(2));
                        labelLHSRow9.invalidate();
                        break;
                    case "LHSRow10":
                        labelLHSRow10.setText(hitActorDown.getName().substring(2));
                        labelLHSRow10.invalidate();
                        break;
                    case "RHSRow1":
                        labelRHSRow1.setText(hitActorDown.getName().substring(2));
                        labelRHSRow1.invalidate();
                        break;
                    case "RHSRow2":
                        labelRHSRow2.setText(hitActorDown.getName().substring(2));
                        labelRHSRow2.invalidate();
                        break;
                    case "RHSRow3":
                        labelRHSRow3.setText(hitActorDown.getName().substring(2));
                        labelRHSRow3.invalidate();
                        break;
                    case "RHSRow4":
                        labelRHSRow4.setText(hitActorDown.getName().substring(2));
                        labelRHSRow4.invalidate();
                        break;
                    case "RHSRow5":
                        labelRHSRow5.setText(hitActorDown.getName().substring(2));
                        labelRHSRow5.invalidate();
                        break;
                    case "RHSRow6":
                        labelRHSRow6.setText(hitActorDown.getName().substring(2));
                        labelRHSRow6.invalidate();
                        break;
                    case "RHSRow7":
                        labelRHSRow7.setText(hitActorDown.getName().substring(2));
                        labelRHSRow7.invalidate();
                        break;
                    case "RHSRow8":
                        labelRHSRow8.setText(hitActorDown.getName().substring(2));
                        labelRHSRow8.invalidate();
                        break;
                    case "RHSRow9":
                        labelRHSRow9.setText(hitActorDown.getName().substring(2));
                        labelRHSRow9.invalidate();
                        break;
                    case "RHSRow10":
                        labelRHSRow10.setText(hitActorDown.getName().substring(2));
                        labelRHSRow10.invalidate();
                        break;
                    case "Sweep":
                        labelSweep.setText(hitActorDown.getName().substring(2));
                        labelSweep.invalidate();
                        break;
                    default:
                        return false;
                    }
                }
            } else {
                hitActorUp = null;
                hitActorDown = null;
                return false;
            }
            hitActorUp = null;
            hitActorDown = null;
            return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
