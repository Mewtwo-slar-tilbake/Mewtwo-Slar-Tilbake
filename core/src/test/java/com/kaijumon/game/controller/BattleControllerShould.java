package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IBattleModel;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.TextEvent;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.view.BattleView;
import com.kaijumon.game.view.IBattleView;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.mock;

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
        IBattleController battleController = createController();
        battleController.update();
    }

    @Test
    public void processKeyEvents() {
        IBattleController battleController = createController();
        battleController.keyDown(Input.Keys.X);
    }

    private IBattleController createController() {
        KaijumonFactory kaijumonFactory = new KaijumonFactory();

        Kaijumon opponentKaijumon = kaijumonFactory.createKaijumon();
        Player player = new Player(0, 0);
        player.getBag().addKaijumon(kaijumonFactory.createKaijumon());
        ITrainer trainer = new Trainer(List.of(kaijumonFactory.createKaijumon()), TrainerType.Wild);
        Queue<Event> eventQueue = new ArrayDeque<>();
        IBattleModel battleModel = new BattleModel(new Trainer(player.getBag().getCrew(), TrainerType.Player), trainer, eventQueue);
        IBattleView battleView = new BattleView(game, battleModel);
        BattleResultListener battleResultListener = mock(BattleResultListener.class);

        eventQueue.add(new TextEvent(""));

        return new BattleController(game, battleModel, battleView, eventQueue, opponentKaijumon, player, trainer, battleResultListener);
    }

}
