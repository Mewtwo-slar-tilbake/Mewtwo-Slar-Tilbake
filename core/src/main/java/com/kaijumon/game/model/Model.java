package com.kaijumon.game.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.utils.Consts;

public class Model implements IModel {

    public final Player player = new Player(0, 0);
    private final TiledMap tileMap;

    private long timeSinceLastMove;

    public Model() {
        timeSinceLastMove = System.currentTimeMillis();
        tileMap = new TmxMapLoader().load("map.tmx");
    }

    @Override
    public void movePlayer(int deltaX, int deltaY) {
        if (System.currentTimeMillis() - timeSinceLastMove > Consts.timeBetweenMovesMillis) {
            timeSinceLastMove = System.currentTimeMillis();
            player.move(deltaX, deltaY);
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public TiledMap getTileMap() {
        return tileMap;
    }

    @Override
    public long getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

}
