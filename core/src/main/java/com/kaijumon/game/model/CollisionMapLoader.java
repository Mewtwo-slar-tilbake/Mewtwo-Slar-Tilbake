package com.kaijumon.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.awt.*;

public class CollisionMapLoader implements ICollisionMapLoader {
    public boolean[][] getCollisionMap(String mapPath) {
        TiledMap tileMap = new TmxMapLoader().load(mapPath);
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) tileMap.getLayers().get("BLOCK");

        boolean[][] collisionMap = new boolean[collisionLayer.getWidth()][collisionLayer.getHeight()];
        for (int x = 0; x < collisionLayer.getWidth(); x++) {
            for (int y = 0; y < collisionLayer.getHeight(); y++) {
                collisionMap[x][y] = collisionLayer.getCell(x, y) != null;
            }
        }

        return collisionMap;
    }

    public Point getMapDimensions(String mapPath) {
        TiledMap tileMap = new TmxMapLoader().load(mapPath);
        int x = tileMap.getProperties().get("width", Integer.class);
        int y = tileMap.getProperties().get("height", Integer.class);
        return new Point(x, y);
    }
}