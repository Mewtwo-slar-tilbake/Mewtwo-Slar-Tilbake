package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kaijumon.game.KaijumonGame;

public class HelpScreen implements Screen {
    private KaijumonGame game;
    private Stage stage;
    private final TextButton textButton;
    private ShapeRenderer shapeRenderer;

    private final Texture background;
    private final Texture background2;

    ShapeRenderer shapeRender = new ShapeRenderer();


    public HelpScreen(final KaijumonGame game){
        shapeRenderer = new ShapeRenderer();
        this.game = game;
        stage = new Stage();

        background = new Texture(Gdx.files.internal("background.jpg"));
        background2 = new Texture(Gdx.files.internal("frame.png"));

        Table uiRoot = new Table();

        Table buttonTable = new Table();

        textButton = new TextButton("Exit", game.getSkin());
        textButton.setPosition(stage.getWidth()/2-10, (stage.getHeight()/2)-50);
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
        game.getBatch().begin();

        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.getBatch().end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(Gdx.graphics.getWidth()/2-200, Gdx.graphics.getHeight()/2-80, 400, 280);
        shapeRenderer.end();

        game.getBatch().begin();

        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);

        //align text in center of screen
        game.getBatch().draw(background2, Gdx.graphics.getWidth()/2-200, Gdx.graphics.getHeight()/2-80, 400, 280);
        font.draw(game.getBatch(), "Walk around - Arrow keys", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2);
        font.draw(game.getBatch(), "Interact with NPC - X or Enter", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-50);
        font.draw(game.getBatch(), "Change selection in battle - Arrow keys", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-100);
        font.draw(game.getBatch(), "Confirm selection in battle - X or Enter", Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth()/2-150);

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
        shapeRender.dispose();
    }

    private void handleExitButtonEvent() {
        game.mainMenu = new MainMenuScreen(game);
        game.setScreen(game.mainMenu);
        dispose();
    }
}