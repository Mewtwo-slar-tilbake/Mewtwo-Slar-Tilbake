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
import com.kaijumon.game.utils.Consts;

import java.awt.*;

import static com.kaijumon.game.utils.MathUtil.lerp2d;

public class View implements IView {

    private final Texture playerTexture;
    private final Texture grassTexture;
    private final KaijumonGame game;
    private final IModel model;
    private final OrthographicCamera camera;
    private final Point lastPlayerPos;

    public View(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        playerTexture = new Texture(Gdx.files.internal("player.jpg"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        lastPlayerPos = new Point(model.getPlayer().getX(), model.getPlayer().getY());
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

        Point playerPosition = calculatePlayerPos();
        game.batch.draw(
                playerTexture,
                playerPosition.x,
                playerPosition.y,
                Consts.tileSize,
                Consts.tileSize
        );


        game.batch.end();
    }

    /**
     * Calculates the player sprite position based on the time since the last move
     * the player moves instantly in the model, so we need to interpolate the position of the sprite.
     * @return Point with the player sprite position
     */
    private Point calculatePlayerPos(){
        float timeSinceMoveStarted = System.currentTimeMillis() - model.getTimeSinceLastMove();
        Player player = model.getPlayer();

        if (timeSinceMoveStarted > Consts.timeBetweenMovesMillis)
            lastPlayerPos.move(player.getX(), player.getY());

        if (lastPlayerPos.equals(player.getPosition()))
            return worldToScreen(player.getPosition());

        Point oldPos = new Point(worldToScreen(lastPlayerPos));
        Point newPos = new Point(worldToScreen(player.getPosition()));
        Vector2 playerPosition = lerp2d(oldPos, newPos, timeSinceMoveStarted / Consts.timeBetweenMovesMillis);

        return new Point((int) playerPosition.x, (int) playerPosition.y);
    }

    /**
     * Converts world coordinates to screen coordinates
     * @param worldPoint world coordinates
     * @return screen coordinates
     */
    private Point worldToScreen(Point worldPoint){
        return new Point(worldPoint.x * Consts.tileSize, worldPoint.y * Consts.tileSize);
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        grassTexture.dispose();
    }
}
