package com.kaijumon.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.kaijumon.game.model.entities.Player;

/**
 * Represents a main model that manages
 * the state of all game models.
 */
public interface IModel {

    /**
     * Move the player.
     *
     * @param deltaX the distance to move the player in the x direction.
     * @param deltaY the distance to move the player in the y direction.
     */
    void movePlayer(int deltaX, int deltaY);

    /**
     * Get the player instance.
     */
    Player getPlayer();

    /**
     * Get all tiles.
     */
    TiledMap getTileMap();

    /**
     * Get the time in milliseconds since the last time the player moved
     */
    long getTimeSinceLastMove();

    /**
     * Saves the state of the model by calling the function savegame
     */
    void saveModel();

}
