package com.kaijumon.game.model.map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.utils.Consts;
import com.kaijumon.game.utils.Methods;

import java.awt.Point;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class MapController {
    private final String[] mapPaths;
    private String currentMapPath;
    private Map currentMap;
    private HashMap<String, Map> mapDictionary;
    private static MapController instance;


    /**
     * Creates a new MapController
     * @param mapPaths list of the paths to the maps. first string in the list is the starting map.
     */
    private MapController(String[] mapPaths) {
        this.mapPaths = mapPaths;
        this.currentMapPath = mapPaths[0];
        loadMaps();
        this.currentMap = mapDictionary.get(currentMapPath);
    }

    /**
     * Changes the current map to the map in the given path.
     * @param mapPath the path to the map to change to.
     */
    public static MapController getInstance() {
        if (instance == null) {
            instance = new MapController(Consts.mapPaths);
        }
        return instance;
    }

    /**
     * Loads all the maps in the mapPaths array.
     */
    private void loadMaps() {
        mapDictionary = new HashMap<>();
        for (String mapPath : mapPaths) {
            //TODO check that map exists
            mapDictionary.put(mapPath, new Map(mapPath));
        }
        loadMapTransitions();
    }

    /**
     * Changes the current map to the map in the given path.
     * @param mapPath the path to the map to change to.
     */
    public void setLoadGameMap(String mapPaht){
        changeMap(mapPaht);
    }

    /**
     * Loads the map transitions from the mapTransition.json file, into a list in each Map.
     */
    private void loadMapTransitions(){
        if (Methods.isJUnitTest())
            return;
        try {

            Gson gson = new Gson();
            FileReader file = new FileReader(Consts.mapTransitions);
            JsonReader reader = new JsonReader(file);
            JsonArray jsonList = gson.fromJson(reader, JsonArray.class);


            for (int i = 0; i < jsonList.size(); i++) {
                JsonObject object = jsonList.get(i).getAsJsonObject();
                String mapPath = object.get("map").getAsString();
                Map map = mapDictionary.get(mapPath);
                if (map == null)
                    throw new IllegalStateException("MapController: Could not find map " + object.get("map").getAsString() + " in the map dictionary.");

                JsonArray transitionArray = object.getAsJsonArray("transitions");
                for (int j = 0; j < transitionArray.size(); j++) {
                    MapTransistion mapTransition = gson.fromJson(transitionArray.get(j), MapTransistion.class);
                    map.addMapTransition(mapTransition);

                }
            }


        } catch (Exception e) {
            throw new IllegalStateException("MapController: Could not load file " + "assets/maps/mapTransition.json" + " [ERROR MSG]: " + e.getMessage());
        }
    }

    /**
     * Method to get the current map.
     * @return Map currentMap
     */
    public final void moveToKaijuCenter(Player player){
        changeMap(Consts.kaijuCenterMapPath);
        player.setPosition(4, 2);
    }

    private void changeMap(String mapPath){
        currentMapPath = mapPath;
        currentMap = mapDictionary.get(mapPath);
    }

    /**
     * method to get the dimensions of the current map.
     * @return Point representing the dimensions of the current map.
     */
    public Point getMapDimensions() {
        return currentMap.getMapDimensions();
    }



    /**
     * Check if a given point in the tileMap is blocked.
     * @param point the point to check
     * @return true if the point is blocked, false otherwise.
     */
    public boolean isTileBlocked(Point point) {
        return currentMap.isTileBlocked(point);

    }

    /**
     * Check if a given point in the tileMap is tall grass.
     * @param point the point to check
     * @return true if the point is tall grass, false otherwise.
     */
    public boolean isTallGrass(Point point){
        return currentMap.isTallGrass(point);
    }

    /**
     * Method to get the path to the current map.
     * @return String mapPath
     */
    public String getTileMapPath() {
        return currentMapPath;
    }

    /**
     * Method to add an npc to the current map.
     * @param npc the npc to add.
     */
    public void addNpc(Npc npc){
        addNpc(npc, currentMapPath);
    }

    /**
     * Method to add an npc to a specific map.
     * @param npc the npc to add.
     * @param mapPath the path to the map to add the npc to.
     */
    public void addNpc(Npc npc, String mapPath){
        mapDictionary.get(mapPath).addNpc(npc);
    }

    /**
     * Method to get the list of npcs in the current map.
     * @return List of Npcs
     */
    public List<Npc> getNpcList() {
        return currentMap.getNpcList();
    }

    /**
     * Method to check if a given point is a door.
     * @param playerPosition the point to check.
     * @return true if the point is a door, false otherwise.
     */
    public boolean isDoor(Point playerPosition) {
        return currentMap.isDoor(playerPosition);
    }

    /**
     * Method to handle a door.
     * @param player the player to handle the door.
     */
    public void handleDoor(Player player) {
        MapTransistion mapTransistion = currentMap.getMapTransition(player.getPosition());
        changeMap(mapTransistion.toMap);
        Point to = mapTransistion.getToPoint();
        player.setPosition(to.x, to.y);
    }
}
