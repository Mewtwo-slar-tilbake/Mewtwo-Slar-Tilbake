package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.utils.Consts;
import java.awt.*;


import static com.kaijumon.game.model.Savegame.saveGame;

public class Model implements IModel {

    public final Player player;

    private long timeSinceLastMove;
    private Point mapDimensions;
    private boolean[][] collisionMap;
    private String mapPath;


    public Model(Player player) {
        this.player = player;
        timeSinceLastMove = System.currentTimeMillis();
        mapPath = "maps/map.tmx";
        ICollisionMapLoader collisionMapLoader = new CollisionMapLoader();
        mapDimensions = collisionMapLoader.getMapDimensions(mapPath);
        collisionMap = collisionMapLoader.getCollisionMap(mapPath);
    }

    public Model() {
        this.player = new Player(0,0);
        timeSinceLastMove = System.currentTimeMillis();
        mapPath = "maps/map.tmx";
        ICollisionMapLoader collisionMapLoader = new CollisionMapLoader();
        mapDimensions = collisionMapLoader.getMapDimensions(mapPath);
        collisionMap = collisionMapLoader.getCollisionMap(mapPath);
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
     * @param x the x coordinate of the tile.
     * @param y the y coordinate of the tile.
     * @return true if the tile is blocked, false otherwise.
     */
    private boolean isTileBlocked(int x, int y) {
        // if outside the map
        if (x >= mapDimensions.x || x < 0)
            return true;
        if (y >= mapDimensions.y || y < 0)
            return true;

        // Check if the "BLOCK" layer has a cell at the given position
        return collisionMap[x][y];

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String getTileMapPath() {
        return mapPath;
    }

    @Override
    public long getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

    @Override
    public void saveModel() {
        saveGame(this.getPlayer(),"core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
    }



}
