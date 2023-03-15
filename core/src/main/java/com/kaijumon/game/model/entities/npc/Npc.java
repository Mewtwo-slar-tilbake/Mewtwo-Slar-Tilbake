package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.dialog.DialogueTraverser;
import com.kaijumon.game.model.entities.IEntity;

import java.awt.Point;
import java.io.Serializable;

public class Npc implements Serializable, IEntity, IInteractable {
    private final Point position;
    private final Point lastPosition;
    private final IMoveBehaviourStrategy moveBehaviour;
    private long timeSinceLastMove;
    private final int dialogId;

    public Npc(int x, int y, IMoveBehaviourStrategy moveBehaviour, int dialogId) {
        position = new Point(x, y);
        lastPosition = new Point(x, y);
        this.moveBehaviour = moveBehaviour;
        long randomMillis = (long) (Math.random() * 1000);
        timeSinceLastMove = System.currentTimeMillis() + randomMillis;
        this.dialogId = dialogId;
    }

    /**
     * a Method to move the npc according to the moveBehaviourStrategy.
     */
    public void moveNpc() {
        timeSinceLastMove = System.currentTimeMillis();
        lastPosition.move(position.x, position.y);
        moveBehaviour.move(position);
    }

    @Override
    public void interact() {
        if (dialogId == 0) {
            return;
        }
        new DialogueTraverser(dialogId);
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
