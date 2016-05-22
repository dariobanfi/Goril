package com.ambigioz.goril.models.objects;

import com.ambigioz.goril.util.Assets;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Coconut extends FallingObject {

    private float size;


    public float getSize() {
        return size;
    }

    /**
     *
     * @param x min 0, max 100
     * @param y
     * @param size
     */
    public Coconut(float x, float y, float rotation, float size) {
        super(x, y, size, size, 0, rotation);
        this.size = size / Constants.PPM;
    }

    @Override
    public BodyDef getBodyDef(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        bodyDef.linearDamping = 40.5f;
        bodyDef.angularDamping = 40.5f;

        return bodyDef;
    }

    @Override
    public FixtureDef getFixtureDef(){
        CircleShape dynamicCircle = new CircleShape();
        dynamicCircle.setRadius(size);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicCircle;
        fixtureDef.density = 3.1f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        return fixtureDef;
    }

    @Override
    public Sprite getSprite(){
        Sprite polySprite = new Sprite(Assets.shapeSphere);
        polySprite.setSize(size * 2.9f, size * 2.9f);
        polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
        return polySprite;
    }
}
