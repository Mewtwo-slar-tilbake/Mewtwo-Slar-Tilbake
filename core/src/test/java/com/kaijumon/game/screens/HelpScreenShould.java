package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class HelpScreenShould {

    private final KaijumonGame game;

    public HelpScreenShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void show() {
        HelpScreen helpScreen = createScreen();
        helpScreen.show();
        helpScreen.dispose();
    }

    @Test
    public void render() {
        HelpScreen helpScreen = createScreen();
        helpScreen.render(1);
        helpScreen.dispose();
    }

    @Test
    public void resize() {
        HelpScreen helpScreen = createScreen();
        helpScreen.resize(1, 1);
        helpScreen.dispose();
    }

    @Test
    public void pause() {
        HelpScreen helpScreen = createScreen();
        helpScreen.pause();
        helpScreen.dispose();
    }

    @Test
    public void resume() {
        HelpScreen helpScreen = createScreen();
        helpScreen.resume();
        helpScreen.dispose();
    }

    @Test
    public void hide() {
        HelpScreen helpScreen = createScreen();
        helpScreen.hide();
        helpScreen.dispose();
    }

    private HelpScreen createScreen() {
        return new HelpScreen(game);
    }

}
