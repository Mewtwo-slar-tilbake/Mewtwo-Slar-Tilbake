package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerShould {

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
        Point playerPosition = player.getPosition();
        assertEquals(x + 10, newX);
        assertEquals(y + 10, newY);
        assertEquals(newX, playerPosition.x);
        assertEquals(newY, playerPosition.y);
    }

}
