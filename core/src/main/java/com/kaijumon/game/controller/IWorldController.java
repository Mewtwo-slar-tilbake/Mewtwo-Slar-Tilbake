package com.kaijumon.game.controller;

/**
 * Represents a controller that handles user input and
 * tells the model to update accordingly.
 */
public interface IWorldController {

    /**
     * Handle user input and update the model.
     */
    void update(float delta);

    /**
     * Dispose of assets.
     */
    void dispose();

}