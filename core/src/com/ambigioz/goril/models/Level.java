package com.ambigioz.goril.models;

import com.ambigioz.goril.models.objects.FallingObject;
import com.ambigioz.goril.models.objects.SquareGem;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.math.MathUtils;
import javafx.util.Pair;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Level {

    public Stack<Pair<FallingObject, Float>> levelObjects ;
    private long startTime;



    public Level(){
        levelObjects.add(new SquareGem(MathUtils.random(0, w / Constants.PPM), h / Constants.PPM, 25f / Constants.PPM,25f / Constants.PPM);
    }
    

    public void start(){
        Timer timer = new Timer();
        timer.schedule(myTask, 1000, 3000);
        System.currentTimeMillis();
    }


    public FallingObject getStatus(){
        return null;
    }


    TimerTask myTask = new TimerTask() {
        @Override
        public void run() {
            if()
        }
    };
}
