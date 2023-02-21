package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;

import java.io.*;

public class Savegame {


    public Savegame(){}

    public void savePlayer(Player savePlayer, String fileName){
        //"core/src/main/java/com/kaijumon/game/savefiles/testing.txt"
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

    public Player loadPlayer(String fileName){
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
