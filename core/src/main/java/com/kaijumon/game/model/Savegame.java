package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;

import java.io.*;

public class Savegame {

    /**
     * Saves the given Player object to a file using serialization.
     *
     * @param savePlayer the Player object to be saved
     * @param fileName the name of the file where the object will be stored
     *
     * @throws IOException if an error occurs while writing the object to the file
     */
    public static void saveGame(Player savePlayer, String fileName){
        try (FileOutputStream saveFile = new FileOutputStream(fileName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(saveFile);
            objectOutputStream.writeObject(savePlayer);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Successfully saved game state");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**
     * Loads a Player object from a file using deserialization.
     *
     * @param fileName the name of the file from which the object will be loaded
     *
     * @return the loaded Player object, or null if the file could not be loaded
     *
     * @throws IOException if an error occurs while reading the object from the file
     * @throws RuntimeException if the class of the object in the file cannot be found
     */
    public static Player loadPlayer(String fileName){
        Player loadedPlayer = null;
        try (FileInputStream loadFile = new FileInputStream(fileName)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(loadFile);
            Player loadedTestPlayer = (Player) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(loadedTestPlayer);
            loadedPlayer = loadedTestPlayer;
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loadedPlayer;
    }

}
