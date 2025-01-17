package com.kaijumon.game.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.screens.ui.OptionBox;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class DialogueTraverserShould {

    private final KaijumonGame game;

    public DialogueTraverserShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
        DialogueSystem.init(game);
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void beInstantiated() {
        new DialogueTraverser(1);
        new DialogueTraverser(1, new Player(0, 0), mock(ITrainer.class));
    }

    @Test
    public void goToNext() {
        // Arrange
        DialogueTraverser traverser = new DialogueTraverser(1);
        traverser.game = game;
        traverser.optionBox = new OptionBox(game.getSkin());
        DialogueNode startingNode = traverser.currentNode;

        // Act
        traverser.next();
        DialogueNode nextNode = traverser.currentNode;

        // Assert
        assertNotEquals(startingNode, nextNode);
    }

}
