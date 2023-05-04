package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IModel;
import com.kaijumon.game.model.WorldModel;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.view.BattleView;
import com.kaijumon.game.view.WorldView;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BattleControllerShould {

    private final KaijumonGame game;

    public BattleControllerShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void update() {
        BattleController battleController = createController();
        battleController.update();
    }

    @Test
    public void processKeyEvents() {
        BattleController battleController = createController();
        battleController.keyDown(Input.Keys.X);
    }

    private BattleController createController() {
        KaijumonFactory kaijumonFactory = new KaijumonFactory();

        Kaijumon opponentKaijumon = kaijumonFactory.createKaijumon();
        Player player = new Player(0, 0);
        player.getBag().addKaijumon(kaijumonFactory.createKaijumon());
        ITrainer trainer = new Trainer(List.of(kaijumonFactory.createKaijumon()), TrainerType.Wild);
        Queue<Event> eventQueue = new ArrayDeque<>();
        BattleModel battleModel = new BattleModel(new Trainer(player.getBag().getCrew(), TrainerType.Player), trainer, eventQueue);
        BattleView battleView = new BattleView(game, battleModel);
        BattleResultListener battleResultListener = mock(BattleResultListener.class);

        eventQueue.add(new TextEvent(""));

        return new BattleController(game, battleModel, battleView, eventQueue, opponentKaijumon, player, trainer, battleResultListener);
    }

}