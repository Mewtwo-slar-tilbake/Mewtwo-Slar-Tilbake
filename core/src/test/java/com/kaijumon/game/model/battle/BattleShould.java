package com.kaijumon.game.model.battle;

import com.kaijumon.game.battle.Action;
import com.kaijumon.game.battle.Battle;
import com.kaijumon.game.battle.Trainer;
import com.kaijumon.game.model.entities.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleShould {

    @Test
    public void damageOpponent() {
        // Arrange
        Trainer t1 = new Trainer(A_Kaijumon);
        Trainer t2 = new Trainer(A_Kaijumon);

        Battle battle = new Battle(t1, t2);

        // Act
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 1);

        // Assert
        assertEquals(92, t1.getKaijumon(0).getStats().hp);
    }

    private Kaijumon A_Kaijumon = new Kaijumon(
            "Test McTested",
            new Stats(100, 10, 10, 5, 5, 10),
            Element.BUG,
            Arrays.asList(Attack.PUNCH, Attack.SLASH)
    );

}
