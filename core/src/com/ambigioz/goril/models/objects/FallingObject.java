package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class FallingObject {

    public float positionX;
    public float positionY;
    public float sizeX;
    public float sizeY;
    private float rotation;
    private float angular;

    public FallingObject(float x, float y, float sizeX, float sizeY, float rotation, float angular) {
        this.rotation = rotation;

        this.positionX = Constants.WIDTH * x / 100 / Constants.PPM;
        if(this.positionX<(sizeX/Constants.PPM/2)){
            this.positionX = sizeX/Constants.PPM;
        }
        else if (this.positionX >= (Constants.WIDTH / Constants.PPM)-sizeX/2/Constants.PPM){
            this.positionX = (Constants.WIDTH - sizeX)/Constants.PPM;
        }
        this.positionY = y / Constants.PPM;
        this.sizeX = sizeX / Constants.PPM;
        this.sizeY = sizeY / Constants.PPM;
        this.rotation = rotation;
        this.angular = angular;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getRotation() {
        return rotation;
    }

    public BodyDef getBodyDef(){
        return null;
    }

    public FixtureDef getFixtureDef(){
        return null;
    }

    public Sprite getSprite() {
        return null;
    }
}
