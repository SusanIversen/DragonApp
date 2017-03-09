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

import static nz.co.karodata.view.DragonBoatView.testActorBoat;

public class DragonBoatApplication extends ApplicationAdapter implements InputProcessor{
	private Stage stageBoat;
	private Skin skin;
	private Team team;
	private DragonBoat thisBoat;


	private DragonBoatView boatView;
    private TeamView teamView;

    private Actor hitActorDown;
    private Actor hitActorUp;
    private String hitActorDownPlace;
	private String hitActorUpPlace;


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
        hitActorDownPlace = "Nothing";

		if(hitActorDown != null) {
			//Gdx.app.log("touchDown", hitActorDown.getName());
			if (testActorBoat(hitActorDown, boatView)) {
				hitActorDownPlace = "Boat";
			} else {
				if (TeamView.testActorTeam(hitActorDown,teamView)){
					hitActorDownPlace = "Team";
				}
			}
		}
        return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Paddler curPaddler;
        Vector2 coordUp = stageBoat.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        hitActorUp = stageBoat.hit(coordUp.x, coordUp.y, false);
        hitActorUpPlace = "Nothing";
        if(hitActorUp != null) {
            //Gdx.app.log("touchDown", hitActorDown.getName());
            if (testActorBoat(hitActorUp, boatView)) {
                hitActorUpPlace = "Boat";
            } else {
                if (TeamView.testActorTeam(hitActorUp,teamView)){
                    hitActorUpPlace = "Team";
                }
            }
        }


