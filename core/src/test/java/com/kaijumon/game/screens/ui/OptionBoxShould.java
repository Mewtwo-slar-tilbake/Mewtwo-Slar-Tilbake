package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionBoxShould {

    private final KaijumonGame game;

    public OptionBoxShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void haveOptions() {
        // Arrange
        OptionBox optionBox = new OptionBox(game.getSkin());

        // Act
        optionBox.addOption("Option 1");
        optionBox.addOption("Option 2");
        optionBox.addOption("Option 3");
        optionBox.moveDown();
        optionBox.moveDown();

        // Assert that the bottom option (Option 3) is selected
        assertEquals(2, optionBox.getSelected());
    }

    @Test
    public void moveUpAndDown() {
        // Arrange
        OptionBox optionBox = new OptionBox(game.getSkin());

        // Act
        optionBox.addOption("Option 1");
        optionBox.addOption("Option 2");
        optionBox.addOption("Option 3");
        optionBox.moveDown();
        optionBox.moveUp();
        optionBox.moveDown();

        // Assert that the middle option (Option 2) is selected
        assertEquals(1, optionBox.getSelected());
    }

    @Test
    public void beCleared() {
        // Arrange
        OptionBox optionBox = new OptionBox(game.getSkin());

        // Act
        optionBox.addOption("Option 1");
        optionBox.addOption("Option 2");
        optionBox.addOption("Option 3");
        optionBox.moveDown();
        optionBox.clear();

        // Assert that the top option (Option 1) is selected
        assertEquals(0, optionBox.getSelected());
    }

}
