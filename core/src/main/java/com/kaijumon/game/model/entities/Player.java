package com.kaijumon.game.model.entities;


import com.kaijumon.game.utils.Consts;

import java.awt.Point;
import java.io.Serializable;

/**
 * Represents a player entity that can be moved around.
 */
public class Player implements Serializable, IEntity {
    private final Point position;
    private final Point lastPosition;
    private long timeSinceLastMove;

    public Player(int x, int y) {
        position = new Point(x, y);
        lastPosition = new Point(x, y);

        timeSinceLastMove = System.currentTimeMillis();
    }

    /**
     * Move the player entity.
     *
     * @param deltaX change along the x-axis.
     * @param deltaY change along the y-axis.
     */
    public void move(int deltaX, int deltaY) {
        if (!(System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis)) {
            return;
        }
        timeSinceLastMove = System.currentTimeMillis();
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


    @Override
    public long getTimeSinceLastMove() {
        return timeSinceLastMove;
    }
}
