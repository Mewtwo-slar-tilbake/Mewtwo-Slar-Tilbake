package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kaijumon.game.KaijumonGame;

public class PauseScreen implements Screen {
    KaijumonGame game;
    private Stage stage;


    public PauseScreen(final KaijumonGame game) {
        this.game = game;
        game.pauseScreen = this;
        game.setScreen(this);

        Table uiRoot = new Table();

        Table buttonTable = new Table();

        TextButton textButton = new TextButton("Resume", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleResumeButtonEvent();
            }
        });
        buttonTable.add(textButton).spaceBottom(10).fillX();

        buttonTable.row();

        textButton = new TextButton("Exit", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleExitButtonEvent();
            }
        });
        buttonTable.add(textButton).spaceBottom(10).fillX();

        buttonTable.row();

        uiRoot.add(buttonTable).expand().fill();
        stage.addActor(uiRoot);
        Gdx.input.setInputProcessor(stage);

    }

    private void handleResumeButtonEvent() {
        game.setScreen(game.gameScreen);
        dispose();

    }
    private void handleExitButtonEvent () {
        game.mainMenu = new MainMenuScreen(game);
        game.setScreen(game.mainMenu);
        dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
