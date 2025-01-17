package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.BattleController;
import com.kaijumon.game.controller.IBattleController;
import com.kaijumon.game.model.IBattleModel;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.view.BattleView;
import com.kaijumon.game.view.IBattleView;

import java.util.ArrayDeque;
import java.util.Queue;

public class BattleScreen implements Screen {

    private final KaijumonGame game;
    private final IBattleView battleView;
    private final IBattleController controller;


    /**
     * Creates a new battle screen.
     *
     * @param game           the game instance
     * @param player         the player
     * @param opponent       the opponent
     * @param resultListener the listener that will be called when the battle is over
     */
    public BattleScreen(final KaijumonGame game, Player player, ITrainer opponent, BattleResultListener resultListener) {
        this.game = game;
        // An event queue that is shared by all battle components.
        Queue<Event> eventQueue = new ArrayDeque<>();
        IBattleModel battleModel = new BattleModel(player.getBag(), opponent, eventQueue);
        this.battleView = new BattleView(game, battleModel);
        this.controller = new BattleController(game, battleModel, battleView, eventQueue, battleModel.getOpponentKaijumon(), player, opponent, resultListener);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        battleView.render(delta);
        controller.update();
        game.getScreenType();
    }

    @Override
    public void resize(int width, int height) {
        battleView.resize(width, height);
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
        battleView.dispose();
    }
}
