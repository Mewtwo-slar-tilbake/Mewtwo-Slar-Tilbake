package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.Direction;
import com.kaijumon.game.utils.Consts;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerShould {

    @Test
    public void MoveForwards() throws InterruptedException {
        // Arrange
        Player player = new Player(10, 10);
        Point position = player.getPosition();
        sleep(Consts.timeBetweenMovesMillis + 3);

        // Act
        player.move(Direction.UP);
        // Assert
        Point newPosition = player.getPosition();
        assertEquals(position.x, newPosition.x);
        assertEquals(position.y + 1, newPosition.y);
    }

}
