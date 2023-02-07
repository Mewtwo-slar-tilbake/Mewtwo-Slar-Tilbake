package com.kaijumon.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.tiles.Tile;

public class View implements IView {

    private final Texture playerTexture;
    private final Texture grassTexture;

    private final KaijumonGame game;
    private final IModel model;
    private final OrthographicCamera camera;

    public View(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        playerTexture = new Texture(Gdx.files.internal("player.jpg"));
        grassTexture = new Texture(Gdx.files.internal("grass.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
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
        game.batch.draw(
                playerTexture,
                player.x,
                player.y,
                60,
                60
        );

        game.batch.end();
    }

    @Override
    public void dispose() {
        playerTexture.dispose();
        grassTexture.dispose();
    }

}
