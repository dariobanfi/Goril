package com.ambigioz.goril.screens;


import com.ambigioz.goril.Goril;
import com.ambigioz.goril.controller.GameState;
import com.ambigioz.goril.controller.InputController;
import com.ambigioz.goril.controller.ShapeController;
import com.ambigioz.goril.models.levels.Level;
import com.ambigioz.goril.models.levels.Level1;
import com.ambigioz.goril.models.objects.FallingObject;
import com.ambigioz.goril.models.objects.Ground;
import com.ambigioz.goril.models.objects.Tray;
import com.ambigioz.goril.models.objects.Wall;
import com.ambigioz.goril.util.Assets;
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
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class GameScreen extends ScreenAdapter {

    private final static String TAG = GameScreen.class.getSimpleName();
    public World world;
    public Box2DDebugRenderer debugRenderer;
    public OrthographicCamera b2dCam;
    public Goril game;
    public Tray tray;
    public Ground ground;
    public Wall wallLeft;
    public Wall wallRight;
    public Array<Body> tempBodies = new Array<>();
    public float w;
    public float h;
    public ShapeController shapeController;
    public Level level;
    public float accumulator;

    private GameState gameState;


    public GameScreen(Goril game) {
        this.game = game;
        Gdx.input.setInputProcessor(new InputController(this));

        w = Constants.WIDTH;
        h = Constants.HEIGHT;


        b2dCam = new OrthographicCamera();

        b2dCam.setToOrtho(false, w / Constants.PPM, h / Constants.PPM);
        b2dCam.update();

        debugRenderer = new Box2DDebugRenderer();

        setLevel(new Level1());
    }


    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if (ground.getFixture().equals(fixtureA) || ground.getFixture().equals(fixtureB)) {
                    gameState = GameState.LOSE;
                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void show() {
        setupWorld();
        initGame();
    }

    public void setupWorld() {
        game.batcher = new SpriteBatch();
        world = new World(new Vector2(0, -8.21f), true);
        createCollisionListener();
        shapeController = new ShapeController(world);
    }


    public void initGame() {

        // Ground and walls
        ground = new Ground(world);
        wallLeft = new Wall(world);
        wallRight = new Wall(world);

        ground.init(w, h);
        wallRight.initRight(w, h);
        wallLeft.initLeft(w, h);

        // Tray
        tray = new Tray(world);
        tray.init(w, h);

        level.start();
        gameState = GameState.PLAYING;
    }

    public void update() {
        b2dCam.update();
    }

    public void draw() {
        // game.batcher jobs
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        accumulator += Gdx.graphics.getDeltaTime();

        if (level.isSpawn()) {
            FallingObject nextFallingObject = level.getNextObject();
            if (nextFallingObject != null) {
                shapeController.spawn(nextFallingObject);
            } else {
                gameState = GameState.HOLD_TIGHT;
            }
        }


        game.batcher.setProjectionMatrix(b2dCam.combined);
        game.batcher.begin();
        game.batcher.disableBlending();
        game.batcher.draw(Assets.backgroundRegion, 0, 0, w / Constants.PPM, h / Constants.PPM);
        game.batcher.enableBlending();

        world.getBodies(tempBodies);
        for (Body body : tempBodies) {
            if (body.getUserData() != null && body.getUserData() instanceof Sprite) {
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(game.batcher);
            }
        }


        if (gameState.equals(GameState.PLAYING)) {

        } else if (gameState.equals(GameState.HOLD_TIGHT)) {
            showHoldTightMessage();
        } else if (gameState.equals(GameState.LOSE)) {
            showLoseMessage();
        }

        game.batcher.end();

        // Rendering world
        debugRenderer.render(world, b2dCam.combined);
        world.step(Constants.BOX_STEP, Constants.BOX_VELOCITY_ITERATIONS, Constants.BOX_POSITION_ITERATIONS);

        tray.render();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    private void showHoldTightMessage() {
        game.batcher.draw(com.ambigioz.goril.util.Assets.ready, 55 / Constants.PPM, 300 / Constants.PPM, 200 / Constants.PPM, 30 / Constants.PPM);
    }

    public void showLoseMessage() {
        game.batcher.draw(Assets.gameOver, 60 / Constants.PPM, 300 / Constants.PPM, 200 / Constants.PPM, 120 / Constants.PPM);
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }


    public interface GameScreenInterface {
        public void endObjects();

        public void spawnObject(FallingObject object);
    }
}
