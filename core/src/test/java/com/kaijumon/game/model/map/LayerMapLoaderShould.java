package com.kaijumon.game.model.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.utils.Consts;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class LayerMapLoaderShould {

    private final KaijumonGame game;

    public LayerMapLoaderShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void readMap() {
        LayerMapLoader layerMapLoader = new LayerMapLoader();
        layerMapLoader.getLayerData(Consts.mainMap, "Collision");
    }

}
