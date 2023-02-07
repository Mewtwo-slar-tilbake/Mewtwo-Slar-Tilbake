package com.mewtwo.game.model.tiles;

import com.badlogic.gdx.math.Rectangle;

/**
 * Represents a tile with a texture and hitbox.
 */
public class Tile {

    public Rectangle rect;

    public Tile(float x, float y) {
        rect = new Rectangle();

        rect.x = x;
        rect.y = y;

        rect.width = 64;
        rect.height = 64;
    }

}
