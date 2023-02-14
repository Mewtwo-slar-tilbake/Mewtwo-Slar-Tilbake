package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SavingShould {


    /**
     * Saves a player object to a txt file and loads it back and checks if it is in the same position.
     */
    @Test
    public void samePositionTest() throws IOException, ClassNotFoundException {
        Player testPlayer = new Player(10,10);

        FileOutputStream fileOutputStream = new FileOutputStream("./src/test/java/com/kaijumon/game/model/testfile.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(testPlayer);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("./src/test/java/com/kaijumon/game/model/testfile.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Player loadedTestPlayer = (Player) objectInputStream.readObject();
        objectInputStream.close();

        assertEquals(loadedTestPlayer.getX(), testPlayer.getX());
        assertEquals(loadedTestPlayer.getY(), testPlayer.getY());
    }
}
