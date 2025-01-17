package com.kaijumon.game.model.entities;


import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.entities.npc.IInteractable;
import com.kaijumon.game.model.items.KaijuBall;
import com.kaijumon.game.model.items.PotionFactory;
import com.kaijumon.game.utils.Consts;

import java.awt.*;

/**
 * Represents a player entity that can be moved around.
 */
public class Player implements IEntity {
    private final Point position;
    private String mapPath;
    private final Point lastPosition;
    private long timeSinceLastMove;
    private Direction direction;
    private final Bag bag;

    public Player(int x, int y) {
        position = new Point(x, y);
        lastPosition = new Point(x, y);
        bag = new Bag();
        bag.addItem(PotionFactory.getRandomPotion());
        bag.addItem(PotionFactory.getRandomPotion());
        bag.addItem(new KaijuBall());

        timeSinceLastMove = System.currentTimeMillis();
        direction = Direction.RIGHT;
    }

    /**
     * Move the player entity.
     *
     * @param delta the direction to move the player
     */
    public void move(Direction delta) {
        timeSinceLastMove = System.currentTimeMillis();
        lastPosition.move(position.x, position.y);
        delta.apply(position);
        updateDirection();
    }

    /**
     * update the direction the player is facing
     * used when the player is moving from one tile to another
     */
    private void updateDirection(){
        int x = position.x - lastPosition.x;
        int y = position.y - lastPosition.y;

        if (x == 0 && y == 0){
            return;
        }
        direction = Direction.getDirection(x, y);
    }

    /**
     * set the direction the player is facing
     * used when the player is changing direction but staying on the same tile
     */
    public void setDirection(Direction direction) {
        if (direction.x == this.direction.x && direction.y == this.direction.y){
            return;
        }
        if (!(System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis)) {
            return;
        }
        this.direction = direction;
        this.lastPosition.move(position.x, position.y);
        timeSinceLastMove = System.currentTimeMillis();
    }

    public Point getPosition(){
        return new Point(position.x, position.y);
    }

    @Override
    public Point getLastPosition() {
        return new Point(lastPosition.x, lastPosition.y);
    }

    public void setPosition(int x, int y) {
        position.setLocation(x, y);
    }


    @Override
    public long getTimeSinceLastMove() {
        if (timeSinceLastMove < 0) {
            throw new IllegalStateException("timeSinceLastMove can not be negative");
        }
        return System.currentTimeMillis() - timeSinceLastMove;
    }

    public void interact(IInteractable interactable){
        interactable.interact();
    }

    public Direction getDirection(){
        return direction;
    }

    public Bag getBag(){
        return bag;
    }

    public void setMapPath(String mapPath){
        this.mapPath = mapPath;
    }

    public String getMapPath(){
        return mapPath;
    }
}
