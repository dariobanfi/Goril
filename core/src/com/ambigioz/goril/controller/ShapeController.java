package com.ambigioz.goril.controller;

import com.ambigioz.goril.util.Assets;
import com.ambigioz.goril.models.objects.falling.FallingObject;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class ShapeController {

	private static final String TAG = Shape.class.getSimpleName();

	private World world;
	
	public ShapeController(World world){
		this.world = world;
	}


	public void spawn(FallingObject obj){

		Gdx.app.log(TAG, "Spawning " + obj);

		Body body = world.createBody(obj.getBodyDef());

		body.createFixture(obj.getFixtureDef());

		body.setUserData(obj.getSprite());

	}
}
