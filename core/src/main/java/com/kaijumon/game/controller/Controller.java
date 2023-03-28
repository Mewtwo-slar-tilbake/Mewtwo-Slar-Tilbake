package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.utils.Consts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Controller implements IController {


    private final KaijumonGame game;
    private final IModel model;
    private float timeSincePlayerMovedMillis;
    private final List<Integer> movementKeys;

    public Controller(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;
        this.movementKeys = new LinkedList<>();
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

    /**
     * Handles the input from the player.
     */
    private void handleInput() {
        handlePlayerMovement();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.pause();
        else if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            model.saveModel();

        else if (Gdx.input.isKeyJustPressed(Input.Keys.I)) // TODO change from key "I" to something else
            model.playerInteract();
    }

    /**
     * Handles the movement of the player.
     */
    private void handlePlayerMovement(){
        removeReleasedKeys();
        addPressedKeys();
        if (timeSincePlayerMovedMillis < Consts.timeBetweenMovesMillis || movementKeys.isEmpty())
            return;

        timeSincePlayerMovedMillis = 0;
        Direction direction = Direction.getDirectionFromKey(movementKeys.get(0));
        model.movePlayer(direction);
    }

    /**
     * Removes all keys from the movementKeys list that are not pressed anymore.
     */
    private void removeReleasedKeys(){
        List<Integer> releasedKeys = new ArrayList<>();
        for (int key : movementKeys) {
            if (!Gdx.input.isKeyPressed(key))
                releasedKeys.add(key);
        }
        movementKeys.removeAll(releasedKeys);
    }

    /**
     * Adds all keys that are pressed to the movementKeys list, if they are not already pressed.
     */
    private void addPressedKeys(){
        for (int key : Arrays.asList(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN)) {
            if (Gdx.input.isKeyPressed(key) && !movementKeys.contains(key)) {
                movementKeys.add(0, key);
            }
        }
    }



    @Override
    public void dispose () {
    }

}

