package com.ambigioz.goril.models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ambigioz.goril.util.Constants;


public class Ground {

    World world;

    public Ground(World  world){
        this.world = world;
    }

    public void init(float w, float h) {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0 / Constants.PPM, 10 / Constants.PPM));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(w / Constants.PPM, .5f / Constants.PPM);
        groundBody.createFixture(groundBox, 0.0f);
    }

}