        //If click up and down on the same team member then include or exclude them
        if (hitActorDownPlace == "Team" && hitActorUpPlace == "Team" && hitActorUp==hitActorDown ){
            //See if clicking on a Team Memeber
                try {
                curPaddler = team.getPaddler(hitActorDown.getName(), team);
                if (curPaddler.available) {
                    teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.RED);
                    teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.RED);
                    curPaddler.available = false;
                } else {
                    if (curPaddler.gender.toUpperCase().equals("M")) {
                        teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.BLUE);
                    } else {
                        teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.PINK);
                    }
                    teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.WHITE);
                    curPaddler.available = true;
                }
            } catch (Exception e) {}
            //deal with the sweep and drummer here
        }

        //If drag a team member into the boat then (need to test if that spot is taken)
        if (hitActorDownPlace == "Team" && hitActorUpPlace == "Boat") {
            if (hitActorUp.getName().equals("Drummer")) {
                boatView.drummer.setText(hitActorDown.getName());
                boatView.drummer.invalidate();
            } else {
                if (hitActorUp.getName().equals("Sweep")) {
                    boatView.sweep.setText(hitActorDown.getName());
                    boatView.sweep.invalidate();
                } else {
                    //find the position in the boat
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

                    //find out if there is someone already in this seat
                    curPaddler = team.getPaddler(boatView.blables[i][j-1].getText().toString(), team);
                    if (curPaddler!=null){
                        //remove them from the team
                        thisBoat.removePaddler(i, j - 1);
                        //make them available
                        curPaddler.available = true;
                        //recolour them
                        if (curPaddler.gender.toUpperCase().equals("M")){
                            teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.BLUE);
                            teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.BLUE);
                        } else {
                            teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.PINK);
                            teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.PINK);
                        }
                    }

                    boatView.blables[i][j - 1].setText(hitActorDown.getName());
                    boatView.blables[i][j - 1].invalidate();
                    curPaddler = team.getPaddler(hitActorDown.getName(), team);
                    thisBoat.addPaddler(i, j - 1, curPaddler);
                    boatView.leftweight.setText(Long.toString(thisBoat.leftWeight(thisBoat)));
                    boatView.rightweight.setText(Long.toString(thisBoat.rightWeight(thisBoat)));
                    boatView.balanceweight.setText(Long.toString(thisBoat.balance(thisBoat)));
                    boatView.frontweight.setText(Long.toString(thisBoat.frontWeight(thisBoat)));
                    boatView.backweight.setText(Long.toString(thisBoat.backWeight(thisBoat)));
                    boatView.balance2weight.setText(Long.toString(thisBoat.balance2(thisBoat)));

                    if (curPaddler.available) {
                        teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.GRAY);
                        teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.GRAY);
                        curPaddler.available = false;
                    }

                }
            }

        }

        //taking someone off the boat
        if (hitActorDownPlace == "Boat" && hitActorUpPlace != "Boat"){
            //do drummer and sweep next

            //find the position in the boat
            int i = 0;
            int j = 0;
            if (hitActorDown.getName().substring(0, 1).equals("L")) {
                i = 0;
            } else {
                if (hitActorDown.getName().substring(0, 1).equals("R")) {
                    i = 1;
                }
            }
            j = Integer.parseInt(hitActorDown.getName().substring(3));

            //find out who is being taken off
            curPaddler = team.getPaddler(boatView.blables[i][j-1].getText().toString(), team);
            if (curPaddler!=null){
                //remove them from the team
                thisBoat.removePaddler(i, j - 1);
                //make them available
                curPaddler.available = true;
                //recolour them
                if (curPaddler.gender.toUpperCase().equals("M")){
                    teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.BLUE);
                    teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.BLUE);
                } else {
                    teamView.changeImageColor(teamView.images[curPaddler.teamCol][curPaddler.teamRow], Color.PINK);
                    teamView.changeActorColor(teamView.tlables[curPaddler.teamCol][curPaddler.teamRow], Color.PINK);
                }
                //change the label on the boat
                if (i == 0) {
                    boatView.blables[i][j - 1].setText("LHSide" + (j));
                } else {
                    boatView.blables[i][j - 1].setText("RHSide" + (j));
                }
                boatView.blables[i][j - 1].invalidate();
                //recalculate the weights
                boatView.leftweight.setText(Long.toString(thisBoat.leftWeight(thisBoat)));
                boatView.rightweight.setText(Long.toString(thisBoat.rightWeight(thisBoat)));
                boatView.balanceweight.setText(Long.toString(thisBoat.balance(thisBoat)));
                boatView.frontweight.setText(Long.toString(thisBoat.frontWeight(thisBoat)));
                boatView.backweight.setText(Long.toString(thisBoat.backWeight(thisBoat)));
                boatView.balance2weight.setText(Long.toString(thisBoat.balance2(thisBoat)));
            }
        }

        //swapping two people around
        if (hitActorDownPlace == "Boat" && hitActorUpPlace == "Boat"){
            Paddler paddler1 = null;
            Paddler paddler2 = null;
            //do sweep and drummer later

            //get the first paddler
            int i1 = 0;
            int j1 = 0;
            if (hitActorDown.getName().substring(0, 1).equals("L")) {
                i1 = 0;
            } else {
                if (hitActorDown.getName().substring(0, 1).equals("R")) {
                    i1 = 1;
                }
            }
            j1 = Integer.parseInt(hitActorDown.getName().substring(3));
            curPaddler = team.getPaddler(boatView.blables[i1][j1-1].getText().toString(), team);
            if (curPaddler!=null) {
                paddler1 = curPaddler;
                thisBoat.removePaddler(i1, j1 - 1);
            }
            //get the second paddler
            int i2 = 0;
            int j2 = 0;
            if (hitActorUp.getName().substring(0, 1).equals("L")) {
                i2 = 0;
            } else {
                if (hitActorUp.getName().substring(0, 1).equals("R")) {
                    i2 = 1;
                }
            }
            j2 = Integer.parseInt(hitActorUp.getName().substring(3));
            curPaddler = team.getPaddler(boatView.blables[i2][j2-1].getText().toString(), team);
            if (curPaddler!=null) {
                paddler2 = curPaddler;
                thisBoat.removePaddler(i2, j2 - 1);
            }

            //Update the first space
            if (paddler2!=null){
                boatView.blables[i1][j1 - 1].setText(paddler2.name);
                thisBoat.addPaddler(i1,j1-1,paddler2);
            } else {
                if (i1 == 0) {
                    boatView.blables[i1][j1 - 1].setText("LHSide" + (j1));
                } else {
                    boatView.blables[i1][j1 - 1].setText("RHSide" + (j1));
                }

            }
            boatView.blables[i1][j1 - 1].invalidate();

            //Update the second space
            if (paddler1!=null){
                boatView.blables[i2][j2 - 1].setText(paddler1.name);
                thisBoat.addPaddler(i2,j2-1,paddler1);
            } else {
                if (i2 == 0) {
                    boatView.blables[i2][j2 - 1].setText("LHSide" + (j2));
                } else {
                    boatView.blables[i2][j2 - 1].setText("RHSide" + (j2));
                }

            }
            boatView.blables[i2][j2 - 1].invalidate();
            boatView.leftweight.setText(Long.toString(thisBoat.leftWeight(thisBoat)));
            boatView.rightweight.setText(Long.toString(thisBoat.rightWeight(thisBoat)));
            boatView.balanceweight.setText(Long.toString(thisBoat.balance(thisBoat)));
            boatView.frontweight.setText(Long.toString(thisBoat.frontWeight(thisBoat)));
            boatView.backweight.setText(Long.toString(thisBoat.backWeight(thisBoat)));
            boatView.balance2weight.setText(Long.toString(thisBoat.balance2(thisBoat)));

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
