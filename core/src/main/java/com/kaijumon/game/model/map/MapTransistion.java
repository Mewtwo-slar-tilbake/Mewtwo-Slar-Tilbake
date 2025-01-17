package com.kaijumon.game.model.map;

import java.awt.Point;

public class MapTransistion {
    public final int fromX;
    public final int fromY;
    public final int toX;
    public final int toY;
    public final String toMap;

    /**
     * Creates a new MapTransistion
     * NB: this constructor is private, becuase we only create MapTransitions from loading them from a json file with Gson.
     *
     * @param fromX the x coordinate of the tile that the player must stand on to trigger the transition
     * @param fromY the y coordinate of the tile that the player must stand on to trigger the transition
     * @param toX the x coordinate of the tile that the player will be placed on when the transition is triggered
     * @param toY the y coordinate of the tile that the player will be placed on when the transition is triggered
     * @param toMap the path to the map that the player will be placed on when the transition is triggered
     */
    private MapTransistion(int fromX, int fromY, int toX, int toY, String toMap) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.toMap = toMap;
    }

    /**
     * Get the point that the player must stand on to trigger the transition
     * @return a Point representing the tile that the player must stand on to trigger the transition
     */
    public Point getFromPoint() {
        return new Point(fromX, fromY);
    }

    /**
     * Get the point that the player will be placed on when the transition is triggered
     * @return a Point representing the tile that the player will be placed on when the transition is triggered
     */
    public Point getToPoint() {
        return new Point(toX, toY);
    }
}
