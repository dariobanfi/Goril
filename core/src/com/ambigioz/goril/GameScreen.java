package com.ambigioz.goril;


import com.ambigioz.goril.controller.ShapeController;
import com.ambigioz.goril.models.Ground;
import com.ambigioz.goril.models.Level;
import com.ambigioz.goril.models.Tray;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen implements Screen, InputProcessor {
    
    static final float BOX_STEP=1/60f;
    static final int BOX_VELOCITY_ITERATIONS=8;
    static final int BOX_POSITION_ITERATIONS=3;
    static final float PPM = 100;
    static float WALK_SPEED = 1.0f;
	static float ROTATION_SPEED = 1.0f;
	boolean spawn = false;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera b2dCam;
    private SpriteBatch batcher;
    private Game game;
    private Tray tray;
    private Vector2 movement;
    private float rotation;
    private Array<Body> tempBodies = new Array<Body>();
    private float w;
    private float h;
    private ShapeController shapeController;
	private Level level;


	public GameScreen(Game game){
		this.game = game;
        Gdx.input.setInputProcessor(this);

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

		Timer timer = new Timer();
		timer.schedule(myTask, 1000, 3000);
	}


	public void setLevel(Level level){
		this.level = level;
	}
	
	@Override
	public void show() {

		setupWorld();

		initGame();


//	    shapeController.spawnSquareObject(w / PPM / 2, h / PPM / 1.5f, 50f / PPM);
//	    shapeController.spawnRectangleGemObject(w / PPM / 2, h / PPM / 1f);
//	    shapeController.spawnHexagonalObject(w / PPM / 2, h / PPM / 1, 80f / PPM);
//	    shapeController.spawnEquilateralTriangleObject(w / PPM / 2, h / PPM / 1, 60f / PPM);
//	    shapeController.spawnSphericObject(w / PPM / 2, h / PPM / 1.2f, 10f / PPM);
//	    shapeController.spawnSphericObject(w / PPM / 2, h / PPM / 1.0f, 15f / PPM);
//	    shapeController.spawnSphericObject(w / PPM / 2 / 5.0f, h / PPM / 1.0f, 20f / PPM);
	    
	    
		
	}

	public void setupWorld(){
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, (w / PPM), (h / PPM));
		b2dCam.update();

		batcher = new SpriteBatch();
		movement = new Vector2();

		rotation = 0f;

		world = new World(new Vector2(0, -8.21f), true);
		shapeController = new ShapeController(world);
		debugRenderer = new Box2DDebugRenderer();
	}


	public void initGame(){
		//Ground body
		Ground ground = new Ground(world);
		ground.init(w,h);

		// Tray
		tray = new Tray(world);
		tray.init(w,h);


		level.start();
	}

	TimerTask myTask = new TimerTask() {
		@Override
		public void run() {
			spawn = true;
		}
	};
	
	@Override
	public void render(float delta) {

		if(level.getStatus()!=null) {
			shapeController.spawn(level.getStatus());
		}

		// Batcher jobs
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.setProjectionMatrix(b2dCam.combined);
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.backgroundRegion, 0, 0, w / PPM, h / PPM);


		 world.getBodies(tempBodies);
		 for(Body body : tempBodies){
			 if(body.getUserData()!= null && body.getUserData() instanceof Sprite){
				 Sprite sprite =  (Sprite) body.getUserData();
				 sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
				 sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
				 sprite.draw(batcher);
			 }
		 }

		batcher.end();
		
		
		// Rendering world
        debugRenderer.render(world, b2dCam.combined);  
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);     
        tray.setLinearVelocity(movement);
        tray.setAngularVelocity(rotation);
	}
	@Override
	public void resize(int width, int height) {
		b2dCam.viewportWidth = width / PPM;
		b2dCam.viewportHeight = height / PPM;
		b2dCam.update();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		world.dispose();
		debugRenderer.dispose();
		
	}

	@Override
	public boolean keyDown(int keycode) {

		switch(keycode){
		case(Keys.ESCAPE):
        	((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
		case(Keys.A):
			System.out.println("Pressed UP");
			movement.x = -WALK_SPEED;
			break;
		case(Keys.S):
			System.out.println("Pressed DOWN");
			movement.x = WALK_SPEED;
			break;
		
		case(Keys.LEFT):
			System.out.println("Pressed Left");
			rotation = ROTATION_SPEED;
			break;

		case(Keys.M):
			spawn = true;
			break;

			case(Keys.RIGHT):
				System.out.println("Pressed Right");
				rotation = -ROTATION_SPEED;
				break;
		}


		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case(Keys.A):
			movement.x = 0;
			break;
		case(Keys.S):
			movement.x = 0;
			break;
			
		case(Keys.LEFT):
			System.out.println("Pressed Left");
			rotation = 0;
			break;
		
		case(Keys.RIGHT):
			System.out.println("Pressed Right");
			rotation = 0;
			break;
		
		}
		

		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if(screenX<=w/2){
			movement.x = -WALK_SPEED;
		}
		else{
			movement.x = WALK_SPEED;
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(screenX<=w/2){
			movement.x = 0;
		}
		else{
			movement.x = 0;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
