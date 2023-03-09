package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.Model;

import java.awt.Point;

public class MoveBehaviourStill implements IMoveBehaviourStrategy {
    Model model;
    public MoveBehaviourStill (Model model){
        this.model = model;
    }
    @Override
    public void move(Point position) {
        //do nothing
    }
}
