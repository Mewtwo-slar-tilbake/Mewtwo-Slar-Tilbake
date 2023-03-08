package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.Model;

import java.awt.*;

public class MoveBehaviourRandom implements IMoveBehaviourStrategy{
    Model model;

    public MoveBehaviourRandom(Model model){
        this.model = model;
    }

    @Override
    public void move(Point npcPosition) {
        int x = npcPosition.x;
        int y = npcPosition.y;
        int random = (int) (Math.random() * 10);
        switch (random) {
            case 0:
                x++;
                break;
            case 1:
                x--;
                break;
            case 2:
                y++;
                break;
            case 3:
                y--;
                break;
            default:
                break;
        }

        if (model.isTileBlocked(x, y)){
            return;
        }
        npcPosition.move(x, y);
    }
}
