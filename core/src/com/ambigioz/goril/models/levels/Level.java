package com.ambigioz.goril.models.levels;

import com.ambigioz.goril.models.objects.FallingObject;
import com.ambigioz.goril.models.objects.SquareGem;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.math.MathUtils;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Level {

    public Stack<FallingObject> levelObjects ;

    public boolean isSpawn() {
        return spawn;
    }

    public long startTime;
    public long endTime;
    public boolean spawn;
    public float spawnHeight = Constants.HEIGHT + 20;

    public Level(){
        levelObjects = new Stack<>();
        levelObjects.add(new SquareGem(MathUtils.random(0, 100), spawnHeight, 25f, 25f));
//        levelObjects.add(new SquareGem(MathUtils.random(0, 100), spawnHeight, 35f, 35f));
//        levelObjects.add(new SquareGem(MathUtils.random(0, 100), spawnHeight, 15f, 15f));
    }

    public void start(){
        Timer timer = new Timer();
        timer.schedule(myTask, 500, 5000);
    }


    public FallingObject getNextObject(){
        spawn = false;
        if(levelObjects.size()>0)
            return levelObjects.pop();
        else {
            myTask.cancel();
            return null;
        }
    }



    TimerTask myTask = new TimerTask() {
        @Override
        public void run() {
            spawn = true;
        }
    };
}
