package com.kaijumon.game.model.entities;

import java.awt.Point;
import java.io.Serializable;

public class Npc implements Serializable, IEntity {
    private final Point position;
    private final Point lastPosition;
    private final IMoveBehaviourStrategy moveBehaviour;

    public Npc(int x, int y, IMoveBehaviourStrategy moveBehaviour) {
        position = new Point(x, y);
        lastPosition = new Point(x, y);
        this.moveBehaviour = moveBehaviour;
    }

    /**
     * a Method to move the npc according to the moveBehaviourStrategy.
     */
    public void moveNpc() {
        lastPosition.move(position.x, position.y);
        moveBehaviour.move(position);
    }

    @Override
    public int getX() {
        return position.x;
    }

    @Override
    public int getY() {
        return position.y;
    }

    @Override
    public Point getPosition() {
        return new Point(position.x, position.y);
    }

    @Override
    public Point getLastPosition() {
        return new Point(lastPosition.x, lastPosition.y);
    }
}
