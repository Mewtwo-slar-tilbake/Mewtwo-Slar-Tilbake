package com.kaijumon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.controller.SoundManager;
import com.kaijumon.game.screens.BattleScreen;
import com.kaijumon.game.screens.GameScreen;
import com.kaijumon.game.screens.MainMenuScreen;
import com.kaijumon.game.screens.ScreenType;

public class KaijumonGame extends Game {

	public GameScreen gameScreen;

	private SpriteBatch batch;
	private BitmapFont font;
	private Skin skin;

	private SoundManager soundManager;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		this.setScreen(new MainMenuScreen(this));
		soundManager = SoundManager.getInstance();
		soundManager.setGame(this);
	}

	public void render() {
		super.render(); // important!
		SoundManager.getInstance().update();
	}

	public Skin getSkin() {
		return this.skin;
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

	public BitmapFont getFont() {
		return this.font;
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		soundManager.dispose();
	}
	public ScreenType getScreenType() {

		if (this.getScreen().getClass() == MainMenuScreen.class)
			return ScreenType.MainMenu;
		else if (this.getScreen().getClass() == GameScreen.class)
			return ScreenType.World;
		else if (this.getScreen().getClass() == BattleScreen.class)
			return ScreenType.Battle;
		else
			throw new RuntimeException("Unknown screen type, extend the ScreenType ENUM and update gamescreen type in kaijumon game");
	}



}
