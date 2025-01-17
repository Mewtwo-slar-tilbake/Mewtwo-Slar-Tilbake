package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;
import org.junit.jupiter.api.Test;

import static com.kaijumon.game.model.Savegame.loadPlayer;
import static com.kaijumon.game.model.Savegame.saveGame;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingShould {

    @Test
    public void savePlayerPosition()  {
        Player player = new Player(10,200);
        saveGame(player, "core/src/test/java/com/kaijumon/game/model/testfile.txt");

        Player loadedPlayer = loadPlayer("core/src/test/java/com/kaijumon/game/model/testfile.txt");
        System.out.println(loadedPlayer);
        assertEquals(player.getPosition(), loadedPlayer.getPosition());
    }

}
