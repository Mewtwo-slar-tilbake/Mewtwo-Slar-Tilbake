package com.kaijumon.game.model.entities;

import java.awt.*;

public class MoveBehaviourRandom implements IMoveBehaviourStrategy{
    @Override
    public void move(Point position) {
        int x = position.x;
        int y = position.y;
        int random = (int) (Math.random() * 4);
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
        }
        //TODO check if the new position is valid
        position.move(x, y);
    }
}
