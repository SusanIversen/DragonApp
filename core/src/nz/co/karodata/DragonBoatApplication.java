package nz.co.karodata;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import nz.co.karodata.model.DragonBoat;
import nz.co.karodata.model.Paddler;
import nz.co.karodata.model.Team;
import nz.co.karodata.view.DragonBoatView;
import nz.co.karodata.view.TeamView;

import java.awt.*;
import java.io.File;

public class DragonBoatApplication extends ApplicationAdapter implements InputProcessor{
	private Stage stageBoat;
	private Skin skin;
	private Team team;
	private DragonBoat thisBoat;


	private DragonBoatView boatView;
    private TeamView teamView;

    private Actor hitActorDown;
    private Actor hitActorUp;


	@Override
	public void create () {
        team = new Team(new File("core/assets/UHStorm/TeamUHStorm.csv"));

	    skin = new Skin(Gdx.files.internal("core/assets/uiskin.json"));
		stageBoat = new Stage(new ScreenViewport());

		//TODO dont just create a new boat here, save it as the boat that will be passed throughout the app
		thisBoat = new DragonBoat();
        boatView = new DragonBoatView(stageBoat, new DragonBoat(), 130, 0);
        teamView = new TeamView(stageBoat, team, 600,0);

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
		    Gdx.app.log("touchDown", hitActorDown.getName());
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

        if (hitActorUp != null) {
            if (hitActorUp == hitActorDown){
                Paddler curPaddler;
                curPaddler = team.getPaddler(hitActorDown.getName(), team);
                if (curPaddler.available == true) {
                    teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow],Color.RED);
                    teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow],Color.RED);
                    curPaddler.available = false;
                } else {
                    if (curPaddler.gender.equals("M")) {
                        teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow],Color.BLUE);
                    } else {
                        teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow],Color.PINK);
                    }
                    teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow],Color.WHITE);
                    curPaddler.available = true;
                }
            }
        }

        if (hitActorUp != null && hitActorDown != null) {
			if (hitActorUp.getName().substring(0,3).equals("LHS")||hitActorUp.getName().substring(0,3).equals("RHS")) {
				if (hitActorUp.getName().equals("Drummer")) {
					boatView.drummer.setText(hitActorDown.getName());
					boatView.drummer.invalidate();
				} else {
					if (hitActorUp.getName().equals("Sweep")) {
						boatView.sweep.setText(hitActorDown.getName());
						boatView.sweep.invalidate();
					} else {
						int i = 0;
						int j = 0;
						if (hitActorUp.getName().substring(0, 1).equals("L")) {
							i = 0;
						} else {
							if (hitActorUp.getName().substring(0, 1).equals("R")) {
								i = 1;
							}
						}
						j = Integer.parseInt(hitActorUp.getName().substring(3));

						boatView.blables[i][j-1].setText(hitActorDown.getName());
						boatView.blables[i][j-1].invalidate();
                        Paddler curPaddler;
                        curPaddler = team.getPaddler(hitActorDown.getName(), team);
                        thisBoat.addPaddler(i,j-1,curPaddler);
                        boatView.leftweight.setText(Long.toString(thisBoat.leftWeight(thisBoat)));
                        boatView.rightweight.setText(Long.toString(thisBoat.rightWeight(thisBoat)));
                        boatView.balanceweight.setText(Long.toString(thisBoat.balance(thisBoat)));
                        boatView.frontweight.setText(Long.toString(thisBoat.frontWeight(thisBoat)));
                        boatView.backweight.setText(Long.toString(thisBoat.backWeight(thisBoat)));
                        boatView.balance2weight.setText(Long.toString(thisBoat.balance2(thisBoat)));

                        if (curPaddler.available == true) {
                            teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow],Color.GRAY);
                            teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow],Color.GRAY);
                            curPaddler.available = false;
                        }


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
