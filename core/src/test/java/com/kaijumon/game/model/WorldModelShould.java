package com.kaijumon.game.model;

import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.entities.Bag;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.utils.Consts;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorldModelShould {

        @Test
        public void blockPlayerMovingIntoWall() throws InterruptedException {
            // Arrange
            IWorldModel model = new WorldModel(null, new Player(0, 0));

            // Act and assert
            // walking all the way around a blocked tile and testing that we can not enter it
            assertEquals(new Point(0,0), model.getPlayer().getPosition());
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(1,0), model.getPlayer().getPosition());
            model.movePlayer(Direction.UP);
            assertEquals(new Point(1,0), model.getPlayer().getPosition());

            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            model.movePlayer(Direction.UP);
            assertEquals(new Point(2,1), model.getPlayer().getPosition());
            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(2,1), model.getPlayer().getPosition());

            model.movePlayer(Direction.UP);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(1,2), model.getPlayer().getPosition());
            model.movePlayer(Direction.DOWN);
            assertEquals(new Point(1,2), model.getPlayer().getPosition());

            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(0,2), model.getPlayer().getPosition());
            model.movePlayer(Direction.DOWN);
            assertEquals(new Point(0,1), model.getPlayer().getPosition());
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(0,1), model.getPlayer().getPosition());
        }

        @Test
        public void NotMovePlayerOutsideMap() throws InterruptedException {
            Point mapSize = new Point(Consts.getCollisionTestMap().length, Consts.getCollisionTestMap()[0].length);
            IWorldModel model = new WorldModel(null, new Player(0,0));

            // trying to move outside the map below and to the left
            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());
            model.movePlayer(Direction.DOWN);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());

            // trying to move outside the map above
            model.getPlayer().setPosition(mapSize.x - 2, mapSize.y - 2);
            assertEquals(new Point(98,98), model.getPlayer().getPosition());
            model.movePlayer(Direction.UP);
            assertEquals(new Point(98,99), model.getPlayer().getPosition());
            model.movePlayer(Direction.UP);
            assertEquals(new Point(98,99), model.getPlayer().getPosition());
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(99,99), model.getPlayer().getPosition());
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(99,99), model.getPlayer().getPosition());
        }

        @Test
        public void returnValues() throws InterruptedException {
            IWorldModel model = new WorldModel(null, new Player(0, 0));//, collisionMap, tallGrassMap);

            assertEquals(0, model.getPlayer().getPosition().x);
            assertEquals(0, model.getPlayer().getPosition().y);
            assertEquals("assets/maps/map.tmx", model.getTileMapPath());
        }

        @Test
        public void startWithNPCs() {
            // Arrange
            KaijumonGame game = mock(KaijumonGame.class);
            Player player = mock(Player.class);
            Bag bag = mock(Bag.class);

            when(bag.getCrewSize()).thenReturn(1);
            when(player.getBag()).thenReturn(bag);

            // Act
            IWorldModel model = new WorldModel(game, player);

            // Assert
            assert(model.getNpcList().size() > 0);
        }

}
