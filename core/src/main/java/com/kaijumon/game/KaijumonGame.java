package com.kaijumon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.controller.SoundManager;
import com.kaijumon.game.screens.*;
import com.kaijumon.game.dialog.DialogueSystem;
import com.kaijumon.game.screens.BattleScreen;
import com.kaijumon.game.screens.WorldScreen;
import com.kaijumon.game.screens.MainMenuScreen;
import com.kaijumon.game.screens.ui.InventoryScreen;

public class KaijumonGame extends Game {

	public WorldScreen worldScreen;

	public HelpScreen helpScreen;

	public MainMenuScreen mainMenu;
	public PauseScreen pauseScreen;
	public InventoryScreen inventoryScreen;

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
		DialogueSystem.init(this);
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
		else if (this.getScreen().getClass() == WorldScreen.class)
			return ScreenType.World;
		else if (this.getScreen().getClass() == BattleScreen.class)
			return ScreenType.Battle;
		else if (this.getScreen().getClass() == HelpScreen.class)
			return ScreenType.Help;
		else if (this.getScreen().getClass() == PauseScreen.class)
			return ScreenType.Pause;
		else if (this.getScreen().getClass() == InventoryScreen.class)
			return ScreenType.Inventory;
		else
			throw new RuntimeException("Unknown screen type, extend the ScreenType ENUM and update gamescreen type in kaijumon game");
	}



}
