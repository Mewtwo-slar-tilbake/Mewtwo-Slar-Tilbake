package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kaijumon.game.KaijumonGame;

public class BattleScreen implements Screen {

    final KaijumonGame game;

    private final Texture kaijumon;
    private final Texture player;

    private final Texture background;

    private Stage stage;
    private TextButton textButton;


    public BattleScreen(final KaijumonGame game) {

        background = new Texture(Gdx.files.internal("background.png"));
        kaijumon = new Texture(Gdx.files.internal("yoda.png"));
        player = new Texture(Gdx.files.internal("player.jpg"));

        this.game = game;
        stage = new Stage();

        Table uiRoot = new Table();
        Table buttonTable = new Table();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont();
        textButtonStyle.fontColor = Color.GOLD;

        textButton = new TextButton("Fight", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 150, 100);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);
        // Prints to the terminal when the button is clicked
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleFightButtonEvent();
            }
        });

        textButton = new TextButton("Bag", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 50, 100);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);
        // Prints to the terminal when the button is clicked
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleBagButtonEvent();
            }
        });

        textButton = new TextButton("Kaijumon", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 140, 50);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);
        // Prints to the terminal when the button is clicked
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleKijumonButtonEvent();
            }
        });

        textButton = new TextButton("Run", textButtonStyle);
        textButton.setPosition((Gdx.graphics.getWidth() - textButton.getWidth()) - 50, 50);
        textButton.getLabel().setFontScale(2);
        stage.addActor(textButton);
        // Prints to the terminal when the button is clicked
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleRunButtonEvent();
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
        game.getBatch().begin();

        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().draw(kaijumon, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200, 100, 100);
        game.getBatch().draw(player, 100, 100, 100, 100);

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

    private void handleFightButtonEvent() {
        System.out.println("Fight button clicked");
    }

    private void handleBagButtonEvent() {
        System.out.println("Bag button clicked");
    }

    private void handleKijumonButtonEvent(){
        System.out.println("Kaijumon button clicked");
    }

    private void handleRunButtonEvent(){
        System.out.println("Run button clicked");
    }
}
