package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.dialog.DialogueState;
import com.kaijumon.game.dialog.DialogueSystem;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.IWorldModel;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.screens.PauseScreen;
import com.kaijumon.game.utils.Consts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class WorldController implements IWorldController {


    private final KaijumonGame game;
    private final IWorldModel model;
    private float timeSincePlayerMovedMillis;
    private final List<Integer> movementKeys;
    private DialogueState dialogueState;

    public WorldController(final KaijumonGame game, IWorldModel model) {
        this.game = game;
        this.model = model;
        this.movementKeys = new LinkedList<>();
        dialogueState = DialogueState.INACTIVE;
    }

    @Override
    public void update(float deltaTime) {
        timeSincePlayerMovedMillis += deltaTime * 1000;
        handleInput();
        moveNpcs();
    }

    private void moveNpcs() {
        if (dialogueState != DialogueState.INACTIVE)
            return;

        for (Npc npc : model.getNpcList()) {
            if (npc.getTimeSinceLastMove() < Consts.timeBetweenMovesMillis) {
                continue;
            }
            npc.moveNpc();
        }
    }

    /**
     * Handles the input from the player.
     */
    private void handleInput() {
        updateDialogueState();
        handlePlayerMovement();
        handleDialogueKeys();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            game.setScreen(new PauseScreen(game, model));
        else if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            model.saveModel();
        else if (Gdx.input.isKeyJustPressed(Input.Keys.X))
            model.playerInteract();
        else if (Gdx.input.isKeyJustPressed(Input.Keys.P))
            model.printPlayerPosition();
    }

    /**
     * Handles the keys that are used during dialogue.
     */
    private void handleDialogueKeys() {
        if (dialogueState != DialogueState.ACTIVE)
            return;
        DialogueSystem dialogueSystem = DialogueSystem.getInstance();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            dialogueSystem.publishInputEvent(Input.Keys.ENTER);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            dialogueSystem.publishInputEvent(Input.Keys.UP);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            dialogueSystem.publishInputEvent(Input.Keys.DOWN);
        }
    }

    /**
     * Handles the movement of the player.
     */
    private void handlePlayerMovement(){
        if (dialogueState != DialogueState.INACTIVE)
            return;

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

    private void updateDialogueState() {
        if (DialogueSystem.getInstance().hasActiveDialogues()) {
            dialogueState = DialogueState.ACTIVE;
        } else {
            dialogueState = DialogueState.INACTIVE;
        }
    }

}

