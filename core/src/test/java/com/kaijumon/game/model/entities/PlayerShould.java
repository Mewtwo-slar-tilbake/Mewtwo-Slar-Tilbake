package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerShould {

    @Test
    public void MoveForwards() {
        // Arrange
        Player player = new Player(10, 10);
        float x = player.x;
        float y = player.y;

        // Act
        player.move(10, 10);

        // Assert
        float newX = player.x;
        float newY = player.y;
        assertEquals(x + 10, newX);
        assertEquals(y + 10, newY);
    }

    @Test void MoveNegative() {
        // Arrange
        Player player = new Player(0, 0);
        float x = player.x;
        float y = player.y;

        // Act
        player.move(-10, -10);

        // Assert
        float newX = player.x;
        float newY = player.y;
        assertTrue(newX >= 0);
        assertTrue(newY >= 0);
    }

}
