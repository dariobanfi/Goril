package com.ambigioz.goril.models.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public interface FallingObject {

    BodyDef getBodyDef();

    FixtureDef getFixtureDef();

    Sprite getSprite();

}
