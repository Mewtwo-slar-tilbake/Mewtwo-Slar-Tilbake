package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.IBattleModel;
import com.kaijumon.game.model.entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleShould {

    @Test
    public void damageOpponent() {
        // Arrange
        Trainer t1 = new Trainer(Arrays.asList(A_Kaijumon), TrainerType.NPC);
        Trainer t2 = new Trainer(Arrays.asList(B_Kaijumon), TrainerType.Wild);

        IBattleModel battle = new BattleModel(t1, t2, new ArrayDeque<>());

        // Act
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 0);

        // Assert
        assertEquals(90, t1.getKaijumon(0).getStats().hp);
        assertEquals(90, t2.getKaijumon(0).getStats().hp);
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 0);
        assertEquals(80, t1.getKaijumon(0).getStats().hp);
        assertEquals(80, t2.getKaijumon(0).getStats().hp);
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 0);
        assertEquals(70, t1.getKaijumon(0).getStats().hp);
        assertEquals(70, t2.getKaijumon(0).getStats().hp);
    }

    private Kaijumon A_Kaijumon = new Kaijumon(
            "Test McTested",
            new Stats(100, 10, 10, 5, 5, 10,0,1),
            Element.BUG,
            Arrays.asList(Attack.PUNCH, Attack.SLASH),
            Species.CHARALALA
    );

    private Kaijumon B_Kaijumon = new Kaijumon(
            "Test McTested",
            new Stats(100, 10, 10, 5, 5, 15,0,1),
            Element.BUG,
            Arrays.asList(Attack.PUNCH, Attack.SLASH),
            Species.MAGDO
    );
}
