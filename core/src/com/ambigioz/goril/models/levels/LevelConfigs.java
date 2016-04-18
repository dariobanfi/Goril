package com.ambigioz.goril.models.levels;

import com.ambigioz.goril.models.objects.SquareGem;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.math.MathUtils;

import java.util.Stack;

public class LevelConfigs {

    public static Stack<LevelPair>  getLevel1() {
        Stack<LevelPair> levelObjects;
        levelObjects = new Stack<>();
//        levelObjects.add(new LevelPair(new SquareGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 15f, 15f), 8.0));
//        levelObjects.add(new LevelPair(new SquareGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 35f, 35f), 4.0));
        levelObjects.add(new LevelPair(new SquareGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 25f, 25f), 0.5));
        return levelObjects;
    }
}
