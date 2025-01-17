package com.kaijumon.game.view;

/**
 * Represents a view that renders the model on the screen.
 */
public interface IWorldView {

    /**
     * Render the model to the screen.
     *
     * @param delta delta time.
     */
    void render(float delta);

    /**
     * Dispose of all loaded assets.
     */
    void dispose();



}
