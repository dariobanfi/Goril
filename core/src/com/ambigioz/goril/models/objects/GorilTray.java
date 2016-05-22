package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GorilTray {

    public Body trayBody;
    public Vector2 movement;
    public Fixture fixture;
    private World world;

    public GorilTray(World word){
        this.world = word;
        movement = new Vector2();
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void init(float w, float h){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set((w / Constants.PPM) / 2 , h / Constants.PPM / 5);
        trayBody = world.createBody(bodyDef);
        PolygonShape dynamicPolygon = new PolygonShape();
        dynamicPolygon.setAsBox((w / Constants.PPM) / 6 , 4f / Constants.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicPolygon;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.6f;
        fixture = trayBody.createFixture(fixtureDef);
    }


    public void setLinearVelocity(Vector2 velocity){
        trayBody.setLinearVelocity(velocity);
    }

    public void setAngularVelocity(float rotation){
        trayBody.setAngularVelocity(rotation);
    }

    public void checkMaxAngle(){
        if(trayBody.getAngle() >= 0.7){
            trayBody.setAngularVelocity(0);
        }
        else if(trayBody.getAngle() <= -0.7){
            trayBody.setAngularVelocity(0);
        }
    }

    private void checkMaxDistance() {
        if (trayBody.getPosition().x * Constants.PPM < 0 - Constants.SCREEN_OFFSET && movement.x<0) {
            this.movement.x = 0;
        } else if (trayBody.getPosition().x * Constants.PPM > Constants.WIDTH + Constants.SCREEN_OFFSET && movement.x>0) {
            this.movement.x = 0;
        }
    }

    public boolean canGoLeft(){
        return ! (trayBody.getPosition().x * Constants.PPM < 0 - Constants.SCREEN_OFFSET);
    }

    public boolean canGoRight(){
        return ! (trayBody.getPosition().x * Constants.PPM > Constants.WIDTH + Constants.SCREEN_OFFSET);
    }

    /**
     * Checks wether the rotation value does not exceed the limit
     */
    public void render(){
        this.setLinearVelocity(movement);
        checkMaxDistance();
        checkMaxAngle();
    }


}
