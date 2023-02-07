package com.mewtwo.game.model;

import com.badlogic.gdx.utils.Array;
import com.mewtwo.game.model.entities.Player;
import com.mewtwo.game.model.tiles.Tile;

public class Model implements IModel {

    public final Player player = new Player();
    public final Array<Tile> tiles = new Array<Tile>();

    public Model() {
        for (int x = 0; x < 13; x++)
            for (int y = 0; y < 10; y++)
                tiles.add(new Tile(x * 64, y * 64));
    }

    @Override
    public void movePlayer(float deltaX, float deltaY) {
        player.move(deltaX, deltaY);
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
