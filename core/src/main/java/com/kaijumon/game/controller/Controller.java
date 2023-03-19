package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.entities.npc.Npc;


public class Controller implements IController {

    private final Music rainMusic;

    private final KaijumonGame game;
    private final IModel model;



    public Controller(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

    }

    @Override
    public void update() {
        handleInput();
        moveNpcs();
    }

    private void moveNpcs() {
        for (Npc npc : model.getNpcList())
            npc.moveNpc();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            model.movePlayer(Direction.LEFT);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            model.movePlayer(Direction.RIGHT);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            model.movePlayer(Direction.UP);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            model.movePlayer(Direction.DOWN);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            game.pause();

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            model.saveModel();
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

