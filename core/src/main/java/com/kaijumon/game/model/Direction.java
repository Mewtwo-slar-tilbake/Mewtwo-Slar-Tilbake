package com.kaijumon.game.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final Point delta;
    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.delta = new Point(x, y);
        this.x = x;
        this.y = y;
    }

    /**
     * construct a list of all directions in a random order
     * @return a list of all directions in a random order
     */
    public static List<Direction> getRandomDirectionList(){
        List<Direction> directionList = new ArrayList<>(List.of(Direction.values()));
        Collections.shuffle(directionList);
        return directionList;
    }

    /**
     * return a new point that is the result of applying this direction to the given point
     * @param point the point to apply the direction to
     * @return a new point that is the result of applying this direction to the given point
     */
    public Point getNewPosition(Point point){
        return new Point(point.x + this.x, point.y + this.y);
    }

    /**
     * apply this direction to the given point
     * NB! this method modifies the given point
     * @param point the point to apply the direction to
     */
    public void apply(Point point){
        point.translate(this.x, this.y);
    }
}

