package com.kaijumon.game.model;

import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.utils.Consts;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelShould {

        @Test
        public void blockPlayerMovingIntoWall() throws InterruptedException {
            boolean[][] collisionMap = new boolean[3][3];
            boolean[][] tallGrassMap = new boolean[3][3];
            collisionMap[1][1] = true;
            IModel model = new Model(null, new Player(0,0), collisionMap, tallGrassMap);

            // walking all the way around a blocked tile and testing that we can not enter it
            sleep(Consts.timeBetweenMovesMillis + 3);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(1,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.UP);
            assertEquals(new Point(1,0), model.getPlayer().getPosition());

            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.UP);
            assertEquals(new Point(2,1), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(2,1), model.getPlayer().getPosition());

            model.movePlayer(Direction.UP);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(1,2), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.DOWN);
            assertEquals(new Point(1,2), model.getPlayer().getPosition());

            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(0,2), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.DOWN);
            assertEquals(new Point(0,1), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(0,1), model.getPlayer().getPosition());
        }

        @Test
        public void NotMovePlayerOutsideMap() throws InterruptedException {
            boolean[][] collisionMap = new boolean[3][3];
            boolean[][] tallGrassMap = new boolean[3][3];
            collisionMap[1][1] = true;
            IModel model = new Model(null, new Player(0,0), collisionMap, tallGrassMap);

            // trying to move outside the map below and to the left
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.LEFT);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());
            model.movePlayer(Direction.DOWN);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());

            // trying to move outside the map above
            model.movePlayer(Direction.RIGHT);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.RIGHT);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.RIGHT);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);

            // trying to move outside the map to the right
            model.movePlayer(Direction.UP);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.UP);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(Direction.UP);
            sleep(Consts.timeBetweenMovesMillis + 3);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
            model.movePlayer(Direction.UP);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
        }

        @Test
    public void returnValues() throws InterruptedException {
            boolean[][] collisionMap = new boolean[3][3];
            boolean[][] tallGrassMap = new boolean[3][3];
            collisionMap[1][1] = true;
            IModel model = new Model(null, new Player(0,0), collisionMap, tallGrassMap);

            assertEquals(0, model.getPlayer().getPosition().x);
            assertEquals(0, model.getPlayer().getPosition().y);
            assertEquals("maps/map.tmx", model.getTileMapPath());

        }
}
