package com.mewtwo.game.model;

import com.badlogic.gdx.utils.Array;
import com.mewtwo.game.model.entities.Player;
import com.mewtwo.game.model.tiles.Tile;

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
    void movePlayer(float deltaX, float deltaY);

    /**
     * Get the player instance.
     */
    Player getPlayer();

    /**
     * Get all tiles.
     */
    Array<Tile> getTiles();

}
