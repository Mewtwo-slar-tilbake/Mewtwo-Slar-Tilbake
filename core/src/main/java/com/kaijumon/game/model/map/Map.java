package com.kaijumon.game.model.map;

import com.kaijumon.game.model.entities.npc.Npc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
    protected final String mapPath;
    private final boolean[][] collisionMap;
    private final boolean[][] tallGrassMap;
    private final Point mapDimensions;

    private final List<Npc> npcList;
    private final List<MapTransistion> mapTransistions;

    protected Map(String mapPath) {
        this.mapPath = mapPath;
        IMapLoader mapLoader = new LayerMapLoader();
        mapDimensions = mapLoader.getMapDimensions(mapPath);
        this.collisionMap = mapLoader.getLayerData(mapPath, "BLOCK");
        this.tallGrassMap = mapLoader.getLayerData(mapPath, "tallgrass");
        this.mapTransistions = new ArrayList<>();

        npcList = new ArrayList<>();
    }

    protected Point getMapDimensions() {
        return new Point(mapDimensions);
    }

    protected boolean isTileBlocked(Point point) {
        // if outside the map
        if (point.x >= mapDimensions.x || point.x < 0)
            return true;
        if (point.y >= mapDimensions.y || point.y < 0)
            return true;

        //TODO check if a npc / player is in the point.

        // Check if the "BLOCK" layer has a cell at the given position
        return collisionMap[point.x][point.y];

    }

    protected boolean isTallGrass(Point point){
        // if outside the map
        if (point.x >= mapDimensions.x || point.x < 0)
            return false;
        if (point.y >= mapDimensions.y || point.y < 0)
            return false;

        return tallGrassMap[point.x][point.y];
    }

    protected void addNpc(Npc npc){
        npcList.add(npc);
    }

    protected List<Npc> getNpcList() {
        return npcList;
    }

    protected void addMapTransition(MapTransistion mapTransistion){
        mapTransistions.add(mapTransistion);
    }

    public boolean isDoor(Point playerPosition) {
        return getMapTransition(playerPosition) != null;
    }

    public MapTransistion getMapTransition(Point playerPosition) {
        for (MapTransistion mapTransistion : mapTransistions) {
            if (mapTransistion.getFromPoint().equals(playerPosition)) {
                return mapTransistion;
            }
        }
        return null;
    }
}