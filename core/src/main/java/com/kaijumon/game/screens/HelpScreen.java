package com.kaijumon.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.entities.Kaijumon;
import com.badlogic.gdx.graphics.GL20;

public class HelpScreen implements Screen {
    private KaijumonGame game;
    private Stage stage;
    private final TextButton textButton;


    public HelpScreen(final KaijumonGame game){
        this.game = game;
        stage = new Stage();

        Table uiRoot = new Table();

        Table buttonTable = new Table();


        textButton = new TextButton("Exit", game.getSkin());
        textButton.setPosition(stage.getWidth()/2, (stage.getHeight()/2)-100);
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

        // set the stage as the input processor so it will respond to clicks etc
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Walk around - Arrow keys", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2);
        game.getFont().draw(game.getBatch(), "Interact with NPC - X or Enter", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-50);
        game.getFont().draw(game.getBatch(), "Change selection in battle - Arrow keys", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-100);
        game.getFont().draw(game.getBatch(), "Confirm selection in battle - X or Enter", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-150);

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

    }

    private void handleExitButtonEvent() {
        game.mainMenu = new MainMenuScreen(game);
        game.setScreen(game.mainMenu);
        dispose();}
    }
