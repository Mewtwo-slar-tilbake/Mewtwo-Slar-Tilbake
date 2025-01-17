package com.kaijumon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl3.TestApplication;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.controller.BattleController;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.entities.KaijumonFactory;
import com.kaijumon.game.model.entities.Player;
import com.kaijumon.game.model.entities.Trainer;
import com.kaijumon.game.model.entities.TrainerType;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

public class BattleScreenShould {

    private final KaijumonGame game;

    public BattleScreenShould() {
        this.game = new KaijumonGame();
        new TestApplication(game);

        game.create();
    }

    @AfterClass
    public static void afterAll() {
        Gdx.app.exit();
    }

    @Test
    public void updateInputProcessor() {
        // Arrange & Act
        BattleScreen battleScreen = createScreen();
        battleScreen.show();

        // Assert
        InputProcessor inputProcessor = Gdx.input.getInputProcessor();
        assertInstanceOf(BattleController.class, inputProcessor);
        battleScreen.dispose();
    }

    @Test
    public void render() {
        BattleScreen battleScreen = createScreen();
        battleScreen.render(1);
        battleScreen.dispose();
    }

    @Test
    public void resize() {
        BattleScreen battleScreen = createScreen();
        battleScreen.resize(1, 1);
        battleScreen.dispose();
    }

    @Test
    public void pause() {
        BattleScreen battleScreen = createScreen();
        battleScreen.pause();
        battleScreen.dispose();
    }

    @Test
    public void resume() {
        BattleScreen battleScreen = createScreen();
        battleScreen.resume();
        battleScreen.dispose();
    }

    @Test
    public void hide() {
        BattleScreen battleScreen = createScreen();
        battleScreen.hide();
        battleScreen.dispose();
    }

    private BattleScreen createScreen() {
        KaijumonFactory kaijumonFactory = new KaijumonFactory();

        Player player = new Player(0, 0);
        player.getBag().addKaijumon(kaijumonFactory.createKaijumon());

        Trainer trainer = new Trainer(List.of(kaijumonFactory.createKaijumon()), TrainerType.Wild);

        return new BattleScreen(game, player, trainer, mock(BattleResultListener.class));
    }

}
