package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class MainMenuScreenShould {

    protected final KaijumonGame game;
    private final TestApplication testApplication;

    public MainMenuScreenShould() {
        this.game = new KaijumonGame();
        this.testApplication = new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void beInitialized() {
        new MainMenuScreen(game);
    }

}
