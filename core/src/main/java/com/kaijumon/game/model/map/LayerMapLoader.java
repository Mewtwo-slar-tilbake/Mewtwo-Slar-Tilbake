package com.kaijumon.game.model.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.awt.*;
import java.util.HashMap;

public class LayerMapLoader implements IMapLoader {

    private final HashMap<String, TiledMap> tileMapHashTable;

    public LayerMapLoader() {
        tileMapHashTable = new HashMap<>();
    }

    /**
     * Get the layer data for a given layer in a given map.
     * @param mapPath the path to the map.
     * @param layerName the name of the layer.
     * @return a 2D array of booleans representing the layer data.
     */
    public boolean[][] getLayerData(String mapPath, String layerName) {
        TiledMap tileMap;
        if (tileMapHashTable.containsKey(mapPath)){
            tileMap = tileMapHashTable.get(mapPath);
        } else {
            tileMap = new TmxMapLoader().load(mapPath);
            tileMapHashTable.put(mapPath, tileMap);
        }

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

    /**
     * Get the dimensions of a map.
     * @param mapPath the path to the map.
     * @return a Point representing the dimensions of the map.
     */
    public Point getMapDimensions(String mapPath) {
        TiledMap tileMap;
        if (tileMapHashTable.containsKey(mapPath)){
            tileMap = tileMapHashTable.get(mapPath);
        } else {
            tileMap = new TmxMapLoader().load(mapPath);
            tileMapHashTable.put(mapPath, tileMap);
        }

        int x = tileMap.getProperties().get("width", Integer.class);
        int y = tileMap.getProperties().get("height", Integer.class);
        return new Point(x, y);
    }
}
