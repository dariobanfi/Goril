package com.ambigioz.goril.models;

import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.physics.box2d.*;

public class Tray {

    private World world;
    public Body trayBody;

    public Tray(World word){
        this.world = word;
    }

    public void init(float w, float h){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set((w / Constants.PPM) / 2 , h / Constants.PPM / 5);
        trayBody = world.createBody(bodyDef);
        PolygonShape dynamicPolygon = new PolygonShape();
        dynamicPolygon.setAsBox((w / Constants.PPM) / 5 , 0.5f / Constants.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicPolygon;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 10.5f;
        fixtureDef.restitution = 0.6f;
        trayBody.createFixture(fixtureDef);
    }

}
