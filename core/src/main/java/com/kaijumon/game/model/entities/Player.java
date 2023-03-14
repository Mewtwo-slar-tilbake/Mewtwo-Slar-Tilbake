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
