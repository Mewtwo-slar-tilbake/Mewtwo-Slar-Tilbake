package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.model.entities.npc.*;
import com.kaijumon.game.utils.Consts;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import static com.kaijumon.game.model.Savegame.saveGame;
import static java.util.Arrays.asList;

public class Model implements IModel {

    public final Player player;

    private boolean[][] collisionMap;
    private boolean[][] tallGrassMap;
    private String mapPath;
    private final List<Npc> npcList = new ArrayList<>();

    public Model(Player player) {
        this.player = player;
        mapPath = Consts.mapPath;
        IMapLoader mapLoader = new LayerMapLoader();
        collisionMap = mapLoader.getLayerData(mapPath, "BLOCK");
        tallGrassMap = mapLoader.getLayerData(mapPath, "tallgrass");
        createNpcs();
    }

    public Model(Player player, boolean[][] collisionMap, boolean[][] tallGrassMap) {
        this.player = player;
        mapPath = Consts.mapPath;
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
        Point playerPosition = player.getPosition();
        player.move(deltaX, deltaY);

        // check if player encountered a Kaijumon in tall grass
        boolean playerMoved = !playerPosition.equals(player.getPosition());
        if (playerMoved && isTallGrass(player.getX(), player.getY())) {
            Random random = new Random();
            int max = 100;
            int min = 1;
            int chance = random.nextInt(max - min + 1) + min;
            if (chance <= 20) {
                System.out.println("You encountered a Kaijumon!");
            }
        }
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

    public Point getMapDimensions(){
        return new Point(collisionMap.length, collisionMap[0].length);
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

    private void createNpcs(){
        NpcFactory npcFactory = new NpcFactory(this);
        npcList.add(npcFactory.getNpc(new ArrayList<>(asList(new Point(5,18)))));
        npcList.add(npcFactory.getNpc(new ArrayList<>(asList(new Point(15,26), new Point(9,26), new Point(9,37), new Point(18,37) ))));
        npcList.add(npcFactory.getNpc(new Point(5,20), new Point(15,30)));
    }

    private boolean isTallGrass(int x, int y){
        return tallGrassMap[x][y];
    }

}
