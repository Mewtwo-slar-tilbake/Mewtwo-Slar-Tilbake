package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveBehaviourRandom implements IMoveBehaviourStrategy{
    Model model;
    Point a;
    Point b;
    List<Point> offsetPointList = new ArrayList<>();

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
        offsetPointList.add(new Point(1, 0));
        offsetPointList.add(new Point(-1, 0));
        offsetPointList.add(new Point(0, 1));
        offsetPointList.add(new Point(0, -1));
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

        Collections.shuffle(offsetPointList);
        for (Point offset : offsetPointList) {
            int x = npcPosition.x + offset.x;
            int y = npcPosition.y + offset.y;
            if (isInsideBoundingBox(x, y) && !model.isTileBlocked(x, y)){
                npcPosition.move(x, y);
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

    /**
     * checks if a given point is inside the bounding box
     */
    private boolean isInsideBoundingBox(int x, int y){
        return (x >= a.x && x <= b.x) && (y >= a.y && y <= b.y) ||
                (x >= b.x && x <= a.x) && (y >= b.y && y <= a.y);
    }
}
