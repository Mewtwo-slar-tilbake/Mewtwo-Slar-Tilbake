package com.kaijumon.game.model.entities;

import com.kaijumon.game.model.Direction;
import com.kaijumon.game.utils.Consts;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerShould {

    @Test
    public void moveForwards() throws InterruptedException {
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

    @Test
    public void changeDirection() throws InterruptedException {
        // Arrange
        Player player = new Player(0, 0);
        Direction direction = player.getDirection();
        Direction newDirection = Direction.getOpposite(direction);
        sleep(Consts.timeBetweenMovesMillis + 3);

        // Act
        player.setDirection(newDirection);

        // Assert
        assertEquals(player.getDirection(), newDirection);
    }

}
