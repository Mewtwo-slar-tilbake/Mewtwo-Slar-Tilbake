package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IModel;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class PauseScreenShould {

    protected final KaijumonGame game;
    private final TestApplication testApplication;

    public PauseScreenShould() {
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
        new PauseScreen(game, mock(IModel.class));
    }

}
