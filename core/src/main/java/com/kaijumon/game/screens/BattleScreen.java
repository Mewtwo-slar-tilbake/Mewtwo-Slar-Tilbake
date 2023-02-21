package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;

public class BattleScreen implements Screen {

    final KaijumonGame game;

    private final Texture kaijumon;

    private Stage stage;
    private TextButton textButton;

    public BattleScreen(final KaijumonGame game) {
        kaijumon = new Texture(Gdx.files.internal("yoda.png"));

        this.game = game;
        stage = new Stage();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.font;
        textButtonStyle.fontColor = Color.BLACK;

        textButton = new TextButton("Fight", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 150, 100);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);

        textButton = new TextButton("Bag", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 50, 100);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);

        textButton = new TextButton("Pokémon", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 140, 50);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);

        textButton = new TextButton("Run", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 50, 50);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        game.batch.begin();

        game.batch.draw(kaijumon, 100, 100, 100, 100);

        game.batch.end();

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
