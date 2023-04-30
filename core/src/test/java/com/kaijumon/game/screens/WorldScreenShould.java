package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.IController;
import com.kaijumon.game.view.IView;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class WorldScreenShould {

    protected final KaijumonGame game;
    private final TestApplication testApplication;

    public WorldScreenShould() {
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
        new WorldScreen(game, mock(IView.class), mock(IController.class));
    }

}
