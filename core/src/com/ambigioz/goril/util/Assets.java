package com.ambigioz.goril.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static Texture fallingGemBlue;
	public static Texture fallingCoconutHairy;
	public static Texture fallingHexagonRed;
    public static Texture fallingGemGreen;
    public static Texture fallingBamboo3;
    public static Texture fallingBamboo4;
    public static Texture fallingBamboo5;
    public static Texture fallingGemPointy;
    public static Texture fallingGemPurple;

	public static TextureRegion backgroundRegion;

	public static Texture items;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	public static TextureRegion spring;
	public static BitmapFont font;

	public static Music music;
	public static Sound clickSound;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		background = loadTexture("background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);


		// Shapes
		fallingCoconutHairy = loadTexture("fallingobj_coconut_hairy.png");
		fallingGemBlue = loadTexture("fallingobj_gem_blue.png");
		fallingHexagonRed = loadTexture("fallingobj_hexagon_red.png");
        fallingGemGreen = loadTexture("fallingobj_gem_green.png");
        fallingBamboo3 = loadTexture("fallingobj_bamboo_3.png");
        fallingBamboo4 = loadTexture("fallingobj_bamboo_4.png");
        fallingBamboo5 = loadTexture("fallingobj_bamboo_5.png");
        fallingGemPointy = loadTexture("fallingobj_gem_pointy.png");
        fallingGemPurple = loadTexture("fallingobj_gem_purple.png");


		items = loadTexture("items.png");
		mainMenu = new TextureRegion(items, 0, 224, 300, 110);
		pauseMenu = new TextureRegion(items, 224,  128, 192, 96);
		ready = new TextureRegion(items, 320, 224, 192, 32);
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(items, 0, 352, 274, 142);
		soundOff = new TextureRegion(items, 0, 0, 64, 64);
		soundOn = new TextureRegion(items, 64, 0, 64, 64);
		arrow = new TextureRegion(items, 0, 64, 64, 64);
		pause = new TextureRegion(items, 64, 64, 64, 64);

		spring = new TextureRegion(items, 128, 0, 32, 32);


		font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//		music.setLooping(true);
//		music.setVolume(0.5f);
//		if (Settings.soundEnabled) music.play();
	}

	public static void playSound (Sound sound) {
//		if (com.ambigioz.goril.util.Settings.soundEnabled) sound.play(1);
	}
}
