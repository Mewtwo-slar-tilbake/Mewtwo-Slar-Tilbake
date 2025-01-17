package com.kaijumon.game.model;

import com.badlogic.gdx.Input;

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

    public static Direction getDirectionFromKey(Integer integer) {
        switch (integer){
            case Input.Keys.UP:
                return UP;
            case Input.Keys.DOWN:
                return DOWN;
            case Input.Keys.LEFT:
                return LEFT;
            case Input.Keys.RIGHT:
                return RIGHT;
            default:
                throw new IllegalArgumentException("No direction with key " + integer + " exists");
        }
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

    public static Direction getDirection(int x, int y){
        for (Direction direction : Direction.values()){
            if (direction.x == x && direction.y == y){
                return direction;
            }
        }
        throw new IllegalArgumentException("No direction with x=" + x + " and y=" + y + " exists");
    }

    public static Direction getOpposite(Direction direction) {
        if (direction.x == 0) // we are on the y-axis
            return direction.y == 1 ? DOWN : UP;
        else // we are on the x-axis
            return direction.x == 1 ? LEFT : RIGHT;
    }

}
