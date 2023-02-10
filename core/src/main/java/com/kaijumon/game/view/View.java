package com.kaijumon.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.tiles.Tile;

import java.awt.*;

import static com.kaijumon.game.utils.MathUtil.lerp2d;

public class View implements IView {

    private final Texture playerTexture;
    private final Texture grassTexture;

    private final KaijumonGame game;
    private final IModel model;
    private final OrthographicCamera camera;
    private final int tileSize = 64;
    private Point lastPlayerPos; //TODO REFACTOR !!!!!!!!!

    public View(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        playerTexture = new Texture(Gdx.files.internal("player.jpg"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        lastPlayerPos = model.getPlayer().getPosition();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        for (Tile tile : model.getTiles()) {
            game.batch.draw(grassTexture, tile.rect.x, tile.rect.y);
        }

        Player player = model.getPlayer();
        float t = System.currentTimeMillis() - model.getTimeSinceLastMove();
        if (t > 1000){
            t = 1000;
            lastPlayerPos = player.getPosition();
        }
        t = t / 1000;
        Point oldPos = new Point(lastPlayerPos.x * tileSize, lastPlayerPos.y * tileSize);
        Point newPos = new Point(player.getX() * tileSize, player.getY() * tileSize);

        Vector2 pos = lerp2d(oldPos, newPos , t) ;
        System.out.println("oldx: " + oldPos.x + " - oldy: " + oldPos.y + "-----------newx: " + newPos.x + " - newy: " + newPos.y + "-------------x: " + pos.x + " - y: " + pos.y + " - t: " + t);

        game.batch.draw(
                playerTexture,
                /*
                player.getX() * tileSize,
                player.getY() * tileSize,

                 */
                pos.x,
                pos.y,
                tileSize,
                tileSize
        );


        game.batch.end();
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        grassTexture.dispose();
    }

}
