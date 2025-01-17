package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.dialog.DialogueTraverser;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.entities.IEntity;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Player;

import java.awt.Point;
import java.io.Serializable;

public class Npc implements IEntity, IInteractable {
    private final Point position;
    private final Point lastPosition;
    private final IMoveBehaviourStrategy moveBehaviour;
    private long timeSinceLastMove;
    private final int dialogId;
    public final int spriteId;
    private Direction direction;
    private final ITrainer npcTrainer;
    private final Player player;

    public Npc(int x, int y, IMoveBehaviourStrategy moveBehaviour, int dialogId, int spriteId, Player player, ITrainer npcTrainer) {
        position = new Point(x, y);
        lastPosition = new Point(x, y);
        this.moveBehaviour = moveBehaviour;
        timeSinceLastMove = System.currentTimeMillis();
        this.dialogId = dialogId;
        this.spriteId = spriteId;
        direction = Direction.DOWN;
        this.npcTrainer = npcTrainer;
        this.player = player;
    }

    /**
     * a Method to move the npc according to the moveBehaviourStrategy.
     */
    public void moveNpc() {
        int randomRoll = (int) (Math.random() * 100) + 1;
        if (randomRoll > 3)
            return;

        timeSinceLastMove = System.currentTimeMillis();
        lastPosition.move(position.x, position.y);
        moveBehaviour.move(position);

        updateDirection();
    }

    private void updateDirection() {
        int x = position.x - lastPosition.x;
        int y = position.y - lastPosition.y;

        if (x == 0 && y == 0) {
            return;
        }
        direction = Direction.getDirection(x, y);
    }

    @Override
    public void interact() {
        if (dialogId == 0) {
            return;
        }
        new DialogueTraverser(dialogId, player, npcTrainer);
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
        if (timeSinceLastMove < 0) {
            throw new IllegalStateException("timeSinceLastMove can not be negative");
        }
        return System.currentTimeMillis() - timeSinceLastMove;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
