package com.kaijumon.game.model;

import com.badlogic.gdx.utils.Array;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.tiles.Tile;

public class Model implements IModel {

    public final Player player = new Player(0, 0);
    public final Array<Tile> tiles = new Array<Tile>();

    public Model() {
        for (int x = 0; x < 13; x++)
            for (int y = 0; y < 10; y++)
                tiles.add(new Tile(x * 64, y * 64));
    }

    @Override
    public void movePlayer(float deltaX, float deltaY) {
        player.move((int)deltaX, (int)deltaY);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Array<Tile> getTiles() {
        return tiles;
    }

}
