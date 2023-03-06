package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;
import org.junit.jupiter.api.Test;

import static com.kaijumon.game.model.Savegame.loadPlayer;
import static com.kaijumon.game.model.Savegame.saveGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class SavingShould {


    /**
     * Saves a player object to a txt file and loads it back and checks if it is in the same position.
     */
    @Test
    public void samePositionTest()  {
        Player testPlayer = new Player(10,200);
        saveGame(testPlayer, "./src/test/java/com/kaijumon/game/model/testfile.txt");

        Player loadedPlayer = loadPlayer("./src/test/java/com/kaijumon/game/model/testfile.txt");
        System.out.println(loadedPlayer);
        assertEquals(testPlayer.getX(), loadedPlayer.getX());
        assertEquals(testPlayer.getY(), loadedPlayer.getY());

    }
}