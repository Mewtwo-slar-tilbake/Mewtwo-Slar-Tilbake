package com.kaijumon.game.utils;

public class Consts {
    public static final int tileSize = 64;
    public static final int timeBetweenMovesMillis = 300;

    // Asset paths
    public static final String skinPath = "assets/skin/uiskin.json";
    public static final String dialogPath = "assets/dialogue.json";
    public static final String mapTransitions = "assets/maps/mapTransition.json";
    public static final String playerSpritePath = "assets/characters/player.png";
    public static final String[] npcSpritePaths = {"assets/characters/woman1.png"};
    public static final boolean DEBUG = false;

    public static String kaijuCenterMapPath = "assets/maps/kaijuCenter.tmx";
    public static String mainMap = "assets/maps/map.tmx";
    public static final String[] mapPaths = {mainMap, kaijuCenterMapPath, "assets/maps/home_floor1.tmx", "assets/maps/home_floor2.tmx", "assets/maps/store.tmx", "assets/maps/house1.tmx", "assets/maps/house2.tmx"};



    // ------ Test Maps ------
    public static boolean[][] getCollisionTestMap() {
        boolean[][] collisionMap = new boolean[100][100];
        collisionMap[1][1] = true;
        return collisionMap;
    }

    public static boolean[][] getGrassTestMap() {
        return new boolean[100][100];
    }

}
