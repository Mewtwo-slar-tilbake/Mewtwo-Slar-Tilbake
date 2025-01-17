package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.Direction;

import java.awt.Point;

public interface IEntity {

    /**
     * get the position of the entity.
     * @return the position of the entity.
     */
    Point getPosition();

    /**
     * get the last position of the entity.
     * @return a Point representing the position the player was before it moved
     */
    Point getLastPosition();

    /**
     * Get the time in milliseconds since the last time the player moved
     */
    long getTimeSinceLastMove();

    Direction getDirection();
}
