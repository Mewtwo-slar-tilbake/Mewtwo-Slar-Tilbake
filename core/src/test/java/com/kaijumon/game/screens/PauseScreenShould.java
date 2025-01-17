package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IWorldModel;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class PauseScreenShould {

    private final KaijumonGame game;

    public PauseScreenShould() {
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
        PauseScreen pauseScreen = createScreen();
        pauseScreen.render(1);
        pauseScreen.dispose();
    }

    @Test
    public void pause() {
        PauseScreen pauseScreen = createScreen();
        pauseScreen.pause();
        pauseScreen.dispose();
    }

    @Test
    public void resume() {
        PauseScreen pauseScreen = createScreen();
        pauseScreen.resume();
        pauseScreen.dispose();
    }

    @Test
    public void hide() {
        PauseScreen pauseScreen = createScreen();
        pauseScreen.hide();
        pauseScreen.dispose();
    }

    private PauseScreen createScreen() {
        return new PauseScreen(game, mock(IWorldModel.class));
    }

}
