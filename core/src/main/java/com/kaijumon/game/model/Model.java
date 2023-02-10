package com.kaijumon.game.model;

import com.badlogic.gdx.utils.Array;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.tiles.Tile;

public class Model implements IModel {

    public final Player player = new Player(0, 0);
    public final Array<Tile> tiles = new Array<Tile>();

    private long timeSinceLastMove;
    int timeBetweenMoves = 1000;

    public Model() {
        timeSinceLastMove = System.currentTimeMillis();
        for (int x = 0; x < 13; x++)
            for (int y = 0; y < 10; y++)
                tiles.add(new Tile(x * 64, y * 64));
    }

    @Override
    public void movePlayer(int deltaX, int deltaY) {
        if (System.currentTimeMillis() - timeSinceLastMove > timeBetweenMoves) {
            timeSinceLastMove = System.currentTimeMillis();
            player.move(deltaX, deltaY);
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Array<Tile> getTiles() {
        return tiles;
    }

    @Override
    public long getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

}
