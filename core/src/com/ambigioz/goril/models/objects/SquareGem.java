package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.util.Assets;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SquareGem extends FallingObject {


    /**
     *
     * @param positionX min 0, max 100
     * @param positionY
     * @param sizeX
     * @param sizeY
     */
    public SquareGem(float positionX, float positionY, float sizeX, float sizeY, float rotation) {
        super(positionX, positionY, sizeX, sizeY, rotation, 0);
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
        fixtureDef.friction = 0.0f;
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
