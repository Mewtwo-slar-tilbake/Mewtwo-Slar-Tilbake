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
import com.kaijumon.game.dialog.DialogueSystem;
import com.kaijumon.game.model.IWorldModel;
import com.kaijumon.game.model.entities.IEntity;
import com.kaijumon.game.model.entities.npc.Npc;
import com.kaijumon.game.utils.Consts;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.clamp;
import static com.badlogic.gdx.math.MathUtils.map;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class WorldView implements IWorldView {

    private final Texture npcTexture;
    private final CharacterSprite playerSprite;
    private final HashMap<Integer, CharacterSprite> npcSprites;
    private final KaijumonGame game;
    private final IWorldModel model;
    private final OrthographicCamera camera;
    private MapLayers mapLayers;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private String currentMapPath;


    public WorldView(final KaijumonGame game, IWorldModel model) {
        this.game = game;
        this.model = model;

        npcTexture = new Texture(Gdx.files.internal("assets/player.jpg"));
        playerSprite = new CharacterSprite(Consts.playerSpritePath);
        npcSprites = new HashMap<>();
        int i = 0;
        for (String spritePath : Consts.npcSpritePaths) {
            i++;
            npcSprites.put(i, new CharacterSprite(spritePath));
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
        TextureRegion sprite = playerSprite.getSprite(model.getPlayer());
        game.getBatch().draw(
                sprite,
                playerPosition.x,
                playerPosition.y,
                sprite.getRegionWidth() * 2,
                sprite.getRegionHeight() * 2
        );
        camera.translate(calculateCameraPosition(playerPosition));
    }


    private void drawNpcs() {
        List<Npc> npcList = model.getNpcList();
        for (Npc npc : npcList) {
            Point npcPosition = calculateEntityPos(npc);
            CharacterSprite npcSprite = npcSprites.get(npc.spriteId);
            TextureRegion sprite = npcSprite.getSprite(npc);
            game.getBatch().draw(
                    sprite,
                    npcPosition.x,
                    npcPosition.y,
                    sprite.getRegionWidth() * 2,
                    sprite.getRegionHeight() * 2
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

        // default camera movement following the player
        float cameraMoveVectorX = playerPosition.x - camera.position.x;
        float cameraMoveVectorY = playerPosition.y - camera.position.y;

        Vector3 cameraBottomLeft = camera.frustum.planePoints[0];
        Vector3 cameraTopRight = camera.frustum.planePoints[2];
        int numTilesX = tiledMapRenderer.getMap().getProperties().get("width", Integer.class);
        int numTilesY = tiledMapRenderer.getMap().getProperties().get("height", Integer.class);
        Vector2 worldTopRight = new Vector2(numTilesX * Consts.tileSize ,numTilesY * Consts.tileSize );

        // if the camera is outside the bottom or left part of the world
        if (cameraBottomLeft.x <= 0)
            cameraMoveVectorX = max(cameraMoveVectorX, -cameraBottomLeft.x);
        if (cameraBottomLeft.y <= 0)
            cameraMoveVectorY = max(cameraMoveVectorY, -cameraBottomLeft.y);

        // if the camera is outside the top or right part of the world
        if (cameraTopRight.x > worldTopRight.x)
            cameraMoveVectorX = min(cameraMoveVectorX, -(cameraTopRight.x - worldTopRight.x - 1));
        if (cameraTopRight.y > worldTopRight.y)
            cameraMoveVectorY = min(cameraMoveVectorY, -(cameraTopRight.y - worldTopRight.y - 1));

        // if the camera is bigger than the world
        if (cameraTopRight.x - cameraBottomLeft.x > worldTopRight.x)
            cameraMoveVectorX = playerPosition.x - camera.position.x;
        if (cameraTopRight.y - cameraBottomLeft.y > worldTopRight.y)
            cameraMoveVectorY = playerPosition.y - camera.position.y;

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
        npcTexture.dispose();
    }
}
