package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.MoveBehaviourPatrol;
import com.kaijumon.game.model.entities.MoveBehaviourRandom;
import com.kaijumon.game.model.entities.Npc;
import com.kaijumon.game.model.entities.Player;


import java.awt.Point;
import com.kaijumon.game.utils.Consts;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import static com.kaijumon.game.model.Savegame.saveGame;

public class Model implements IModel {

    public final Player player;

    private long timeSinceLastMove;
    private boolean[][] collisionMap;
    private boolean[][] tallGrassMap;
    private String mapPath;
    private final List<Npc> npcList = new ArrayList<>();




    public Model(Player player) {
        this.player = player;
        mapPath = "maps/map.tmx"; //TODO dont hardcode
        IMapLoader mapLoader = new LayerMapLoader();
        collisionMap = mapLoader.getLayerData(mapPath, "BLOCK");
        tallGrassMap = mapLoader.getLayerData(mapPath, "tallgrass");
    }

    public Model(Player player, boolean[][] collisionMap, boolean[][] tallGrassMap) {
        this.player = player;
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
        player.move(deltaX, deltaY);

        /*
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

         */

    }

    /**
     * Check if a given tile in the tileMap is blocked.
     * @param x the x coordinate of the tile.
     * @param y the y coordinate of the tile.
     * @return true if the tile is blocked, false otherwise.
     */
    public boolean isTileBlocked(int x, int y) {
        // if outside the map
        if (x >= collisionMap.length || x < 0)
            return true;
        if (y >= collisionMap[0].length || y < 0)
            return true;

        //TODO check if a npc / player is in the tile.

        // Check if the "BLOCK" layer has a cell at the given position
        return collisionMap[x][y];

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Npc> getNpcList() {
        return npcList;
    }

    @Override
    public String getTileMapPath() {
        return mapPath;
    }


    @Override
    public void saveModel() {
        saveGame(this.getPlayer(),"core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
    }

    private boolean isTallGrass(int x, int y){
        return tallGrassMap[x][y];
    }

}
