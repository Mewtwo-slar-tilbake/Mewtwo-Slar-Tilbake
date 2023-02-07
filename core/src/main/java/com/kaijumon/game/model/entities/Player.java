package com.kaijumon.game.model.entities;

/**
 * Represents a player entity that can be moved around.
 */
public class Player {

    private int x;
    private int y;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
