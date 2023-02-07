package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerShould {

    @Test
    public void MoveForwards() {
        // Arrange
        Player player = new Player();
        float x = player.rect.x;
        float y = player.rect.y;

        // Act
        player.move(10, 10);

        // Assert
        float newX = player.rect.x;
        float newY = player.rect.y;
        assertEquals(x + 10, newX);
        assertEquals(y + 10, newY);
    }

}
