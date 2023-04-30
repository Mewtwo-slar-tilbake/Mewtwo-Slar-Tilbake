package com.kaijumon.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kaijumon.game.KaijumonGame;
import com.kaijumon.game.model.battle.BattleModel;
import com.kaijumon.game.model.battle.BattleResultListener;
import com.kaijumon.game.model.battle.event.Event;
import com.kaijumon.game.model.entities.*;
import com.kaijumon.game.screens.ui.ActionSelectBox;
import com.kaijumon.game.screens.ui.OptionBox;
import com.kaijumon.game.view.BattleView;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BattleControllerShould {

    /*
    @Test
    public void updateViewWidgets() {
        // Arrange
        KaijumonGame game = mock(KaijumonGame.class);
        Player player = new Player(0, 0);
        Kaijumon k = new Kaijumon("Kaijumon1", new Stats(100, 10, 10, 10, 10, 10, 0, 1), Element.FIRE, Arrays.asList(Attack.PUNCH, Attack.SLASH), Species.randomSpecies());

        ITrainer trainer = new Trainer(List.of(k), TrainerType.Player);
        ITrainer opponent = new Trainer(List.of(k), TrainerType.Wild);
        Queue<Event> eventQueue = mock(Queue.class);
        Kaijumon opponentKaijumon = mock(Kaijumon.class);
        BattleResultListener listener = mock(BattleResultListener.class);

        BattleModel model = new BattleModel(trainer, opponent, eventQueue);
        BattleView view = new BattleView(game, model);
        BattleController controller = new BattleController(game, model, view, eventQueue, opponentKaijumon, player, opponent, listener);

        // Act
        controller.setSelectOption();

        // Assert
        assert(!view.getActionSelectBox().isVisible());
        assert(view.getOptionBox().isVisible());
    }
    */

}
