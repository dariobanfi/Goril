package com.ambigioz.goril.screens;

import com.ambigioz.goril.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen extends ScreenAdapter {
	Goril game;

	OrthographicCamera guiCam;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;

	public MainMenuScreen (Goril game) {
		this.game = game;

		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
		highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
		helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
		touchPoint = new Vector3();
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				com.ambigioz.goril.util.Assets.playSound(com.ambigioz.goril.util.Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
				com.ambigioz.goril.util.Assets.playSound(com.ambigioz.goril.util.Assets.clickSound);
				//game.setScreen(new ());
				return;
			}
			if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
				com.ambigioz.goril.util.Assets.playSound(com.ambigioz.goril.util.Assets.clickSound);
//				game.setScreen(new HelpScreen(game));
				return;
			}
			if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
				com.ambigioz.goril.util.Assets.playSound(com.ambigioz.goril.util.Assets.clickSound);
				com.ambigioz.goril.util.Settings.soundEnabled = !com.ambigioz.goril.util.Settings.soundEnabled;
				if (com.ambigioz.goril.util.Settings.soundEnabled) {
//					Assets.music.play();
				}
				else
					com.ambigioz.goril.util.Assets.music.pause();
			}
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);

		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(com.ambigioz.goril.util.Assets.backgroundRegion, 0, 0, 320, 480);
		game.batcher.end();

		game.batcher.enableBlending();
		game.batcher.begin();
		//game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
		game.batcher.draw(com.ambigioz.goril.util.Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
		game.batcher.draw(com.ambigioz.goril.util.Settings.soundEnabled ? com.ambigioz.goril.util.Assets.soundOn : com.ambigioz.goril.util.Assets.soundOff, 0, 0, 64, 64);
		game.batcher.end();
	}

	@Override
	public void render (float delta) {
		update();
		draw();
	}

	@Override
	public void pause () {
		com.ambigioz.goril.util.Settings.save();
	}
}
