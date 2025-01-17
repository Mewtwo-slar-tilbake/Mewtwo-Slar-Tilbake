package com.kaijumon.game.model.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KaijumonShould {

    @Test
    public void returnInfo() {
        Stats stats = new Stats(0, 0, 0, 0, 0, 0,0,0);
        List<Attack> attackList = List.of(Attack.PUNCH);
        Kaijumon kai = new Kaijumon("FooBar", stats, Element.GHOST, attackList, Species.CHARALALA);

        assertEquals("FooBar", kai.getName());
        assertEquals(stats, kai.getStats());
        assertEquals(Element.GHOST, kai.getElement());
        assertEquals(attackList, kai.getAttacksList());
    }

}
