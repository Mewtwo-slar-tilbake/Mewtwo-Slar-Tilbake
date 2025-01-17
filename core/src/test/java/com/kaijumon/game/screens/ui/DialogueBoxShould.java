package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.badlogic.gdx.graphics.Color;
import com.kaijumon.game.KaijumonGame;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class DialogueBoxShould {

    private final KaijumonGame game;

    public DialogueBoxShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void setText() {
        // Arrange
        DialogueBox dialogueBox = new DialogueBox("", game.getSkin());

        // Act (test passes if no exception is thrown)
        dialogueBox.setText("test");
        dialogueBox.setTextColor(Color.BLACK);
    }

}
