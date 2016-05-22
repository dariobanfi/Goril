package com.ambigioz.goril.models.objects.falling;

import com.ambigioz.goril.util.Assets;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PurpleGem extends FallingObject{

    public PurpleGem(float positionX, float positionY, float sizeX, float sizeY, float rotation, float angular) {
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
        dynamicPolygon.set(new float[]{
                0.11250001937150955f ,0.21375001966953278f ,
                0.07250002771615982f , 0.2512500286102295f,
                0.07499996572732925f ,0.73375004529953f ,
                0.1237499788403511f, 0.784375011920929f ,
                0.862500011920929f ,0.7837499976158142f,
                0.9274999499320984f,0.7287499308586121f ,
                0.925000011920929f,0.27625003457069397f,
                0.8725000023841858f,0.21125002205371857f});

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicPolygon;
        fixtureDef.density = 3.1f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;


        return fixtureDef;
    }

    @Override
    public Sprite getSprite(){
        Sprite polySprite = new Sprite(Assets.fallingGemPurple);
        polySprite.setSize(sizeX , sizeY);
        polySprite.setOrigin(polySprite.getWidth(), polySprite.getHeight());
        return polySprite;
    }
}
