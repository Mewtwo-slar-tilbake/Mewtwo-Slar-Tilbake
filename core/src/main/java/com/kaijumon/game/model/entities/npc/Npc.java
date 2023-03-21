package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.entities.IEntity;
import com.kaijumon.game.utils.Consts;

import java.awt.Point;
import java.io.Serializable;

public class Npc implements Serializable, IEntity, IInteractable {
    private final Point position;
    private final Point lastPosition;
    private final IMoveBehaviourStrategy moveBehaviour;
    private long timeSinceLastMove;

    public Npc(int x, int y, IMoveBehaviourStrategy moveBehaviour) {
        //TODO add movement speed to the npc
        position = new Point(x, y);
        lastPosition = new Point(x, y);
        this.moveBehaviour = moveBehaviour;
        long randomMillis = (long) (Math.random() * 1000);
        timeSinceLastMove = System.currentTimeMillis() + randomMillis;
    }

    /**
     * a Method to move the npc according to the moveBehaviourStrategy.
     */
    public void moveNpc() {
        if (!(System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis)) {
            return;
        }
        timeSinceLastMove = System.currentTimeMillis();
        lastPosition.move(position.x, position.y);
        moveBehaviour.move(position);
    }

    @Override
    public void interact() {
        //TODO
        System.out.println("Interacting with NPC at " + position.x + ", " + position.y );
    }

    @Override
    public Point getPosition() {
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
