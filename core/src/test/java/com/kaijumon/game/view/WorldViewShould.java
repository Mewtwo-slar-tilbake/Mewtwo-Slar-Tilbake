package com.kaijumon.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.WorldModel;
import com.kaijumon.game.model.entities.Player;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class WorldViewShould {

    private final KaijumonGame game;

    public WorldViewShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void render() {
        // Arrange
        IModel model = new WorldModel(game, new Player(0, 0));
        WorldView view = new WorldView(game, model);

        // Act & Assert
        view.render(1);
        view.dispose();
    }

}
