package com.kaijumon.game.model.entities;

import com.badlogic.gdx.math.Rectangle;

/**
 * Represents a player entity that can be moved around.
 */
public class Player {

    public int x;
    public int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Move the player entity.
     *
     * @param deltaX change along the x-axis.
     * @param deltaY change along the y-axis.
     */
    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
        if (x < 0) x = 0;
        if (y < 0) y = 0;
    }

}
