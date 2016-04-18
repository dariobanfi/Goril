package com.ambigioz.goril;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Goril extends Game {

	public SpriteBatch batcher;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		com.ambigioz.goril.util.Assets.load();
		setScreen(new com.ambigioz.goril.screens.MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}


	@Override
	public void dispose () {
		super.dispose();

		getScreen().dispose();
	}
}
