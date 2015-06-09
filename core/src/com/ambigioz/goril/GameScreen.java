package com.ambigioz.goril;


import com.ambigioz.goril.controller.InputController;
import com.ambigioz.goril.controller.ShapeController;
import com.ambigioz.goril.models.Ground;
import com.ambigioz.goril.models.Level;
import com.ambigioz.goril.models.Tray;
import com.ambigioz.goril.models.objects.FallingObject;
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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ambigioz.goril.util.Constants;


public class GameScreen implements Screen {
    

    static final float PPM = 100;

	boolean spawn = false;
    public World world;
    public Box2DDebugRenderer debugRenderer;
    public OrthographicCamera b2dCam;
    public SpriteBatch batcher;
    public Goril game;
    public Tray tray;
    public Vector2 movement;
    public float rotation;
    public Array<Body> tempBodies = new Array<Body>();
    public float w;
    public float h;
    public ShapeController shapeController;
	public Level level;


	public GameScreen(Goril game){
		this.game = game;
        Gdx.input.setInputProcessor(new InputController(this));

        w = 360;
        h = 480;

		setLevel(new Level());
	}


	public void setLevel(Level level){
		this.level = level;
	}
	
	@Override
	public void show() {
		setupWorld();
		initGame();
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
		ground.init(w, h);

		// Tray
		tray = new Tray(world);
		tray.init(w, h);


		level.start();
	}


	@Override
	public void render(float delta) {

		if(level.isSpawn()) {
			FallingObject nextFallingObject = level.getNextObject();
			if(nextFallingObject != null) {
				shapeController.spawn(nextFallingObject);
			}
			else {
				showEndLevelMessage();
			}
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
        world.step(Constants.BOX_STEP, Constants.BOX_VELOCITY_ITERATIONS, Constants.BOX_POSITION_ITERATIONS);
        tray.setLinearVelocity(movement);
        tray.setAngularVelocity(rotation);
	}

	public void showEndLevelMessage() {
		// TODO show hold thight message
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



}
