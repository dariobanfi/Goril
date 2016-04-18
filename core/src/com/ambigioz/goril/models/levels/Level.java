package com.ambigioz.goril.models.levels;

import com.ambigioz.goril.models.objects.FallingObject;
import com.ambigioz.goril.util.Constants;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Level {

    public Stack<FallingObject> levelObjects ;
    public long startTime;
    public long endTime;
    public boolean spawn;
    public float spawnHeight = Constants.HEIGHT + 20;
    long levelPeriod;
    TimerTask myTask = new TimerTask() {
        @Override
        public void run() {
            spawn = true;
        }
    };

    public Level(long levelPeriod) {
        this.levelPeriod = levelPeriod;
    }

    public boolean isSpawn() {
        return spawn;
    }

    public void start(){
        Timer timer = new Timer();
        timer.schedule(myTask, 500, this.levelPeriod);
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
}
