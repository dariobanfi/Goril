package com.ambigioz.goril.controller;

import com.ambigioz.goril.screens.GameScreen;
import com.ambigioz.goril.screens.MainMenuScreen;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputController implements InputProcessor {

    public GameScreen gameScreen;

    boolean moveLeftDown;
    boolean moveRightDown;
    boolean rotateLeftDown;
    boolean rotateRightDown;

    public InputController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch(keycode){
            case(Input.Keys.ESCAPE):
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(gameScreen.game));
            case(Input.Keys.A):
                moveLeftDown = true;
                if(gameScreen.gorilTray.canGoLeft())
                    gameScreen.gorilTray.movement.x = -Constants.WALK_SPEED;
                break;
            case(Input.Keys.S):
                moveRightDown = true;
                if(gameScreen.gorilTray.canGoRight())
                    gameScreen.gorilTray.movement.x = Constants.WALK_SPEED;
                break;

            case(Input.Keys.LEFT):
                rotateLeftDown = true;
                gameScreen.gorilTray.setAngularVelocity(Constants.ROTATION_SPEED);
                break;

            case(Input.Keys.RIGHT):
                rotateRightDown = true;
                gameScreen.gorilTray.setAngularVelocity(-Constants.ROTATION_SPEED);
                break;
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case(Input.Keys.A):
                moveLeftDown = false;
                if(!moveRightDown)
                    gameScreen.gorilTray.movement.x = 0;
                break;
            case(Input.Keys.S):
                moveRightDown = false;
                if(!moveLeftDown)
                    gameScreen.gorilTray.movement.x = 0;
                break;

            case(Input.Keys.LEFT):
                rotateLeftDown = false;
                if(!rotateRightDown)
                    gameScreen.gorilTray.setAngularVelocity(0);
                break;

            case(Input.Keys.RIGHT):
                rotateRightDown = false;
                if(!rotateLeftDown)
                    gameScreen.gorilTray.setAngularVelocity(0);
                break;

        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(screenX<=gameScreen.w/2){
            gameScreen.gorilTray.movement.x = -Constants.WALK_SPEED;
        }
        else{
            gameScreen.gorilTray.movement.x = Constants.WALK_SPEED;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX<=gameScreen.w/2){
            gameScreen.gorilTray.movement.x = 0;
        }
        else{
            gameScreen.gorilTray.movement.x = 0;
        }
        return true;
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
