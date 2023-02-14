package com.kaijumon.game.model.entities;


import java.awt.*;
import java.io.Serializable;

/**
 * Represents a player entity that can be moved around.
 */
public class Player implements Serializable {
    private final Point position;

    public Player(int x, int y) {
        position = new Point(x, y);
    }

    /**
     * Move the player entity.
     *
     * @param deltaX change along the x-axis.
     * @param deltaY change along the y-axis.
     */
    public void move(int deltaX, int deltaY) {
        position.translate(deltaX, deltaY);
        if (position.x < 0) position.move(0, position.y);
        if (position.y < 0) position.move(position.x, 0);

    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public Point getPosition(){
        return position;
    }
}
