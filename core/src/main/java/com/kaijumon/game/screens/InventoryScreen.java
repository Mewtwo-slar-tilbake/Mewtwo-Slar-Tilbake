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
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.items.Item;

import java.util.ArrayList;


public class InventoryScreen implements Screen {

    private final Stage stage;
    final KaijumonGame game;
    Player player;

    ArrayList<Item> inventory = new ArrayList<>();

    public InventoryScreen(KaijumonGame game){
        this.game = game;
        stage = new Stage();
        game.inventoryScreen = this;
        game.setScreen(this);

        Table buttonTable = new Table();
        buttonTable.padLeft(10.0f);

        Texture texture = new Texture(Gdx.files.internal("inventorybackground.png"));
        Image image = new Image(texture);
        Texture texture2 = new Texture(Gdx.files.internal("inventory.png"));
        Image image2 = new Image(texture2);
        image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        image2.setSize(250, 250);
        image2.setPosition(Gdx.graphics.getWidth() / 2 - 110, Gdx.graphics.getHeight() / 2 - 100);

        TextButton textButton = new TextButton("Back to game", game.getSkin());
        textButton.getLabel().setFontScale(1);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.worldScreen);
            }
        });
        buttonTable.add(textButton).spaceBottom(10).row();
        stage.addActor(buttonTable);


        //show items in inventory
        for (int i = 0; i < inventory.size(); i++) {
            inventory.add(player.getItems().get(i));
        }


        stage.addActor(image);
        stage.addActor(image2);
        Gdx.input.setInputProcessor(stage);


        //show items in inventory on screen



        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //clear the screen with transparent color
        Gdx.gl.glClearColor(0,0,0,0);
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

