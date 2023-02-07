package com.mewtwo.game.model.entities;

import com.badlogic.gdx.math.Rectangle;

/**
 * Represents a player entity that can be moved around.
 */
public class Player {

    public Rectangle rect;

    public Player() {
        rect = new Rectangle();

        rect.x = 800 / 2 - 64 / 2;
        rect.y = 20;

        rect.width = 64;
        rect.height = 64;
    }

    /**
     * Move the player entity.
     *
     * @param deltaX change along the x-axis.
     * @param deltaY change along the y-axis.
     */
    public void move(float deltaX, float deltaY) {
        rect.x += deltaX;
        rect.y += deltaY;
    }

}
