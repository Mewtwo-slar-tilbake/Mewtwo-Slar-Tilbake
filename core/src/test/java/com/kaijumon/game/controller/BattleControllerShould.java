package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.IBattleModel;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleParty;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.battle.event.HpChangeEvent;
import com.kaijumon.game.model.battle.event.KaijumonChangeEvent;
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
        // select "Attack"
        battleController.keyDown(Input.Keys.X);
        battleController.keyDown(Input.Keys.C);
        // select "Kaijumon"
        battleController.keyDown(Input.Keys.RIGHT);
        battleController.keyDown(Input.Keys.X);
        battleController.keyDown(Input.Keys.C);
        // select "Flee"
        battleController.keyDown(Input.Keys.DOWN);
        battleController.keyDown(Input.Keys.X);
        battleController.keyDown(Input.Keys.C);
        // select "Bag"
        battleController.keyDown(Input.Keys.LEFT);
        battleController.keyDown(Input.Keys.X);
        battleController.keyDown(Input.Keys.C);
        // switch between attacks
        battleController.keyDown(Input.Keys.UP);
        battleController.keyDown(Input.Keys.X);
        battleController.keyDown(Input.Keys.DOWN);
        battleController.keyDown(Input.Keys.UP);
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
        eventQueue.add(new HpChangeEvent(BattleParty.OPPONENT, 10));
        eventQueue.add(new KaijumonChangeEvent(BattleParty.OPPONENT, opponentKaijumon));

        return new BattleController(game, battleModel, battleView, eventQueue, opponentKaijumon, player, trainer, battleResultListener);
    }

}
