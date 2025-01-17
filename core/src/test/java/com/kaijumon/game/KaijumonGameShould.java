package com.kaijumon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KaijumonGameShould {

    public KaijumonGameShould() {
        new TestApplication(new KaijumonGame());
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void render() {
        KaijumonGame game = new KaijumonGame();
        game.create();
        game.render();
        game.dispose();
    }

    @Test
    public void returnFont() {
        KaijumonGame game = new KaijumonGame();
        game.create();
        assertNotNull(game.getFont());
        game.dispose();
    }

}
