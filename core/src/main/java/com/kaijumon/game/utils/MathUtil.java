package com.kaijumon.game.utils;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class MathUtil {

    static public float lerp(int a, int b, float t){
        return a + (b-a)*t;
    }

    static public Vector2 lerp2d(Point a, Point b, float t){
        float x = lerp(a.x, b.x, t);
        float y = lerp(a.y, b.y, t);
        return new Vector2(x, y);
    }
}
