package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kaijumon.game.KaijumonGame;

public class HelpScreen implements Screen {
    private KaijumonGame game;
    private Stage stage;
    private final TextButton textButton;

    private final Texture background;



    public HelpScreen(final KaijumonGame game){
        this.game = game;
        stage = new Stage();

        background = new Texture(Gdx.files.internal("helpscreen.png"));

        Table uiRoot = new Table();

        Table buttonTable = new Table();

        textButton = new TextButton("Exit", game.getSkin());
        textButton.setPosition(stage.getWidth()/2-10, (stage.getHeight()/2)-40);
        textButton.getLabel().setFontScale(1);
        stage.addActor(textButton);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleExitButtonEvent();
            }
        });

        uiRoot.add(buttonTable);

        stage.addActor(uiRoot);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().end();
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
        background.dispose();
        stage.dispose();

    }

    private void handleExitButtonEvent() {
        game.mainMenu = new MainMenuScreen(game);
        game.setScreen(game.mainMenu);
        dispose();
    }
}
