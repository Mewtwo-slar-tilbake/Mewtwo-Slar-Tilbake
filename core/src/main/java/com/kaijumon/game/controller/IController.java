package com.kaijumon.game.controller;

/**
 * Represents a controller that handles user input and
 * tells the model to update accordingly.
 */
public interface IController {

    /**
     * Handle user input and update the model.
     */
    void update();

    /**
     * Play background music.
     */
    void playMusic();

    /**
     * Dispose of assets.
     */
    void dispose();

}