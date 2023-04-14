package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.model.entities.Player;

import java.util.List;

/**
 * Represents a main model that manages
 * the state of all game models.
 */
public interface IModel {

    /**
     * Move the player.
     *
     * @param delta direction to move the player.
     */
    void movePlayer(Direction delta);

    /**
     * Get the player instance.
     */
    Player getPlayer();

    /**
     * Get the list of all NPCs.
     */
    List<Npc> getNpcList();

    /**
     * Get all tiles.
     */
    String getTileMapPath();

    /**
     * Saves the state of the model by calling the function savegame
     */
    void saveModel();

    /**
     * Method to get the player to interact with what is in front of it.
     */
    void playerInteract();

    void printPlayerPosition();
}
