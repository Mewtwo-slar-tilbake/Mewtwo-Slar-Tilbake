package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class MainMenuScreenShould {

    private final KaijumonGame game;

    public MainMenuScreenShould() {
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
        MainMenuScreen mainMenuScreen = createScreen();
        mainMenuScreen.render(1);
        mainMenuScreen.dispose();
    }

    @Test
    public void pause() {
        MainMenuScreen mainMenuScreen = createScreen();
        mainMenuScreen.pause();
        mainMenuScreen.dispose();
    }

    @Test
    public void resume() {
        MainMenuScreen mainMenuScreen = createScreen();
        mainMenuScreen.resume();
        mainMenuScreen.dispose();
    }

    private MainMenuScreen createScreen() {
        return new MainMenuScreen(game);
    }

}
