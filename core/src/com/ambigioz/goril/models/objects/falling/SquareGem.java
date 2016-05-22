package com.ambigioz.goril.models.objects.falling;

import com.ambigioz.goril.util.Assets;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class SquareGem extends com.ambigioz.goril.models.objects.falling.FallingObject {

    protected SquareGemType type;

    public enum SquareGemType {
        GREEN,
        BLUE
    }

    public SquareGem(float positionX, float positionY, float sizeX, float sizeY, float rotation, SquareGemType type) {
        super(positionX, positionY, sizeX, sizeY, rotation, 0);
        this.type = type;
    }

    @Override
    public BodyDef getBodyDef(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        bodyDef.angle = this.rotation;
        bodyDef.linearDamping = 40.5f;
        bodyDef.angularDamping = 40.5f;

        return bodyDef;
    }

    @Override
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

    @Override
    public Sprite getSprite(){
        Sprite polySprite;
        if (type.equals(SquareGemType.BLUE))
            polySprite = new Sprite(Assets.fallingGemBlue);
        else
            polySprite = new Sprite(Assets.fallingGemGreen);

        polySprite.setSize(sizeX * 2, sizeY * 2);
        polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
        return polySprite;
    }
}
