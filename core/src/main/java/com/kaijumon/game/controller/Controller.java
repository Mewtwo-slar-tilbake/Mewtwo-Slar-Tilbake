package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.Savegame;

public class Controller implements IController {

    private final Music rainMusic;

    private final KaijumonGame game;
    private final IModel model;

    private Savegame savegame;


    public Controller(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;
        this.savegame = new Savegame(model.getPlayer());

        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            model.movePlayer(-1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            model.movePlayer(1, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            model.movePlayer(0, 1);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            model.movePlayer(0, -1);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            game.pause();

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            savegame.saveGameState();
        }



        @Override
        public void playMusic () {
            rainMusic.play();
        }

        @Override
        public void dispose () {
            rainMusic.dispose();
        }

    }

