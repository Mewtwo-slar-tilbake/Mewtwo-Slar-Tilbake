package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.dialog.DialogueSystem;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.WorldModel;
import com.kaijumon.game.model.entities.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorldControllerShould {

    @Test
    public void updateModel() {
        // Arrange
        KaijumonGame game = new KaijumonGame();
        IModel model = new WorldModel(game, new Player(0, 0));
        IController controller = new WorldController(game, model);
        DialogueSystem.init(game);

        Gdx.input = mock(Input.class); // mock the input such that the right key is always pressed
        when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);

        // Act
        controller.update(10);

        // Assert
        assertNotEquals(new Point(0, 0), model.getPlayer().getPosition());
    }

}