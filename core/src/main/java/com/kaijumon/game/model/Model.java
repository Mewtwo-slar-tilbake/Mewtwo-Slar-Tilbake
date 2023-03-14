package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.utils.Consts;


import java.util.Random;

import static com.kaijumon.game.model.Savegame.saveGame;

public class Model implements IModel {

    public final Player player;

    private long timeSinceLastMove;
    private boolean[][] collisionMap;
    private String mapPath;

    private boolean[][] tallGrassMap;



    public Model(Player player) {
        this.player = player;
        timeSinceLastMove = System.currentTimeMillis();
        mapPath = "maps/map.tmx"; //TODO dont hardcode
        IMapLoader collisionMapLoader = new LayerMapLoader();
        collisionMap = collisionMapLoader.getLayerData(mapPath, "BLOCK");
        tallGrassMap = collisionMapLoader.getLayerData(mapPath, "tallgrass");
    }

    public Model(Player player, boolean[][] collisionMap, boolean[][] tallGrassMap) {
        this.player = player;
        timeSinceLastMove = System.currentTimeMillis();
        mapPath = "maps/map.tmx"; //TODO dont hardcode
        this.collisionMap = collisionMap;
        this.tallGrassMap = tallGrassMap;
    }

    @Override
    public void movePlayer(int deltaX, int deltaY) {
        boolean movedMoreThanOneTile = deltaX != 0 && deltaY != 0 || deltaX + deltaY > 1 || deltaX + deltaY < -1;
        if (movedMoreThanOneTile) {
            return;
        }
        if (isTileBlocked(player.getX() + deltaX, player.getY() + deltaY)) {
            return;
        }
        if (System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis) {
            timeSinceLastMove = System.currentTimeMillis();
            player.move(deltaX, deltaY);
            if (isTallGrass(player.getX() + deltaX, player.getY() + deltaY)) {
                Random random = new Random();
                int max = 100;
                int min = 1;
                int chance = random.nextInt(max - min + 1) + min;
                if (chance <= 20) {
                    System.out.println("You encountered a Kaijumon!");
                }
            }
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
        if (x >= collisionMap.length || x < 0)
            return true;
        if (y >= collisionMap[0].length || y < 0)
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

    private boolean isTallGrass(int x, int y){
        return tallGrassMap[x][y];
    }



}
