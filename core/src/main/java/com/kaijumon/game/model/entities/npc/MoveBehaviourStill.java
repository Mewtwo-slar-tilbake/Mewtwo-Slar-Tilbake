package com.kaijumon.game.model.entities.npc;

import com.kaijumon.game.model.WorldModel;

import java.awt.Point;

public class MoveBehaviourStill implements IMoveBehaviourStrategy {
    WorldModel model;

    public MoveBehaviourStill () {
        this.model = null;
    }
    @Override
    public void move(Point position) {
        //do nothing
    }
}
