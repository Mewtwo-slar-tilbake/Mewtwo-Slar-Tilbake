package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.Model;

import java.awt.Point;

public class MoveBehaviourRandom implements IMoveBehaviourStrategy{
    Model model;
    Point a;
    Point b;

    /**
     * Behavior for moving an npc randomly within a given area
     * @param model model
     * @param a one of the four corners of the bounding box
     * @param b the opposite corner of A on the bounding box
     */
    public MoveBehaviourRandom(Model model, Point a, Point b){
        this.model = model;
        this.a = a;
        this.b = b;
    }

    @Override
    public void move(Point npcPosition) {
        if (!isInsideBoundingBox(npcPosition)){
            throw new IllegalArgumentException("npcPosition is not inside the bounding box");
        }

        // only move 20% of the time
        int random = (int) (Math.random() * 11);
        if (random > 2) {
            return;
        }

        for (Direction offset : Direction.getRandomDirectionList()) {
            Point tile = offset.getNewPosition(npcPosition);

            if (isInsideBoundingBox(tile) && !model.isTileBlocked(tile)){
                offset.apply(npcPosition);
                return;
            }
        }
    }

    /**
     * checks if a given point is inside the bounding box
     */
    private boolean isInsideBoundingBox(Point p){
        return (p.x >= a.x && p.x <= b.x) && (p.y >= a.y && p.y <= b.y) ||
                (p.x >= b.x && p.x <= a.x) && (p.y >= b.y && p.y <= a.y);
    }
}
