package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IWorldModel;

/**
 This class represents the pause screen in the Kaijumon game. It implements the Screen interface and contains

 buttons to resume, save or exit the game. The class interacts with the KaijumonGame class and an IModel instance.
 */
public class PauseScreen implements Screen {
    KaijumonGame game;
    private final Stage stage;

    IWorldModel model;

    /**
     Constructor for the PauseScreen class.

     @param game The KaijumonGame instance to interact with.

     @param model The IModel instance to interact with.
     */
    public PauseScreen(final KaijumonGame game, IWorldModel model) {
        this.game = game;
        this.stage = new Stage();
        this.model = model;
        game.pauseScreen = this;
        game.setScreen(this);

        Texture texture = new Texture("assets/gamepaused.png");
        Image background = new Image(texture);
        background.setSize(stage.getWidth(), stage.getHeight());


        Table uiRoot = new Table();
        uiRoot.setFillParent(true);

        Table buttonTable = new Table();
        buttonTable.padLeft(10.0f);

        TextButton textButton = new TextButton("Continue", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleResumeButtonEvent();
            }
        });

        buttonTable.add(textButton).spaceBottom(10).fillX();
        buttonTable.row();

        textButton = new TextButton("Save", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleSaveButtonEvent();
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
        stage.addActor(background);
        stage.addActor(uiRoot);
        Gdx.input.setInputProcessor(stage);

    }

    private void handleResumeButtonEvent() {
        game.setScreen(game.worldScreen);
        dispose();

    }

    private void handleExitButtonEvent () {
        game.mainMenu = new MainMenuScreen(game);
        game.setScreen(game.mainMenu);
        dispose();
    }

    private void handleSaveButtonEvent () {
        model.saveModel();
        dispose();
    }

    @Override
    public void show() {

    }

    /**

     Renders the pause screen. Clears the screen and draws the stage.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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
