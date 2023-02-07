package com.mewtwo.game.screens;

import com.badlogic.gdx.Screen;
import com.mewtwo.game.KaijumonGame;
import com.mewtwo.game.controller.IController;
import com.mewtwo.game.view.IView;

/**
 * Represents the screen where the main game will occur.
 */
public class GameScreen implements Screen {
    final KaijumonGame game;

    private final IView view;
    private final IController controller;

    public GameScreen(final KaijumonGame game, IView view, IController controller) {
        this.game = game;

        this.view = view;
        this.controller = controller;
    }

    @Override
    public void render(float delta) {
        view.render(delta);
        controller.handleInput();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        controller.playMusic();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        view.dispose();
        controller.dispose();
    }

}
