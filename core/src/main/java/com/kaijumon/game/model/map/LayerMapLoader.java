package com.kaijumon.game.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.awt.*;

public class LayerMapLoader implements IMapLoader {
    public boolean[][] getLayerData(String mapPath, String layerName) {
        TiledMap tileMap = new TmxMapLoader().load(mapPath);
        TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(layerName);
        if (layer == null) {
            Point mapDimensions = getMapDimensions(mapPath);
            return new boolean[mapDimensions.x][mapDimensions.y];
        }

        boolean[][] mapData = new boolean[layer.getWidth()][layer.getHeight()];
        for (int x = 0; x < layer.getWidth(); x++) {
            for (int y = 0; y < layer.getHeight(); y++) {
                mapData[x][y] = layer.getCell(x, y) != null;
            }
        }

        return mapData;
    }

    public Point getMapDimensions(String mapPath) {
        TiledMap tileMap = new TmxMapLoader().load(mapPath);
        int x = tileMap.getProperties().get("width", Integer.class);
        int y = tileMap.getProperties().get("height", Integer.class);
        return new Point(x, y);
    }
}