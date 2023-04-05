package com.kaijumon.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.dialog.DialogueSystem;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.entities.IEntity;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.utils.Consts;

import java.awt.Point;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class View implements IView {

    private final Texture npcTexture;
    private final TextureRegion[] playerDown = new TextureRegion[4];
    private final TextureRegion[] playerUp = new TextureRegion[4];
    private final TextureRegion[] playerLeft = new TextureRegion[4];
    private final TextureRegion[] playerRight = new TextureRegion[4];
    private final KaijumonGame game;
    private final IModel model;
    private final OrthographicCamera camera;
    private MapLayers mapLayers;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private String currentMapPath;


    public View(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        npcTexture = new Texture(Gdx.files.internal("player.jpg"));
        Texture playerSpriteSheet = new Texture(Gdx.files.internal("Male-Sprite-Sheet.png"));
        for (int i = 0; i < 4;  i++) {
            playerDown[i]   = new TextureRegion(playerSpriteSheet, 32 * (i + 4 * 0), 0, 31, 35);
            playerRight[i]  = new TextureRegion(playerSpriteSheet, 32 * (i + 4 * 1), 0, 31, 35);
            playerUp[i]     = new TextureRegion(playerSpriteSheet, 32 * (i + 4 * 2), 0, 31, 35);
            playerLeft[i]   = new TextureRegion(playerSpriteSheet, 32 * (i + 4 * 3), 0, 31, 35);
        }
        getNewMap();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if (!currentMapPath.equals(model.getTileMapPath())) {
            getNewMap();
        }

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);

        drawMapBackground();
        game.getBatch().begin();
        drawNpcs();
        drawPlayer();
        game.getBatch().end();
        drawMapForeground();
        DialogueSystem.getInstance().render();
    }

    private void getNewMap() {
        TiledMap tiledMap = new TmxMapLoader().load(model.getTileMapPath());
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 2);
        mapLayers = tiledMap.getLayers();
        currentMapPath = model.getTileMapPath();
    }

    private void drawPlayer() {
        Point playerPosition = calculateEntityPos(model.getPlayer());
        game.getBatch().draw(
                getPlayerTexture(),
                playerPosition.x,
                playerPosition.y,
                getPlayerTexture().getRegionWidth() * 2,
                getPlayerTexture().getRegionHeight() * 2
        );
        camera.translate(calculateCameraPosition(playerPosition));
    }


    private void drawNpcs() {
        List<Npc> npcList = model.getNpcList();
        for (Npc npc : npcList) {
            Point npcPosition = calculateEntityPos(npc);
            game.getBatch().draw(
                    npcTexture,
                    npcPosition.x,
                    npcPosition.y,
                    Consts.tileSize,
                    Consts.tileSize
            );
        }
    }

    /**
     * function to draw the background tiles
     * this is separate from the foreground tiles so that we can draw the player behind the foreground tiles
     * only tiles that have a 'isForeground' property and is visible will be drawn
     */
    private void drawMapBackground(){
        tiledMapRenderer.getBatch().begin();
        for (MapLayer layer : mapLayers) {
            boolean isForeground = layer.getProperties().containsKey("isForeground") && layer.getProperties().get("isForeground").equals(true);
            if (isForeground || !layer.isVisible())
                continue;
            tiledMapRenderer.renderTileLayer((TiledMapTileLayer) layer);

        }
        tiledMapRenderer.getBatch().end();
    }

    /**
     * function to draw the foreground tiles
     * this is separate from the background tiles so that we can draw the player behind the foreground tiles
     * only tiles that have a 'isForeground' property and is visible will be drawn
     */
    private void drawMapForeground(){
        tiledMapRenderer.getBatch().begin();
        for (MapLayer layer : mapLayers) {
            boolean isForeground = layer.getProperties().containsKey("isForeground") && layer.getProperties().get("isForeground").equals(true);
            if (!isForeground || !layer.isVisible())
                continue;
            tiledMapRenderer.renderTileLayer((TiledMapTileLayer) layer);

        }
        tiledMapRenderer.getBatch().end();
    }

    /**
     * Calculates the entity sprite of position based on the time since the last move
     * the entity moves instantly in the model, so we need to interpolate the position of the sprite.
     * @param entity the entity to calculate the position of
     * @return the world position of the entity sprite
     */
    private Point calculateEntityPos(IEntity entity) {
        float timeSinceMoveStarted = entity.getTimeSinceLastMove();
        Point lastEntityPos = entity.getLastPosition();

        if (timeSinceMoveStarted >= Consts.timeBetweenMovesMillis)
            lastEntityPos.setLocation(entity.getPosition());

        if (lastEntityPos.equals(entity.getPosition()))
            return worldToScreen(entity.getPosition());

        Point oldPos = new Point(worldToScreen(lastEntityPos));
        Point newPos = new Point(worldToScreen(entity.getPosition()));

        // Mapping from a time range to a position range, based on the time since the last move
        float clampedTime = clamp(timeSinceMoveStarted, 0, Consts.timeBetweenMovesMillis);
        int x = (int) map(0, Consts.timeBetweenMovesMillis, oldPos.x, newPos.x, clampedTime);
        int y = (int) map(0, Consts.timeBetweenMovesMillis, oldPos.y, newPos.y, clampedTime);
        return new Point(x, y);
    }

    private Vector2 calculateCameraPosition(Point playerPosition){

        float cameraMoveVectorX = playerPosition.x - camera.position.x;
        float cameraMoveVectorY = playerPosition.y - camera.position.y;

        Vector3 cameraBottomLeft = camera.frustum.planePoints[0];
        if (cameraBottomLeft.x < 0)
            cameraMoveVectorX = max(cameraMoveVectorX, 0);
        if (cameraBottomLeft.y < 0)
            cameraMoveVectorY = max(cameraMoveVectorY, 0);

        Vector3 cameraTopRight = camera.frustum.planePoints[2];

        int numTilesX = tiledMapRenderer.getMap().getProperties().get("width", Integer.class);
        int numTilesY = tiledMapRenderer.getMap().getProperties().get("height", Integer.class);

        Vector2 worldTopRight = new Vector2(numTilesX * Consts.tileSize ,numTilesY * Consts.tileSize );
        if (cameraTopRight.x > worldTopRight.x)
            cameraMoveVectorX = min(cameraMoveVectorX, 0);
        if (cameraTopRight.y > worldTopRight.y)
            cameraMoveVectorY = min(cameraMoveVectorY, 0);

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

    private TextureRegion getPlayerTexture(){
        Player player = model.getPlayer();
        Direction direction = player.getDirection();
        Long timeSinceLastMoved = player.getTimeSinceLastMove();
        int numImages = 4;
        int index;
        if (timeSinceLastMoved == 0 || timeSinceLastMoved > Consts.timeBetweenMovesMillis) {
            index = 0;
        } else {
            index = (int) ((timeSinceLastMoved / (Consts.timeBetweenMovesMillis / numImages)) % 4);
        }

        switch (direction){
            case UP:
                return playerUp[index];
            case DOWN:
                return playerDown[index];
            case LEFT:
                return playerLeft[index];
            case RIGHT:
                return playerRight[index];
        }

        return playerDown[0];

    }

    @Override
    public void dispose() {
        npcTexture.dispose();
    }
}
