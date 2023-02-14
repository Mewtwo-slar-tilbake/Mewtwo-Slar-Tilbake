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

import static com.badlogic.gdx.math.MathUtils.*;

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


        camera.translate(calculateCameraPosition(playerPosition));

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

        if (timeSinceMoveStarted >= Consts.timeBetweenMovesMillis)
            lastPlayerPos.move(player.getX(), player.getY());

        if (lastPlayerPos.equals(player.getPosition()))
            return worldToScreen(player.getPosition());

        Point oldPos = new Point(worldToScreen(lastPlayerPos));
        Point newPos = new Point(worldToScreen(player.getPosition()));

        // Mapping from a time range to a position range, based on the time since the last move
        float clampedTime = clamp(timeSinceMoveStarted, 0, Consts.timeBetweenMovesMillis);
        int x = (int) map(0, Consts.timeBetweenMovesMillis, oldPos.x, newPos.x, clampedTime);
        int y = (int) map(0, Consts.timeBetweenMovesMillis, oldPos.y, newPos.y, clampedTime);
        return new Point(x, y);
    }

    private Vector2 calculateCameraPosition(Point playerPosition){

        float cameraMoveVectorX = playerPosition.x - camera.position.x;
        float cameraMoveVectorY = playerPosition.y - camera.position.y;

        return new Vector2(cameraMoveVectorX, cameraMoveVectorY);

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
