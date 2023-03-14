package com.kaijumon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.screens.BattleScreen;
import com.kaijumon.game.screens.MainMenuScreen;

public class KaijumonGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public Skin skin;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		this.setScreen(new BattleScreen(this));
	}

	public void render() {
		super.render(); // important!
	}



	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
