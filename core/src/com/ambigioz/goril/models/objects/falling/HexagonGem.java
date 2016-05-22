package com.ambigioz.goril.models.objects.falling;


import com.ambigioz.goril.util.Assets;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class HexagonGem extends FallingObject {

    public HexagonGem(float positionX, float positionY, float sizeX, float sizeY, float rotation, float angular) {
        super(positionX, positionY, sizeX, sizeY, rotation, angular);
    }

    @Override
    public BodyDef getBodyDef(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX,positionY);
        bodyDef.angle = this.rotation;
        bodyDef.linearDamping = 40.5f;
        bodyDef.angularDamping = 40.5f;

        return bodyDef;
    }

    @Override
    public FixtureDef getFixtureDef(){
        PolygonShape dynamicPolygon = new PolygonShape();
        float alfa = sizeX / 4;
        float beta = (float) Math.sqrt(3) * alfa;
        dynamicPolygon.set(new float[]{ - beta, -alfa,  -beta, alfa,
                0, 2* alfa, beta, alfa, beta, -alfa, 0, -2 * alfa});


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicPolygon;
        fixtureDef.density = 3.1f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;


        return fixtureDef;
    }

    @Override
    public Sprite getSprite(){
        Sprite polySprite = new Sprite(Assets.fallingHexagonRed);
        polySprite.setSize(sizeX, sizeY);
        polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
        return polySprite;
    }

}
