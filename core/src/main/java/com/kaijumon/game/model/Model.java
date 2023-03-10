package com.kaijumon.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.utils.Consts;


import static com.kaijumon.game.model.Savegame.saveGame;

public class Model implements IModel {

    public final Player player;
    private final TiledMap tileMap;

    private long timeSinceLastMove;

    public Model(Player player) {
        this.player = player;
        timeSinceLastMove = System.currentTimeMillis();
        tileMap = new TmxMapLoader().load("maps/map.tmx");
    }

    public Model() {
        this.player = new Player(0, 0);
        timeSinceLastMove = System.currentTimeMillis();
        tileMap = new TmxMapLoader().load("maps/map.tmx");
    }

    @Override
    public void movePlayer(int deltaX, int deltaY) {
        if (isTileBlocked(player.getX() + deltaX, player.getY() + deltaY))
            return;
        if (System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis) {
            timeSinceLastMove = System.currentTimeMillis();
            player.move(deltaX, deltaY);
        }
    }

    /**
     * Check if a given tile in the tileMap is blocked.
     *
     * @param x the x coordinate of the tile.
     * @param y the y coordinate of the tile.
     * @return true if the tile is blocked, false otherwise.
     */
    private boolean isTileBlocked(int x, int y) {
        // if outside the map
        if (x >= tileMap.getProperties().get("width", Integer.class) || x < 0)
            return true;
        if (y >= tileMap.getProperties().get("height", Integer.class) || y < 0)
            return true;

        // Check if the "BLOCK" layer has a cell at the given position
        TiledMapTileLayer blockedLayer = (TiledMapTileLayer) tileMap.getLayers().get("BLOCK");
        if (blockedLayer.getCell(x, y) != null)
            return true;
        return false;
    }

        @Override
        public Player getPlayer () {
            return player;
        }

        @Override
        public TiledMap getTileMap () {
            return tileMap;
        }

        @Override
        public long getTimeSinceLastMove () {
            return timeSinceLastMove;
        }

        @Override
        public void saveModel () {
            saveGame(this.getPlayer(), "core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
        }
    }

