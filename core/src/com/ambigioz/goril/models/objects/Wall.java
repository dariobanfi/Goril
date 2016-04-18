package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Wall {

    World world;
    Fixture fixture;
    Body body;

    public Wall(World world) {
        this.world = world;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public Body getBody() {
        return body;
    }

    public void initLeft(float w, float h) {
        BodyDef wallBodyDef = new BodyDef();
        wallBodyDef.position.set(new Vector2((0 - Constants.SCREEN_OFFSET) / Constants.PPM, 0 / Constants.PPM));
        body = world.createBody(wallBodyDef);
        PolygonShape wallBox = new PolygonShape();
        wallBox.setAsBox(.5f / Constants.PPM, h / Constants.PPM);
        fixture = body.createFixture(wallBox, 0.0f);
    }

    public void initRight(float w, float h) {
        BodyDef wallBodyDef = new BodyDef();
        wallBodyDef.position.set(new Vector2((w + Constants.SCREEN_OFFSET) / Constants.PPM, 0 / Constants.PPM));
        body = world.createBody(wallBodyDef);
        PolygonShape wallBox = new PolygonShape();
        wallBox.setAsBox(.5f / Constants.PPM, h / Constants.PPM);
        fixture = body.createFixture(wallBox, 0.0f);
    }
}
