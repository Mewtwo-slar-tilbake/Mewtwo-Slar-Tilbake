package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.kaijumon.game.KaijumonGame;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import org.w3c.dom.Text;

public class PauseScreen implements Screen {

    private boolean isPaused;
    KaijumonGame game = new KaijumonGame();
    private Stage stage;
    private TextButton textButton;
    private Skin skin;



    public PauseScreen() {
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
                Gdx.app.exit();
            }
        });
        buttons.add(exitButton).spaceBottom(10).fillX();
        table.add(buttons);
        stage.addActor(table);

        isPaused = true;

        Gdx.input.setInputProcessor(stage);
    }

    private void handleSaveButtonEvent() {
    }

    private void handleResumeButtonEvent() {
        isPaused = false;
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

