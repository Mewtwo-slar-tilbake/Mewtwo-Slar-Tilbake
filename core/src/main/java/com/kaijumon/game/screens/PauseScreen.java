package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.kaijumon.game.KaijumonGame;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.kaijumon.game.controller.Controller;
import com.kaijumon.game.controller.IController;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.Model;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.view.IView;
import com.kaijumon.game.view.View;

import static com.kaijumon.game.model.Savegame.loadPlayer;

public class PauseScreen implements Screen {

    private boolean isPaused;
    private final KaijumonGame game;
    private final Stage stage;



    public PauseScreen(final KaijumonGame game) {
        this.game = game;
        stage = new Stage();

        Table table = new Table();
        table.setFillParent(true);

        Table buttons = new Table();
        buttons.padLeft(10.0f);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = labelStyle.font; // Set the font of the text button to the label font
        textButtonStyle.fontColor = Color.CORAL;

        TextButton resumeButton = new TextButton("Resume", textButtonStyle);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleResumeButtonEvent();
            }
        });

        buttons.add(resumeButton).spaceBottom(10).fillX();
        buttons.row();

        TextButton saveButton = new TextButton("Save", textButtonStyle);
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleSaveButtonEvent();
            }
        });
        buttons.add(saveButton).spaceBottom(10).fillX();
        buttons.row();

        TextButton exitButton = new TextButton("Exit", textButtonStyle);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //exit to main menu
                game.setScreen(new MainMenuScreen(game));
            }
        });
        buttons.add(exitButton).spaceBottom(10).fillX();
        table.add(buttons);
        stage.addActor(table);

        isPaused = true;

        Gdx.input.setInputProcessor(stage);
    }

    private void handleSaveButtonEvent() {
        Player loadedPlayer = loadPlayer("core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
        IModel model = new Model(loadedPlayer);
        IView view = new View(game, model);
        IController controller = new Controller(game, model);
        model.saveModel();
        isPaused = false;
        //remove the pause screen and go back to the game screen
        game.setScreen(new GameScreen(game, view, controller));

    }

    private void handleResumeButtonEvent() {
        isPaused = false;
        Player loadedPlayer = loadPlayer("core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
        IModel model = new Model(loadedPlayer);
        IView view = new View(game, model);
        IController controller = new Controller(game, model);
        model.saveModel();
        isPaused = false;
        //remove the pause screen and go back to the game screen
        game.setScreen(new GameScreen(game, view, controller));
        dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}

