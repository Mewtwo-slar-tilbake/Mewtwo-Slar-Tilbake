package com.kaijumon.game.screens;

import com.badlogic.gdx.Screen;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.IWorldController;
import com.kaijumon.game.view.IWorldView;

/**
 * Represents the screen where the main game will occur.
 */
public class WorldScreen implements Screen {
    private final KaijumonGame game;
    private final IWorldView view;
    private final IWorldController controller;

    public WorldScreen(final KaijumonGame game, IWorldView view, IWorldController controller) {
        this.game = game;
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void render(float delta) {
        view.render(delta);
        controller.update(delta);
        game.getScreenType();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

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
