package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LossShould {

    /*
    public void damage() {
        Trainer t1 = new Trainer(Arrays.asList(A_Kaijumon), TrainerType.NPC);
        Trainer t2 = new Trainer(Arrays.asList(B_Kaijumon), TrainerType.Wild);

        BattleModel battle = new BattleModel(t1, t2, new ArrayDeque<>());

        // Act
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 0);
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 0);
        battle.progress(Action.FIGHT, 0, Action.FIGHT, 0);

        battle.FullHPCrew();
    }
     */


    private Kaijumon A_Kaijumon = new Kaijumon(
            "Test McTested",
            new Stats(20, 10, 10, 5, 5, 10,0,1),
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

    @Test
    public void getFullHP() {
        //damage();

        //assertEquals(100, A_Kaijumon.getStats().hp);
    }


}
