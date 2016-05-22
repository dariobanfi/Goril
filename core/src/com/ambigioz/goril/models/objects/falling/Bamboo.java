package com.ambigioz.goril.models.objects.falling;

import com.ambigioz.goril.util.Assets;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Bamboo extends FallingObject {

    private int sticklength;
    public Bamboo(float positionX, float positionY, float sizeX, float sizeY, float rotation, int sticklength) {
        super(positionX, positionY, sizeX, sizeY, rotation, 0);
        this.sticklength = sticklength;
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
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.0f;

        return fixtureDef;
    }

    @Override
    public Sprite getSprite(){
        Sprite polySprite;
        switch (sticklength) {
            case 3:
                polySprite = new Sprite(Assets.fallingBamboo3);
                break;
            case 4:
                polySprite = new Sprite(Assets.fallingBamboo4);
                break;
            case 5:
                polySprite = new Sprite(Assets.fallingBamboo5);
                break;
            default:
                polySprite = new Sprite(Assets.fallingBamboo3);
                break;
        }

        polySprite.setSize(sizeX * 2, sizeY * 2 * 4.6f);
        polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
        return polySprite;
    }
}
