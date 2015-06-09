package com.ambigioz.goril.controller;

import com.ambigioz.goril.GameScreen;
import com.ambigioz.goril.MainMenuScreen;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputController implements InputProcessor {

    public GameScreen gameScreen;

    public InputController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch(keycode){
            case(Input.Keys.ESCAPE):
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(gameScreen.game));
            case(Input.Keys.A):
                System.out.println("Pressed UP");
                gameScreen.movement.x = -Constants.WALK_SPEED;
                break;
            case(Input.Keys.S):
                System.out.println("Pressed DOWN");
                gameScreen.movement.x = Constants.WALK_SPEED;
                break;

            case(Input.Keys.LEFT):
                System.out.println("Pressed Left");
                gameScreen.tray.setAngularVelocity(Constants.ROTATION_SPEED);
                break;

            case(Input.Keys.RIGHT):
                System.out.println("Pressed Right");
                gameScreen.tray.setAngularVelocity(-Constants.ROTATION_SPEED);
                break;
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case(Input.Keys.A):
                gameScreen.movement.x = 0;
                break;
            case(Input.Keys.S):
                gameScreen.movement.x = 0;
                break;

            case(Input.Keys.LEFT):
                System.out.println("Pressed Left");
                gameScreen.tray.setAngularVelocity(0);
                break;

            case(Input.Keys.RIGHT):
                System.out.println("Pressed Right");
                gameScreen.tray.setAngularVelocity(0);
                break;

        }


        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(screenX<=gameScreen.w/2){
            gameScreen.movement.x = -Constants.WALK_SPEED;
        }
        else{
            gameScreen.movement.x = Constants.WALK_SPEED;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX<=gameScreen.w/2){
            gameScreen.movement.x = 0;
        }
        else{
            gameScreen.movement.x = 0;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
