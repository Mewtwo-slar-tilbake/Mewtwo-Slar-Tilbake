package com.kaijumon.game.screens;

import com.badlogic.gdx.Screen;
import com.kaijumon.game.KaijumonGame;

public class PauseScreen implements Screen {
    KaijumonGame game;


    public PauseScreen(final KaijumonGame game) {
        this.game = game;
        game.pauseScreen = this;
        game.setScreen(this);
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
