package com.ambigioz.goril.models;

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
    public boolean spawn;


    public Level(){
        levelObjects = new Stack<>();
        levelObjects.add(new SquareGem(200, 200, 25f, 25f));
        levelObjects.add(new SquareGem(200, 200, 35f, 35f));
        levelObjects.add(new SquareGem(200, 200, 45f, 45f));
    }


    public void start(long startTime){
        this.startTime = startTime;
        Timer timer = new Timer();
        timer.schedule(myTask, 500, 3000);
        System.currentTimeMillis();
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
