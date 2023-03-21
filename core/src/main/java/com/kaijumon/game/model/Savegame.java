package com.kaijumon.game.model;

import com.google.gson.Gson;
import com.kaijumon.game.model.entities.Player;

import java.io.*;

public class Savegame {

    /**
     * Saves the given Player object to a file using Gson.
     *
     * @param savePlayer the Player object to be saved
     * @param fileName the name of the file where the object will be stored
     *
     * @throws IOException if an error occurs while writing the object to the file
     */
    public static void saveGame(Player savePlayer, String fileName){
        try {
            Gson gson = new Gson();
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
     * @throws IOException if an error occurs while reading the object to the file
     */
    public static Player loadPlayer(String fileName){
        Player loadedPlayer = null;
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            loadedPlayer = gson.fromJson(br, Player.class);
            System.out.println("Successfully loaded game state");

        } catch (IOException e) {
            System.out.println(e);
        }
        return loadedPlayer;
    }
}
