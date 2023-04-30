package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.entities.*;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class BattleScreenShould {

    protected final KaijumonGame game;
    private final TestApplication testApplication;

    public BattleScreenShould() {
        this.game = new KaijumonGame();
        this.testApplication = new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void beInitialized() {
        // Arrange
        KaijumonFactory kaijumonFactory = new KaijumonFactory();

        Player player = new Player(0, 0);
        player.getBag().addKaijumon(kaijumonFactory.createKaijumon());

        Trainer trainer = new Trainer(List.of(kaijumonFactory.createKaijumon()), TrainerType.Wild);

        // Act & Assert
        new BattleScreen(game, player, trainer, mock(BattleResultListener.class));
    }

}
