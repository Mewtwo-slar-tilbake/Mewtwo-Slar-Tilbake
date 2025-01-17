package com.kaijumon.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionSelectBoxShould {

    private final KaijumonGame game;

    public ActionSelectBoxShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void startUpperLeft() {
        // Arrange
        ActionSelectBox actionSelectBox = new ActionSelectBox(game.getSkin());

        // Act & Assert (upper left has index 0)
        assertEquals(0, actionSelectBox.getSelected());
    }

    @Test
    public void moveSelection() {
        // Arrange
        ActionSelectBox actionSelectBox = new ActionSelectBox(game.getSkin());

        // Act & Assert
        // Select upper right
        actionSelectBox.moveRight();
        assertEquals(1, actionSelectBox.getSelected());
        // Select lower left
        actionSelectBox.moveLeft();
        actionSelectBox.moveDown();
        assertEquals(2, actionSelectBox.getSelected());
        // Select lower right
        actionSelectBox.moveRight();
        assertEquals(3, actionSelectBox.getSelected());
        // Select upper left
        actionSelectBox.moveLeft();
        actionSelectBox.moveUp();
        assertEquals(0, actionSelectBox.getSelected());

        // More movement to test all possibilities
        actionSelectBox.moveRight();
        actionSelectBox.moveDown();
        actionSelectBox.moveUp();
    }

}
