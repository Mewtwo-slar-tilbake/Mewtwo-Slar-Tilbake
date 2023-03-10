package com.kaijumon.game.model;

import java.awt.*;

public interface ICollisionMapLoader {
    boolean[][] getCollisionMap(String mapPath);

    Point getMapDimensions(String mapPath);
}