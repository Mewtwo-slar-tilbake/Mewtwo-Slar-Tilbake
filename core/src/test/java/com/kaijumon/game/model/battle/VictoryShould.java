package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VictoryShould {

    private Kaijumon A_Kaijumon = new Kaijumon(
            "Test McTested",
            new Stats(20, 10, 10, 5, 5, 10, 0, 1),
            Element.BUG,
            Arrays.asList(Attack.PUNCH, Attack.SLASH),
            Species.CHARALALA
    );

    @Test
    public void levelUp() {
        A_Kaijumon.setLevel(A_Kaijumon.getStats().level+1);
        assertEquals(2, A_Kaijumon.getStats().level);

    }

    @Test
    public void xpLevelUp() {
        Player player = new Player(0,0);
        player.getBag().addKaijumon(A_Kaijumon);

        A_Kaijumon.setXp(50);
        BattleResultListener listener = new BattleResultListener() {};

        listener.onBattleResult(BattleState.WIN,player);
        assertEquals(2, A_Kaijumon.getStats().level);
        assertEquals(0, A_Kaijumon.getStats().xp);
    }

    @Test
    public void xpExceedesLimit() {
        Player player = new Player(0,0);
        player.getBag().addKaijumon(A_Kaijumon);

        A_Kaijumon.setXp(90);
        BattleResultListener listener = new BattleResultListener() {};

        listener.onBattleResult(BattleState.WIN,player);

        assertEquals(40, A_Kaijumon.getStats().xp);
        assertEquals(2, A_Kaijumon.getStats().level);

    }
}
