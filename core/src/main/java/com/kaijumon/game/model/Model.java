package com.kaijumon.game.model;

import com.badlogic.gdx.utils.Array;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.tiles.Tile;
import com.kaijumon.game.utils.Consts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
    public void saveGameState(){
        try (FileOutputStream saveFile = new FileOutputStream("core/src/main/java/com/kaijumon/game/savefiles/testing.txt")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(saveFile);
            objectOutputStream.writeObject(player);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Successfully saved game state");
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
