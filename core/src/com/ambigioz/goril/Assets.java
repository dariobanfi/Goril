package com.ambigioz.goril;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static Texture diamondRhombus;
	public static Texture shapeSphere;
	public static Texture shapeHexagon;
	public static Texture shapePolygon;
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
	public static TextureRegion castle;
	public static TextureRegion bobHit;
	public static TextureRegion platform;
	public static BitmapFont font;

	public static Music music;
	public static Sound jumpSound;
	public static Sound highJumpSound;
	public static Sound hitSound;
	public static Sound coinSound;
	public static Sound clickSound;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		background = loadTexture("jungle-background.png");
		//sphere = loadTexture("coconut_test.png");
		shapeSphere = loadTexture("coconut_clean.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		
		diamondRhombus = loadTexture("gem_full.png");
		shapeHexagon = loadTexture("hexagon.png");
		shapePolygon = loadTexture("le_gem.png");
		
		items = loadTexture("items.png");
		mainMenu = new TextureRegion(items, 0, 224, 300, 110);
		pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
		ready = new TextureRegion(items, 320, 224, 192, 32);
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(items, 0, 352, 274, 142);
		soundOff = new TextureRegion(items, 0, 0, 64, 64);
		soundOn = new TextureRegion(items, 64, 0, 64, 64);
		arrow = new TextureRegion(items, 0, 64, 64, 64);
		pause = new TextureRegion(items, 64, 64, 64, 64);

		spring = new TextureRegion(items, 128, 0, 32, 32);
		castle = new TextureRegion(items, 128, 64, 64, 64);


		font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//		music.setLooping(true);
//		music.setVolume(0.5f);
//		if (Settings.soundEnabled) music.play();
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		highJumpSound = Gdx.audio.newSound(Gdx.files.internal("highjump.wav"));
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
		coinSound = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
	}

	public static void playSound (Sound sound) {
		if (Settings.soundEnabled) sound.play(1);
	}
}
