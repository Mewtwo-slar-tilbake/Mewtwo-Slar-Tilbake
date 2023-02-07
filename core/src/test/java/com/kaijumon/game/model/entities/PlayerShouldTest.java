package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerShouldTest {

    @Test
    public void MoveForwards() {
        // Arrange
        Player player = new Player(10, 10);
        int x = player.getX();
        int y = player.getY();

        // Act
        player.move(10, 10);

        // Assert
        int newX = player.getX();
        int newY = player.getY();
        assertEquals(x + 10, newX);
        assertEquals(y + 10, newY);
    }

    @Test void NotMoveNegative() {
        // Arrange
        Player player = new Player(0, 0);

        // Act
        player.move(-10, -10);

        // Assert
        int newX = player.getX();
        int newY = player.getY();
        assertTrue(newX >= 0);
        assertTrue(newY >= 0);
    }

}