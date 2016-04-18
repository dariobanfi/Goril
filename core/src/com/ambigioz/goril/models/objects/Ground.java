package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Ground {

    World world;
    Fixture fixture;
    Body body;

    public Ground(World world) {
        this.world = world;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public Body getBody() {
        return body;
    }

    public void init(float w, float h) {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0 / Constants.PPM, 10 / Constants.PPM));
        body = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(w / Constants.PPM, .5f / Constants.PPM);
        fixture = body.createFixture(groundBox, 0.0f);
    }

}
