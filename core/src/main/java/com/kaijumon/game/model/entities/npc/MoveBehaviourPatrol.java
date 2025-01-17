package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.IWorldModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveBehaviourPatrol implements IMoveBehaviourStrategy{
    IWorldModel model;
    List<Point> path;
    int currentPoint = 0;

    public MoveBehaviourPatrol(IWorldModel model, List<Point> movePointList){
        this.model = model;
        this.path = createPath(movePointList);
    }

    @Override
    public void move(Point npcPosition) {
        Point tile = path.get(currentPoint);

        if (!npcPosition.equals(tile) && model.tileContainsCharacter(tile)){
            return;
        }

        if (model.isTileBlocked(tile)){
            return;
        }
        currentPoint = (currentPoint + 1) % path.size();
        npcPosition.move(tile.x, tile.y);
    }

    /**
     * a method that given two points creates a list of points between the two points
     * if the two points are not aligned on one axis an illegalArgumentExeption will be thrown
     * @param a the starting point
     * @param b the ending point
     * @return a list of points between a and b
     */
    public static List<Point> createPointLine(Point a, Point b){
        boolean xChanged = false;
        boolean yChanged = false;
        List<Point> output = new ArrayList<>();

        if (a.x != b.x){
            xChanged = true;
        }
        if (a.y != b.y){
            yChanged = true;
        }
        if (xChanged && yChanged){
            throw new IllegalArgumentException("Points are not on the same line");
        }
        if (!xChanged && !yChanged){
            output.add(a);
            return output;
        }

        if (xChanged) {
            if (a.x <= b.x) {
                for (int i = a.x; i <= b.x; i++) {
                    Point p = new Point(i, a.y);
                    output.add(p);
                }
            } else {
                for (int i = a.x; i >= b.x; i--) {
                    Point p = new Point(i, a.y);
                    output.add(p);
                }
            }
        }
        if (yChanged) {
            if (a.y <= b.y) {
                for (int i = a.y; i <= b.y; i++) {
                    Point p = new Point(a.x, i);
                    output.add(p);
                }
            } else {
                for (int i = a.y; i >= b.y; i--) {
                    Point p = new Point(a.x, i);
                    output.add(p);
                }
            }
        }
        return output;
    }

    /**
     * given a list of points, this method will create point lines between each of the points and return a list with all the point lines.
     * @param pointList a list of points, each point has to be on the same axis as the point before and after
     * @return a list of all points (will also add the points to walk back from the end to the start)
     */
    public static List<Point> createPath(List<Point> pointList){
        List<Point> output = new ArrayList<>();
        for (int i = 0; i < pointList.size() - 1; i++) {
            List<Point> line = createPointLine(pointList.get(i), pointList.get(i + 1));
            if (i != 0){
                line.remove(0);
            }
            output.addAll(line);
        }
        return addReturnPoints(output);
    }

    /**
     * given a list of points, this method will add all points between the last and first to the end of the list
     * @param pointList list of points
     * @return list of points with the return points added
     */
    public static List<Point> addReturnPoints(List<Point> pointList){
        if (pointList.size() < 3){
            // the list is already mirrored so no need to do anything
            return new ArrayList<>(pointList);
        }
        List<Point> output = new ArrayList<>(pointList);
        List<Point> mirrored = new ArrayList<>(pointList);
        mirrored.remove(0);
        mirrored.remove(mirrored.size() - 1);
        Collections.reverse(mirrored);

        output.addAll(mirrored);
        return output;
    }
}
