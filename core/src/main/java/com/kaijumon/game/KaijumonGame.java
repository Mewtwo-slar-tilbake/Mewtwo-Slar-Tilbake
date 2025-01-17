package com.kaijumon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.controller.SoundManager;
import com.kaijumon.game.dialog.DialogueSystem;
import com.kaijumon.game.screens.*;
import com.kaijumon.game.utils.Consts;

public class KaijumonGame extends Game {

	public WorldScreen worldScreen;

	public HelpScreen helpScreen;

	public MainMenuScreen mainMenu;
	public PauseScreen pauseScreen;

	private SpriteBatch batch;
	private BitmapFont font;
	private Skin skin;

	private SoundManager soundManager;

	/**
	 * Create the game.
	 */
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		skin = new Skin(Gdx.files.internal(Consts.skinPath));

		this.setScreen(new MainMenuScreen(this));
		soundManager = SoundManager.getInstance();
		soundManager.setGame(this);
		DialogueSystem.init(this);
	}

	/**
	 * Render the game.
	 */
	public void render() {
		super.render(); // important!
		SoundManager.getInstance().update();
	}

	/**
	 * Get the skin.
	 * @return the skin
	 */
	public Skin getSkin() {
		return this.skin;
	}

	/**
	 * Get the batch.
	 * @return the batch
	 */
	public SpriteBatch getBatch() {
		return this.batch;
	}

	/**
	 * Get the font.
	 * @return the font
	 */
	public BitmapFont getFont() {
		return this.font;
	}

	/**
	 * dispose of the game.
	 */
	public void dispose() {
		batch.dispose();
		font.dispose();
		soundManager.dispose();
	}

	/**
	 * Gets the screen type of the current screen.
	 * @return the screen type
	 */
	public ScreenType getScreenType() {

		if (this.getScreen().getClass() == MainMenuScreen.class)
			return ScreenType.MainMenu;
		else if (this.getScreen().getClass() == WorldScreen.class)
			return ScreenType.World;
		else if (this.getScreen().getClass() == BattleScreen.class)
			return ScreenType.Battle;
		else if (this.getScreen().getClass() == HelpScreen.class)
			return ScreenType.Help;
		else if (this.getScreen().getClass() == PauseScreen.class)
			return ScreenType.Pause;
		else
			throw new RuntimeException("Unknown screen type, extend the ScreenType ENUM and update gamescreen type in kaijumon game");
	}



}
