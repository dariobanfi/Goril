package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.Assets;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SquareGem implements FallingObject {

    private float positionX;
    private float positionY;

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    private float sizeX;
    private float sizeY;

    public SquareGem(float positionX, float positionY, float sizeX, float sizeY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public BodyDef getBodyDef(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        bodyDef.linearDamping = 40.5f;
        bodyDef.angularDamping = 40.5f;

        return bodyDef;
    }


    public FixtureDef getFixtureDef(){
        PolygonShape dynamicPolygon = new PolygonShape();
        dynamicPolygon.setAsBox(sizeX, sizeY);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicPolygon;
        fixtureDef.density = 3.1f;
        fixtureDef.friction = 20.0f;
        fixtureDef.restitution = 0.0f;


        return fixtureDef;
    }

    public Sprite getSprite(){
        Sprite polySprite = new Sprite(Assets.diamondRhombus);
        polySprite.setSize(sizeX * 2, sizeY * 2);
        polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
        return polySprite;
    }
}
