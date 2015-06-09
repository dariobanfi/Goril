package com.ambigioz.goril;


import com.ambigioz.goril.controller.InputController;
import com.ambigioz.goril.controller.ShapeController;
import com.ambigioz.goril.models.Ground;
import com.ambigioz.goril.models.Level;
import com.ambigioz.goril.models.Tray;
import com.ambigioz.goril.models.objects.FallingObject;
import com.ambigioz.goril.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.utils.TimeUtils;


public class GameScreen extends ScreenAdapter {
    


    public World world;
    public Box2DDebugRenderer debugRenderer;
    public OrthographicCamera b2dCam;
    public Goril game;
    public Tray tray;
    public Vector2 movement;
    public Array<Body> tempBodies = new Array<>();
    public float w;
    public float h;
    public ShapeController shapeController;
	public Level level;
	public float accumulator;
	public final int PPM = 100;


	public GameScreen(Goril game){
		this.game = game;
        Gdx.input.setInputProcessor(new InputController(this));

        w = 320;
        h = 480;


		b2dCam = new OrthographicCamera();

		b2dCam.setToOrtho(false, w / PPM, h / PPM);
		b2dCam.update();

		debugRenderer = new Box2DDebugRenderer();

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
		game.batcher = new SpriteBatch();
		movement = new Vector2();

		world = new World(new Vector2(0, -8.21f), true);
		shapeController = new ShapeController(world);
	}


	public void initGame(){

		//Ground body
		Ground ground = new Ground(world);
		ground.init(w, h);

		// Tray
		tray = new Tray(world);
		tray.init(w, h);

		level.start(TimeUtils.nanoTime());
	}


	@Override
	public void render(float delta) {

		b2dCam.update();

		// game.batcher jobs
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		accumulator+= Gdx.graphics.getDeltaTime();
		
		if(level.isSpawn()) {
			FallingObject nextFallingObject = level.getNextObject();
			if(nextFallingObject != null) {
				shapeController.spawn(nextFallingObject);
			}
			else {
				showEndLevelMessage();
			}
		}


		game.batcher.setProjectionMatrix(b2dCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegion, 0, 0, w / PPM, h / PPM);


		 world.getBodies(tempBodies);
		 for(Body body : tempBodies){
			 if(body.getUserData()!= null && body.getUserData() instanceof Sprite){
				 Sprite sprite =  (Sprite) body.getUserData();
				 sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
				 sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
				 sprite.draw(game.batcher);
			 }
		 }

		game.batcher.end();


		// Rendering world
        debugRenderer.render(world, b2dCam.combined);
        world.step(Constants.BOX_STEP, Constants.BOX_VELOCITY_ITERATIONS, Constants.BOX_POSITION_ITERATIONS);
        tray.setLinearVelocity(movement);
	}

	public void showEndLevelMessage() {
		// TODO show hold thight message
	}

	@Override
	public void dispose() {
		world.dispose();
		debugRenderer.dispose();
		
	}
}
