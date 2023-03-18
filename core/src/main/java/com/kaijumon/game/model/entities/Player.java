package com.kaijumon.game.model.entities;


import com.kaijumon.game.model.Direction;
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

        timeSinceLastMove = System.currentTimeMillis() - Consts.timeBetweenMovesMillis - 10;
    }

    /**
     * Move the player entity.
     *
     * @param delta the direction to move the player
     */
    public void move(Direction delta) {
        if (!(System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis)) {
            return;
        }
        timeSinceLastMove = System.currentTimeMillis();
        lastPosition.move(position.x, position.y);
        delta.apply(position);
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
