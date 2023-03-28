package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.Controller;
import com.kaijumon.game.controller.IController;
import com.kaijumon.game.controller.SoundManager;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.Model;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.view.IView;
import com.kaijumon.game.view.View;


import java.util.Arrays;

import static com.kaijumon.game.model.Savegame.loadPlayer;

public class MainMenuScreen implements Screen {

    final KaijumonGame game;

    private Stage stage;
    private TextButton textButton;


    public MainMenuScreen(final KaijumonGame game) {
        this.game = game;

        stage = new Stage();

        Table uiRoot = new Table();
        uiRoot.setFillParent(true);

        Table buttonTable = new Table();
        buttonTable.padLeft(10.0f);

        textButton = new TextButton("New game", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleNewGameButtonEvent();
            }
        });

        buttonTable.add(textButton).spaceBottom(10).fillX();

        buttonTable.row();

        textButton = new TextButton("Load game", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleLoadGameButtonEvent();
            }
        });
        buttonTable.add(textButton).spaceBottom(10).fillX();

        buttonTable.row();
        textButton = new TextButton("Quit game", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonTable.add(textButton).spaceBottom(10).fillX();


        buttonTable.row();
        textButton = new TextButton("How to play", game.getSkin());
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleHelpScreenButtonEvent();
            }
        });
        buttonTable.add(textButton);


        uiRoot.add(buttonTable);

        stage.addActor(uiRoot);

        // set the stage as the input processor so it will respond to clicks etc
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        stage.draw();
        stage.act();
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
        stage.dispose();
    }

    private void handleNewGameButtonEvent() {
        Player player = new Player(30, 30);
        SoundManager.getInstance().setPlayer(player);
        IModel model = new Model(game, player);
        IView view = new View(game, model);
        IController controller = new Controller(game, model);

        game.gameScreen = new GameScreen(game, view, controller);
        game.setScreen(game.gameScreen);
        dispose();
    }

    private void handleLoadGameButtonEvent() {
        Player loadedPlayer = loadPlayer("core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
        SoundManager.getInstance().setPlayer(loadedPlayer);
        IModel model = new Model(game, loadedPlayer);
        IView view = new View(game, model);
        IController controller = new Controller(game, model);

        game.gameScreen = new GameScreen(game, view, controller);
        game.setScreen(game.gameScreen);
        dispose();
    }

    private void handleHelpScreenButtonEvent(){
        game.helpScreen = new HelpScreen();
        game.setScreen(game.helpScreen);
        dispose();}

}
