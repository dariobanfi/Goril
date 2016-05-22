package com.ambigioz.goril.models.levels;

import com.ambigioz.goril.models.objects.falling.Bamboo;
import com.ambigioz.goril.models.objects.falling.CoconutHairy;
import com.ambigioz.goril.models.objects.falling.HexagonGem;
import com.ambigioz.goril.models.objects.falling.PurpleGem;
import com.ambigioz.goril.models.objects.falling.SquareGem;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.math.MathUtils;

import java.util.Stack;

public class LevelConfigs {

    public static Stack<LevelPair>  getLevel1() {
        Stack<LevelPair> levelObjects;
        levelObjects = new Stack<>();
//        levelObjects.add(new LevelPair(
//                new CoconutHairy(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 25f, 25f),
//                3.5
//        ));
//        levelObjects.add(new LevelPair(
//                new SquareGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 35f, 35f, 0, SquareGem.SquareGemType.BLUE),
//                2.5
//        ));
//        levelObjects.add(new LevelPair(
//                new SquareGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 35f, 35f, 45f, SquareGem.SquareGemType.GREEN),
//                0.5
//        ));
//        levelObjects.add(new LevelPair(
//                new Bamboo(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 45f, 6f, 0f, 3),
//                0.5
//        ));
//        levelObjects.add(new LevelPair(
//                new Bamboo(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 65f, 6f, 90f, 4),
//                0.5
//        ));
//        levelObjects.add(new LevelPair(
//                new HexagonGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 40f, 40f, 0f, 0),
//                0.5
//        ));
        levelObjects.add(new LevelPair(
                new PurpleGem(MathUtils.random(0, 100), Constants.SPAWN_HEIGHT, 60f, 40f, 0f, 0),
                0.5
        ));
        return levelObjects;
    }

    public static Stack<LevelPair>  getLevel2() {
        return null;
    }

    public static Stack<LevelPair>  getLevel3() {
        return null;
    }

    public static Stack<LevelPair>  getLevel4() {
        return null;
    }
}
