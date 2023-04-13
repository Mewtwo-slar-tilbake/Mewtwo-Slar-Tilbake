package com.kaijumon.game.model.battle;

import com.kaijumon.game.model.entities.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VictoryShould {

    @Test
    public void levelUp() {
        A_Kaijumon.setLevel(A_Kaijumon.getStats().level+1);
        assertEquals(2, A_Kaijumon.getStats().level);

    }
    private Kaijumon A_Kaijumon = new Kaijumon(
            "Test McTested",
            new Stats(20, 10, 10, 5, 5, 10, 0, 1),
            Element.BUG,
            Arrays.asList(Attack.PUNCH, Attack.SLASH),
            Species.CHARALALA
    );
}
