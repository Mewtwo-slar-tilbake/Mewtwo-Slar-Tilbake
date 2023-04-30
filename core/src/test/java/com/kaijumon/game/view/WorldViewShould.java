package com.kaijumon.game.view;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.headless.*;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GL20Interceptor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.Direction;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.WorldModel;
import com.kaijumon.game.model.entities.Player;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorldViewShould {
    static {
        HeadlessNativesLoader.load();
    }

    /*
    @Test
    public void moveCamera() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        Gdx.gl20 = mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        Gdx.graphics = mock(Graphics.class);

        Gdx.files = new HeadlessFiles();

        SpriteBatch mockBatch = mock(SpriteBatch.class);

        KaijumonGame game = new KaijumonGame();
        Field f = game.getClass().getDeclaredField("batch");
        f.setAccessible(true);
        f.set(game, mock(SpriteBatch.class)); // mock the sprite batch

        IModel model = new WorldModel(game, new Player(0, 0));
        WorldView view = new WorldView(game, model);

        Field field = view.getClass().getDeclaredField("camera"); // use reflection to access the private camera field
        field.setAccessible(true);

        Camera camera = (Camera) field.get(view);
        Vector3 initialPosition = camera.position;

        // Act
        model.movePlayer(Direction.RIGHT);

        // Assert
        Vector3 updatedPosition = camera.position;
        assertEquals(initialPosition, updatedPosition);
    }
    */

}
