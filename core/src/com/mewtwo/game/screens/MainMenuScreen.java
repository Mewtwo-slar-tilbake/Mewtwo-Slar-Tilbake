package com.mewtwo.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mewtwo.game.KaijumonGame;
import com.mewtwo.game.controller.Controller;
import com.mewtwo.game.controller.IController;
import com.mewtwo.game.model.IModel;
import com.mewtwo.game.model.Model;
import com.mewtwo.game.view.IView;
import com.mewtwo.game.view.View;

public class MainMenuScreen implements Screen {

    final KaijumonGame game;

    OrthographicCamera camera;

    public MainMenuScreen(final KaijumonGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to this game!", 100, 150);
        game.font.draw(game.batch, "Click anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            IModel model = new Model();
            IView view = new View(game, model);
            IController controller = new Controller(game, model);

            game.setScreen(new GameScreen(game, view, controller));
            dispose();
        }
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
    }

}
