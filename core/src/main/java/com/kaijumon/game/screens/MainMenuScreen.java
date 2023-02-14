package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.Model;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.view.IView;
import com.kaijumon.game.view.View;

import java.io.*;

public class MainMenuScreen implements Screen {

    final KaijumonGame game;

    private Stage stage;
    private TextButton textButton;
    private Skin skin;

    public MainMenuScreen(final KaijumonGame game) {
        this.game = game;

        stage = new Stage();

        Table uiRoot = new Table();
        uiRoot.setFillParent(true);

        Table buttonTable = new Table();
        buttonTable.padLeft(10.0f);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.font;
        textButtonStyle.fontColor = Color.CORAL;
        textButton = new TextButton("New game", textButtonStyle);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleNewGameButtonEvent();
            }


        });

        buttonTable.add(textButton).spaceBottom(10).fillX();

        buttonTable.row();

        textButton = new TextButton("Load game", textButtonStyle);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleLoadGameButtonEvent();
            }
        });
        buttonTable.add(textButton).spaceBottom(10).fillX();

        buttonTable.row();
        textButton = new TextButton("Quit game", textButtonStyle);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
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
    }

    private void handleNewGameButtonEvent() {
        IModel model = new Model();
        IView view = new View(game, model);
        IController controller = new Controller(game, model);

        game.setScreen(new GameScreen(game, view, controller));
        dispose();
    }

    private void handleLoadGameButtonEvent() {
        //TODO
        /**
         * have to be able to save the game and then continue from there
         */
        try (FileInputStream loadFile = new FileInputStream("core/src/main/java/com/kaijumon/game/savefiles/testing.txt")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(loadFile);
            Player loadedTestPlayer = (Player) objectInputStream.readObject();
            objectInputStream.close();

            IModel model = new Model(loadedTestPlayer);
            IView view = new View(game, model);
            IController controller = new Controller(game, model);

            game.setScreen(new GameScreen(game, view, controller));
            dispose();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



}
