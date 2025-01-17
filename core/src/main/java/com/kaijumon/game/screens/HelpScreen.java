package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;

public class HelpScreen implements Screen {
    private KaijumonGame game;
    private Stage stage;
    private final TextButton textButton;
    private final Texture background, background2;
    private OrthographicCamera camera;



    public HelpScreen(final KaijumonGame game){
        this.game = game;
        stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        background = new Texture(Gdx.files.internal("assets/landscape.png"));
        background2 = new Texture(Gdx.files.internal("assets/TV No Video Example.png"));

        Table uiRoot = new Table();

        Table buttonTable = new Table();

        textButton = new TextButton("Exit", game.getSkin());
        textButton.setPosition(stage.getWidth()/2-20, (stage.getHeight()/2)-80);
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

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().draw(background2, (Gdx.graphics.getWidth()/2-200), (Gdx.graphics.getHeight()/2)-120, (Gdx.graphics.getWidth()/2), (Gdx.graphics.getHeight()/2));
        game.getFont().draw(game.getBatch(), "Walk around - Arrow keys", (Gdx.graphics.getWidth()/2)-110, (Gdx.graphics.getHeight()/2)+70);
        game.getFont().draw(game.getBatch(), "Pause Game - ESC", (Gdx.graphics.getWidth()/2)-110, (Gdx.graphics.getHeight()/2)+50);
        game.getFont().draw(game.getBatch(), "Save Game - S-key", (Gdx.graphics.getWidth()/2)-110, (Gdx.graphics.getHeight()/2)+30);
        game.getFont().draw(game.getBatch(), "Interact with NPC - X", (Gdx.graphics.getWidth()/2)-110, (Gdx.graphics.getHeight()/2)+10);
        game.getFont().draw(game.getBatch(), "Change selection in batlle - Arrow keys", (Gdx.graphics.getWidth()/2)-110, (Gdx.graphics.getHeight()/2)-10);
        game.getFont().draw(game.getBatch(), "Confirm selection in battle - X or Enter", (Gdx.graphics.getWidth()/2)-110, (Gdx.graphics.getHeight()/2)-30);
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
        stage.dispose();
        background.dispose();
    }

    private void handleExitButtonEvent() {
        game.mainMenu = new MainMenuScreen(game);
        dispose();
        game.setScreen(game.mainMenu);

    }
}
