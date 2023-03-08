package com.kaijumon.game.model.entities;


import java.awt.Point;
import java.io.Serializable;

/**
 * Represents a player entity that can be moved around.
 */
public class Player implements Serializable, IEntity {
    private final Point position;
    private final Point lastPosition;

    public Player(int x, int y) {
        position = new Point(x, y);
        lastPosition = new Point(x, y);
    }

    /**
     * Move the player entity.
     *
     * @param deltaX change along the x-axis.
     * @param deltaY change along the y-axis.
     */
    public void move(int deltaX, int deltaY) {
        lastPosition.move(position.x, position.y);
        position.translate(deltaX, deltaY);

    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public Point getPosition(){
        return new Point(position.x, position.y);
    }

    @Override
    public Point getLastPosition() {
        return new Point(lastPosition.x, lastPosition.y);
    }


}
