package com.ambigioz.goril;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Goril extends Game {

	public SpriteBatch batcher;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		Assets.load();
		setScreen(new MainMenuScreen(this));
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
