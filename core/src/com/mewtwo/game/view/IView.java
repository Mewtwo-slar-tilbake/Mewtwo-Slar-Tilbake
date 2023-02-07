package com.mewtwo.game.view;

/**
 * Represents a view that renders the model on the screen.
 */
public interface IView {

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
