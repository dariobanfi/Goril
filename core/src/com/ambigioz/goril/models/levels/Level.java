package com.ambigioz.goril.models.levels;

import com.ambigioz.goril.controller.ShapeController;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class Level {

    public                  Stack<LevelPair> levelObjects ;
    public                  float startTime;
    public                  float finishTime;
    private                 ShapeController shapeController;
    private static final    String TAG = Level.class.getSimpleName();
    private                 LevelState levelState;
    private                 float      currentTime;
    private                 boolean    showHoldTight;

    public Level(ShapeController shapeController, Stack<LevelPair> levelObjects) {
        this.shapeController = shapeController;
        this.levelObjects = levelObjects;
        levelState = LevelState.WAITING;
    }


    public void start(float startTime){
        this.startTime = startTime;
        levelState = LevelState.STARTED;
    }

    public void update(float time){
        this.currentTime = time;
        if(isStarted()) {
            if (levelObjects.isEmpty()){
                levelState = LevelState.FINISHED;
                finishTime = time;
            }
            else if (levelObjects.lastElement().getSpawnAtTime() <= time - startTime) {
                Gdx.app.log(TAG, "TIME: " + (time - startTime) + " NEXT OBJ TIME " + levelObjects.lastElement().getSpawnAtTime());
                shapeController.spawn(levelObjects.pop().getObject());
            }
        }
        else if(isFinished()){
            if((currentTime - finishTime) > 25){
                this.setWon();
            }
        }
    }

    public void render(){
        if(isFinished()){
//          Change this value to show the hold tight message at different times
            if((currentTime - finishTime) > 18 && (currentTime - finishTime) < 23){
                showHoldTight = true;
            }
            else{
                showHoldTight = false;
            }

        }
    }

    private enum LevelState {
        WAITING,
        STARTED,
        FINISHED,
        WON,
        LOST
    }

    public boolean isStarted(){
        return levelState.equals(LevelState.STARTED);
    }

    public boolean isFinished(){
        return levelState.equals(LevelState.FINISHED);
    }

    public void setLost(){
        levelState = LevelState.LOST;
    }

    public boolean isLost(){
        return levelState.equals(LevelState.LOST);
    }

    public void setWon(){
        levelState = LevelState.WON;
    }

    public boolean isWon(){
        return levelState.equals(LevelState.WON);
    }

    public boolean isShowHoldTight() {
        return showHoldTight;
    }
}
