package com.kaijumon.game.utils;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class MathUtil {

    /**
     * A clamped Lerp function
     * @param a starting position
     * @param b ending position
     * @param t time variable (this will be clamped between 0 and 1)
     * @return float between a and b based on t
     */
    static public float lerp(int a, int b, float t){
        float clampedT = clamp(t, 0, 1);
        return a + (b - a) * clampedT;
    }

    /**
     * A clamped 2d lerp function
     * @param a starting point
     * @param b ending point
     * @param t time variable (this will be clamped between 0 and 1)
     * @return Vector2 between a and b based on t
     */
    static public Vector2 lerp2d(Point a, Point b, float t){
        float x = lerp(a.x, b.x, t);
        float y = lerp(a.y, b.y, t);
        return new Vector2(x, y);
    }
}