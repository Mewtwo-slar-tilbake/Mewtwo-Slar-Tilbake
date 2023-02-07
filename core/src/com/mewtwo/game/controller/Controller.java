package com.mewtwo.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.mewtwo.game.KaijumonGame;
import com.mewtwo.game.model.IModel;

public class Controller implements IController {

    private final Music rainMusic;

    private final KaijumonGame game;
    private final IModel model;

    public Controller(final KaijumonGame game, IModel model) {
        this.game = game;
        this.model = model;

        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);
    }

    @Override
    public void handleInput() {
        float deltaX = 0, deltaY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            deltaX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            deltaX += 200 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            deltaY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            deltaY -= 200 * Gdx.graphics.getDeltaTime();

        model.movePlayer(deltaX, deltaY);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            game.pause();
    }

    @Override
    public void playMusic() {
        rainMusic.play();
    }

    @Override
    public void dispose() {
        rainMusic.dispose();
    }

}
