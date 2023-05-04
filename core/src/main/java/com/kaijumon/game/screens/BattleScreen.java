package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.BattleController;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.entities.ITrainer;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.screens.ui.KaijumonInfo;
import com.kaijumon.game.view.BattleView;

import java.util.ArrayDeque;
import java.util.Queue;

public class BattleScreen implements Screen {

    private final BattleController controller;

    final KaijumonGame game;
    private BattleModel battleModel;
    private BattleView view;

    // An event queue that is shared by all battle components.
    private Queue<Event> eventQueue = new ArrayDeque<>();

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
        this.battleModel = new BattleModel(player.getBag(), opponent, eventQueue);
        this.view = new BattleView(game, battleModel);
        this.controller = new BattleController(game, battleModel, view, eventQueue, battleModel.getOpponentKaijumon(), player, opponent, resultListener);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        view.render(delta);
        controller.update();
        game.getScreenType();
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
