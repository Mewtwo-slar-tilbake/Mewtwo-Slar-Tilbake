package com.kaijumon.game.screens;

import com.badlogic.gdx.Screen;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.IController;
import com.kaijumon.game.view.IView;

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
        controller.update();
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
