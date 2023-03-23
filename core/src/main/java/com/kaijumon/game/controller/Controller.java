package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.utils.Consts;


public class Controller implements IController {

    private final Music rainMusic;

    private final KaijumonGame game;
    private final IModel model;
    private float timeSincePlayerMovedMillis;

    public Controller(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);
    }

    @Override
    public void update(float deltaTime) {
        timeSincePlayerMovedMillis += deltaTime * 1000;
        handleInput();
        moveNpcs();
    }

    private void moveNpcs() {
        for (Npc npc : model.getNpcList()) {
            if (System.currentTimeMillis() - npc.getTimeSinceLastMove() < Consts.timeBetweenMovesMillis) {
                continue;
            }
            npc.moveNpc();
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            handlePlayerMovement(Direction.LEFT);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            handlePlayerMovement(Direction.RIGHT);
        else if (Gdx.input.isKeyPressed(Input.Keys.UP))
            handlePlayerMovement(Direction.UP);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            handlePlayerMovement(Direction.DOWN);

        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.pause();
        else if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            model.saveModel();

        else if (Gdx.input.isKeyJustPressed(Input.Keys.I)) // TODO change from key "I" to something else
            model.playerInteract();
    }

    private void handlePlayerMovement(Direction direction){
        if (timeSincePlayerMovedMillis < Consts.timeBetweenMovesMillis) {
            return;
        }

        timeSincePlayerMovedMillis = 0;
        model.movePlayer(direction);
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

