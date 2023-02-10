package com.kaijumon.game.utils;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.kaijumon.game.utils.MathUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilShould {

    @Test
    public void Lerp() {
        assertEquals(0, lerp(0, 1, 0));
        assertEquals(1, lerp(0, 1, 1));
        assertEquals(0, lerp(0, 1, -1));
        assertEquals(1, lerp(0, 1, 2));
        assertEquals(0.5f, lerp(0, 1, 0.5f));
        assertEquals(0.25f, lerp(0, 1, 0.25f));
        assertEquals(2.5f, lerp(0, 5, 0.5f));
    }

    @Test
    public void Lerp2d(){
        assertEquals(new Vector2(0,0), lerp2d(new Point(0,0), new Point(1,1), 0));
        assertEquals(new Vector2(1,1), lerp2d(new Point(0,0), new Point(1,1), 1));
        assertEquals(new Vector2(0,0), lerp2d(new Point(0,0), new Point(1,1), -1));
        assertEquals(new Vector2(1,1), lerp2d(new Point(0,0), new Point(1,1), 2));
        assertEquals(new Vector2(0.5f,0.5f), lerp2d(new Point(0,0), new Point(1,1), 0.5f));
    }
}