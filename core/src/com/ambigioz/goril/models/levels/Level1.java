package com.ambigioz.goril.models.levels;

import com.ambigioz.goril.models.objects.SquareGem;
import com.badlogic.gdx.math.MathUtils;

import java.util.Stack;

/**
 * Created by Dario on 4/18/16.
 */
public class Level1 extends Level {
    public Level1() {
        super(5000);
        levelObjects = new Stack<>();
        levelObjects.add(new SquareGem(MathUtils.random(0, 100), spawnHeight, 25f, 25f));
        levelObjects.add(new SquareGem(MathUtils.random(0, 100), spawnHeight, 35f, 35f));
        levelObjects.add(new SquareGem(MathUtils.random(0, 100), spawnHeight, 15f, 15f));

    }
}
