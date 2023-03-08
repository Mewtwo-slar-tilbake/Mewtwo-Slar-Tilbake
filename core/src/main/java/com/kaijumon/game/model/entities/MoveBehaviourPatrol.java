package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.Model;

import java.awt.Point;
import java.util.List;

public class MoveBehaviourPatrol implements IMoveBehaviourStrategy{
    Model model;
    List<Point> movePointList;
    int currentPoint = 0;
    int moveThreshold = 4;

    public MoveBehaviourPatrol(Model model, List<Point> movePointList){
        this.model = model;
        this.movePointList = movePointList;
    }

    @Override
    public void move(Point npcPosition) {
        //TODO maybe interpolate between points in the movePointList so we dont have to define each point in the patrol
        int x = movePointList.get(currentPoint).x;
        int y = movePointList.get(currentPoint).y;
        int random = (int) (Math.random() * 10);
        if (random > moveThreshold) return;

        if (model.isTileBlocked(x, y)){
            return;
        }
        currentPoint = (currentPoint + 1) % movePointList.size();
        npcPosition.move(x, y);
    }
}
