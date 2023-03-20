package com.kaijumon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.screens.BattleScreen;
import com.kaijumon.game.screens.MainMenuScreen;
import jdk.tools.jmod.Main;

import java.util.Arrays;

public class KaijumonGame extends Game {

	private SpriteBatch batch;
	private BitmapFont font;
	private Skin skin;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		this.setScreen(new MainMenuScreen(this));
		this.setScreen(new BattleScreen(this, new Trainer(Pikachu), new Trainer(Test)));
	}

	public void render() {
		super.render(); // important!
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
	}

	private Kaijumon Pikachu = new Kaijumon(
			"Pikachu",
			new Stats(100, 10, 10, 5, 5, 10),
			Element.BUG,
			Arrays.asList(Attack.PUNCH, Attack.SLASH),
			Species.CHARALALA
	);

	private Kaijumon Test = new Kaijumon(
			"Test McTested",
			new Stats(100, 10, 10, 5, 5, 10),
			Element.ELECTRIC,
			Arrays.asList(Attack.PUNCH, Attack.SLASH),
			Species.MAGDO
	);



}
