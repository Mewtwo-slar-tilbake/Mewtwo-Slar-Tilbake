package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.BattleController;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.entities.Trainer;
import com.kaijumon.game.view.BattleView;

import java.util.ArrayDeque;
import java.util.Queue;

public class BattleScreen implements Screen {

    private final BattleController controller;

    final KaijumonGame game;
    private BattleModel model;
    private BattleView view;

    // An event queue that is shared by all battle components.
    private Queue<Event> eventQueue = new ArrayDeque<>();

    public BattleScreen(final KaijumonGame game, Trainer player, Trainer opponent) {
        this.game = game;
        this.model = new BattleModel(player, opponent, eventQueue);
        this.view = new BattleView(game, model);
        this.controller = new BattleController(game, model, view, eventQueue);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        view.render(delta);
        controller.update();
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        view.dispose();
    }

}