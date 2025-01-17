package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.IWorldController;
import com.kaijumon.game.view.IWorldView;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class WorldScreenShould {

    private final KaijumonGame game;

    public WorldScreenShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void render() {
        WorldScreen worldScreen = createScreen();
        worldScreen.render(1);
        worldScreen.dispose();
    }

    @Test
    public void resize() {
        WorldScreen worldScreen = createScreen();
        worldScreen.resize(1, 1);
        worldScreen.dispose();
    }

    @Test
    public void show() {
        WorldScreen worldScreen = createScreen();
        worldScreen.show();
        worldScreen.dispose();
    }

    @Test
    public void hide() {
        WorldScreen worldScreen = createScreen();
        worldScreen.hide();
        worldScreen.dispose();
    }

    @Test
    public void pause() {
        WorldScreen worldScreen = createScreen();
        worldScreen.pause();
        worldScreen.dispose();
    }

    @Test
    public void resume() {
        WorldScreen worldScreen = createScreen();
        worldScreen.resume();
        worldScreen.dispose();
    }

    private WorldScreen createScreen() {
        return new WorldScreen(game, mock(IWorldView.class), mock(IWorldController.class));
    }

}
