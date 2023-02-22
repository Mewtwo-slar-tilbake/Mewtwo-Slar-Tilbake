package com.kaijumon.game.model;

import com.badlogic.gdx.utils.Array;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.tiles.Tile;
import com.kaijumon.game.utils.Consts;


import static com.kaijumon.game.model.Savegame.saveGame;

public class Model implements IModel {

    public final Player player;
    public final Array<Tile> tiles = new Array<Tile>();

    private long timeSinceLastMove;

    public Model(Player player) {
        this.player = player;
        timeSinceLastMove = System.currentTimeMillis();
        for (int x = 0; x < 13; x++)
            for (int y = 0; y < 10; y++)
                tiles.add(new Tile(x * 64, y * 64));
    }

    public Model() {
        this.player = new Player(0,0);
        timeSinceLastMove = System.currentTimeMillis();
        for (int x = 0; x < 13; x++)
            for (int y = 0; y < 10; y++)
                tiles.add(new Tile(x * 64, y * 64));
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
    public Array<Tile> getTiles() {
        return tiles;
    }

    @Override
    public long getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

    @Override
    public void saveModel() {
        saveGame(this.getPlayer(),"core/src/main/java/com/kaijumon/game/savefiles/testing.txt");
    }


}
