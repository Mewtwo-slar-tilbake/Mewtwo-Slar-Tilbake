package com.kaijumon.game.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.map.MapController;
import com.kaijumon.game.utils.ItemTypeAdapterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Savegame {

    /**
     * Saves the given Player object to a file using Gson.
     *
     * @param savePlayer the Player object to be saved
     * @param fileName the name of the file where the object will be stored
     */
    public static void saveGame(Player savePlayer, String fileName){
        try {
            savePlayer.setMapPath(MapController.getInstance().getTileMapPath());
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                    .create();

            String json = gson.toJson(savePlayer);


            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
            System.out.println("Successfully saved game state");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**
     * Loads a Player object from a file using Gson.
     *
     * @param fileName the name of the file from which the object will be loaded
     *
     * @return the loaded Player object, or null if the file could not be loaded
     */
    public static Player loadPlayer(String fileName){
        Player loadedPlayer = null;
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                    .create();

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            loadedPlayer = gson.fromJson(br, Player.class);
            System.out.println("Successfully loaded game state");


        } catch (IOException e) {
            System.out.println(e);
        }
        return loadedPlayer;
    }
}
