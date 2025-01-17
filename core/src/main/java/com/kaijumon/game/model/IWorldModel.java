package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Kaijumon;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.entities.npc.Npc;

import java.awt.*;
import java.util.List;

/**
 * Represents a main model that manages
 * the state of all game models.
 */
public interface IWorldModel {

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

    /**
     * A method that prints the players x and y coordinates to the console.
     */
    void printPlayerPosition();

    /**
     * Checks if a tile contains a character.
     * @param tile the tile to check
     * @return true if the tile contains a character, false otherwise.
     */
    boolean tileContainsCharacter(Point tile);

    /**
     * Checks if a tile is blocked by a wall etc.. (but not characters)
     * @param tile the tile to check
     * @return true if the tile is blocked, false otherwise.
     */
    boolean isTileBlocked(Point tile);

    /**
     * Get the dimensions of the map.
     * @return the dimensions of the map in the form of a Point representing a vector.
     */
    Point getMapDimensions();

    /**
     * Get a random Kaijumon.
     * @return a random Kaijumon.
     */
    Kaijumon getRandomKaijumon();
}
