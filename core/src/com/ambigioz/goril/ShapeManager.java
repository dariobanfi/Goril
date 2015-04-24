package com.ambigioz.goril;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class ShapeManager {

	private World world;
	
	public ShapeManager(World world){
		this.world = world;
	}
	
	public void spawnSphericObject(float x, float y, float size){
	    
	    //Dynamic Body  
	    BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(x, y);  
	    Body body = world.createBody(bodyDef);  
	    CircleShape dynamicCircle = new CircleShape();  
	    dynamicCircle.setRadius(size);
	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicCircle;
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.5f;  
	    fixtureDef.restitution = 0.6f;  
	    body.createFixture(fixtureDef);
	    
	    Sprite sprite = new Sprite(Assets.shapeSphere);
	    // use this for hairy coconut
	    //sprite.setSize(size * 2 * 1.45f, size * 2 * 1.45f);
	    sprite.setSize(size * 2 , size * 2);
	    sprite.setOrigin(sprite.getWidth()  / 2 + 0f, sprite.getHeight() / 2 + 0f);
	    body.setUserData(sprite);
	}
	
	public void spawnSquareObject(float x, float y, float size){
		
	    // Body Definition
	    BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(x, y);
	    
	    PolygonShape dynamicPolygon = new PolygonShape();
	    dynamicPolygon.setAsBox(size, size);
	    
	    Body body = world.createBody(bodyDef);

	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicPolygon;
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.2f;  
	    fixtureDef.restitution = 0.0f;
	    
	    body.createFixture(fixtureDef);
	    
	    Sprite polySprite = new Sprite(Assets.diamondRhombus);
	    polySprite.setSize(size * 2, size * 2);
	    polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
	    body.setUserData(polySprite);
	    
	   
	}
	
	/**
	 * @param x
	 * @param y
	 * @param size (the longest diameter of the hexagon)
	 */
	public void spawnHexagonalObject(float x, float y, float size){
	    // Body Definition
	    BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(x, y);
	    
	    PolygonShape dynamicPolygon = new PolygonShape();
	    
	    float alfa = size / 4;
	    float beta = (float) Math.sqrt(3) * alfa;
	    dynamicPolygon.set(new float[]{ - beta, -alfa,  -beta, alfa,
	    		0, 2* alfa, beta, alfa, beta, -alfa, 0, -2 * alfa});
	    //bodyDef.angle = 30 * MathUtils.degreesToRadians;
	    
	    Body body = world.createBody(bodyDef);


	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicPolygon;
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.2f;  
	    fixtureDef.restitution = 0.6f;  
	    
	    body.createFixture(fixtureDef);
	    
	    Sprite polySprite = new Sprite(Assets.shapeHexagon);
	    polySprite.setSize(size, size );
	    polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
	    body.setUserData(polySprite);
	}
	
	public void spawnEquilateralTriangleObject(float x, float y, float base){
		
	    BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(x, y);
		
	    PolygonShape dynamicPolygon = new PolygonShape();
	    
	    float height = (float) (Math.sqrt(3)*base/2);
	    dynamicPolygon.set(new float[]{base / 2, height, 0, 0,  base, 0});
	    
	    Body body = world.createBody(bodyDef);

	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicPolygon;
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.2f;  
	    fixtureDef.restitution = 0.6f;  
	    
	    body.createFixture(fixtureDef);
	    
//	    Sprite polySprite = new Sprite(Assets.diamondRhombus);
//	    polySprite.setSize(size * 2, size * 2);
//	    polySprite.setOrigin(polySprite.getWidth()  / 2, polySprite.getHeight() / 2);
//	    body.setUserData(polySprite);
	}
	
	public void spawnRectangleGemObject(float x, float y){
	    BodyDef bodyDef = new BodyDef();  
	    bodyDef.type = BodyType.DynamicBody;  
	    bodyDef.position.set(x, y);
		
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
	    
	    Body body = world.createBody(bodyDef);

	    FixtureDef fixtureDef = new FixtureDef();  
	    fixtureDef.shape = dynamicPolygon;
	    fixtureDef.density = 1.0f;  
	    fixtureDef.friction = 0.2f;  
	    fixtureDef.restitution = 0.6f;  
	    
	    body.createFixture(fixtureDef);
	    
	    Sprite polySprite = new Sprite(Assets.shapePolygon);
	    polySprite.setSize(polySprite.getWidth() / GameScreen.PPM, polySprite.getHeight() / GameScreen.PPM);
	    //polySprite.setRotation(30);
	    polySprite.setOrigin(0, 0);
	    body.setUserData(polySprite);
	}
	
}
