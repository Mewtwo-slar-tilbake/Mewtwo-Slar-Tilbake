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
            collisionMap[1][1] = true;
            IModel model = new Model(new Player(0,0), collisionMap);

            // walking all the way around a blocked tile and testing that we can not enter it
            sleep(Consts.timeBetweenMovesMillis + 3);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());
            model.movePlayer(1, 0);
            assertEquals(new Point(1,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(0, 1);
            assertEquals(new Point(1,0), model.getPlayer().getPosition());

            model.movePlayer(1, 0);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(0, 1);
            assertEquals(new Point(2,1), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(-1, 0);
            assertEquals(new Point(2,1), model.getPlayer().getPosition());

            model.movePlayer(0, 1);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(-1, 0);
            assertEquals(new Point(1,2), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(0, -1);
            assertEquals(new Point(1,2), model.getPlayer().getPosition());

            model.movePlayer(-1, 0);
            assertEquals(new Point(0,2), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(0, -1);
            assertEquals(new Point(0,1), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(1, 0);
            assertEquals(new Point(0,1), model.getPlayer().getPosition());
        }

        @Test
        public void NotMovePlayerOutsideMap() throws InterruptedException {
            boolean[][] collisionMap = new boolean[3][3];
            collisionMap[1][1] = true;
            IModel model = new Model(new Player(0,0), collisionMap);

            // trying to move outside the map below and to the left
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(-1, 0);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());
            model.movePlayer(0, -1);
            assertEquals(new Point(0,0), model.getPlayer().getPosition());

            // trying to move outside the map above
            model.movePlayer(1, 0);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(1, 0);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(1, 0);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(1, 0);
            assertEquals(new Point(2,0), model.getPlayer().getPosition());
            sleep(Consts.timeBetweenMovesMillis + 3);

            // trying to move outside the map to the right
            model.movePlayer(0, 1);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(0, 1);
            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(0, 1);
            sleep(Consts.timeBetweenMovesMillis + 3);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
            model.movePlayer(0, 1);
            assertEquals(new Point(2,2), model.getPlayer().getPosition());
        }

        @Test
        public void NotMovePlayerMoreThanOneTile() throws InterruptedException {
            boolean[][] collisionMap = new boolean[10][10];
            IModel model = new Model(new Player(5,5), collisionMap);


            sleep(Consts.timeBetweenMovesMillis + 3);
            model.movePlayer(2, 0);
            assertEquals(new Point(5,5), model.getPlayer().getPosition());
            model.movePlayer(0, 2);
            assertEquals(new Point(5,5), model.getPlayer().getPosition());

            model.movePlayer(1, 1);
            assertEquals(new Point(5,5), model.getPlayer().getPosition());
            model.movePlayer(1, -1);
            assertEquals(new Point(5,5), model.getPlayer().getPosition());
            model.movePlayer(-1, 1);
            assertEquals(new Point(5,5), model.getPlayer().getPosition());
            model.movePlayer(-1, -1);
            assertEquals(new Point(5,5), model.getPlayer().getPosition());

        }
}